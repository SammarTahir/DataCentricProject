package com.student.Controllers;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.student.DAOs.DAO;
import com.student.Models.Course;
import com.student.Models.Student;

@ManagedBean
@SessionScoped
public class StudentController {

	DAO dao;
	ArrayList<Student> students;

	public StudentController() throws Exception {
		super();
		dao = new DAO();
		students = new ArrayList<Student>();
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public void loadStudent() throws Exception {
		students = dao.loadStudent();
		// Checking to see if system memory is found
		System.out.println(students.toString());
	}
	
	public String addStudent(Student students) {
		if (dao != null) {
			try {
				dao.addStudent(students);
				return "list_courses";
			} catch (MySQLIntegrityConstraintViolationException e) {
				// Throwing error of Course ID already exists
				FacesMessage message = new FacesMessage("Error: Student ID " + students.getSid() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Student " + students.getSid());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}

	public String deleteStudent(Student student) {
		try {
			dao.deleteStudent(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list_students";

	}
	
	public String findStudent(Student student) {
		try {
			System.out.println(student.getcID());
			dao.findStudent(student);	
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}	
		return "viewFindStudent";
	}
}
