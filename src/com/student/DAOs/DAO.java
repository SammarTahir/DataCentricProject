package com.student.DAOs;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.student.Models.Course;
import com.student.Models.Student;

public class DAO {
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	// Loads in information about courses on list_courses
	public ArrayList<Course> loadCourses() throws SQLException {
		System.out.println("In load Courses");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from course";
		ResultSet rs = myStmt.executeQuery(query);
		ArrayList<Course> courses = new ArrayList<Course>();

		while (rs.next()) {
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			String duration = rs.getString("duration");

			Course c = new Course(cID, cName, duration);
			courses.add(c);
		}
		return courses;
	}
	
	// This method adds a new course
	public void addCourse(Course courses) throws Exception {
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet rs = null;

		conn = mysqlDS.getConnection();
		String sql = "insert into course values (?, ?, ?)";
		myStmt = conn.prepareStatement(sql);
		myStmt.setString(1, courses.getcID());
		myStmt.setString(2, courses.getcName());
		myStmt.setString(3, courses.getDuration());
		myStmt.execute();
	}

	// This method deletes a course based on the course ID
	public void deleteCourse(Course courses) throws Exception {
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet rs = null;

		conn = mysqlDS.getConnection();
		String sql = "delete from course where cID like ?";
		myStmt = conn.prepareStatement(sql);
		myStmt.setString(1, courses.getcID());
		myStmt.execute();
	}

	// Finding students 
	public ArrayList<Student> findStudent(Student student) throws Exception {
		System.out.println("In load Student");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from Student";
		ResultSet rs = myStmt.executeQuery(query);
		ArrayList<Student> studentList = new ArrayList<Student>();
		/*conn = mysqlDS.getConnection();
		String sql = "select * from student where cID like ?";
		myStmt = conn.prepareStatement(sql);
		myStmt.execute();*/
		
		while (rs.next()) {
			String sid = rs.getString("sid");
			String cID = rs.getString("cID");
			String name = rs.getString("name");
			String address = rs.getString("address");

			Student s = new Student(sid, cID, name, address);
			studentList.add(s);
		} 
		return studentList;
	}

	public ArrayList<Student> loadStudent() throws SQLException{
		System.out.println("In load Students");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from student";
		ResultSet rs = myStmt.executeQuery(query);
		ArrayList<Student> students = new ArrayList<Student>();

		while (rs.next()) {
			String sid = rs.getString("sid");
			String cID = rs.getString("cID");
			String name = rs.getString("name");
			String address = rs.getString("address");

			Student s = new Student(sid, cID, name, address);
			students.add(s);
		}
		return students;
	}

	public void addStudent(Student students) throws Exception{
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet rs = null;

		conn = mysqlDS.getConnection();
		String sql = "insert into student values (?, ?, ?, ?)";
		myStmt = conn.prepareStatement(sql);
		myStmt.setString(1, students.getSid());
		myStmt.setString(2, students.getcID());
		myStmt.setString(3, students.getName());
		myStmt.setString(4, students.getAddress());
		myStmt.execute();
		
	}
	
	public void deleteStudent(Student student) throws Exception {
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet rs = null;

		conn = mysqlDS.getConnection();
		String sql = "delete from student where sid like ?";
		myStmt = conn.prepareStatement(sql);
		myStmt.setString(1, student.getSid());
		myStmt.execute();
	}
}
