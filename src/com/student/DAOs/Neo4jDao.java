package com.student.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.*;

import com.student.Models.Course;
import com.student.Models.Student;

public class Neo4jDao {

	// Establishing a connection to neo4j servers
	Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4jdb"));
	// Establishing a session
	Session session = driver.session();

	public ArrayList<Student> loadStudents() throws SQLException {
		System.out.println("In load Courses");
		Session session = driver.session();
		Object name;
		StatementResult result =
				session.run("match(p:Person{name: $name})-[:STUDIES]->(course) return course.name",
						parameters("name", name));
		return null;

	}

	private Value parameters(String string, Object name) {
		// TODO Auto-generated method stub
		return null;
	}
}
