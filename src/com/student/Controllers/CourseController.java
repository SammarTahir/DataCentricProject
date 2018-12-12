package com.student.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.student.DAOs.DAO;
import com.student.Models.Course;

@ManagedBean
@SessionScoped
public class CourseController {

	DAO dao;
	ArrayList<Course> courses;

	public CourseController() throws Exception {
		super();
		dao = new DAO();
		courses = new ArrayList<Course>();
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public void loadCourses() throws Exception {
		courses = dao.loadCourses();
		// Checking to see if system memory is found
		System.out.println(courses.toString());
	}

	public String addCourse(Course courses) {
		if (dao != null) {
			try {
				dao.addCourse(courses);
				return "list_courses";
			} catch (MySQLIntegrityConstraintViolationException e) {
				// Throwing error of Course ID already exists
				FacesMessage message = new FacesMessage("Error: Course ID " + courses.getcID() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Course " + courses.getcID());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}

	public String deleteCourse(Course course) {
		try {
			dao.deleteCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list_courses";

	}
	
	
}
