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

	public void loadStudents() throws Exception {
		students = dao.loadStudents();
		System.out.println(students.toString());
	}
}
