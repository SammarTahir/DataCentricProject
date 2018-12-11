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

	// Loads in information about students on list_courses when show student details
	// is pressed
	public ArrayList<Student> loadStudents() throws SQLException {
		System.out.println("In load Student");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from student where cID like ?";
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
	
	//find city
		/*public ArrayList<City> findCity(City city, String opt) throws Exception{
			ArrayList<City> cityList = new ArrayList<City>();
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int i = 1;
			
			myConn = mysqlDS.getConnection();
			query.append("SELECT * from city where isCoastal = ?");
			
			
			if(!city.getCountryName().equals("")) {
				query.append(" and co_code = ?");
			}
			
			if(city.getPopulation() != 0) {
				if(opt.equals("lt")) {
					query.append(" and population < ?");
				} else if (opt.equals("gt")) {
					query.append(" and population > ?");
				} else if (opt.equals("e")) {
					query.append(" and population = ?");
				}
			}
			myStmt = myConn.prepareStatement(query.toString());
			myStmt.setBoolean(i, city.getCoastal());
			if(city.getPopulation() != 0) {
				myStmt.setInt(i++, city.getPopulation());
			}
			if(!city.getCocode().equals("")) {
				myStmt.setString(i++, city.getCocode());
			}
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				city.setCtycode(myRs.getString("cty_code"));
				city.setAreaKM(myRs.getDouble("areaKM"));
				city.setCoastal(myRs.getBoolean("isCoastal"));
				city.setCocode(myRs.getString("co_code"));
				city.setCtyname(myRs.getString("cty_name"));
				city.setPopulation(myRs.getInt("population"));
				city.setRegcode(myRs.getString("reg_code"));
				city.setRegiomName(myRs.getString("reg_name"));
				city.setCountryName(myRs.getString("co_name"));
				
				cityList.add(city);
			} // while
			
			myRs.close();
			myStmt.close();
			return cityList;		
			
		}*/
}
