package com.otto.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dt;

	public StudentDbUtil(DataSource dt) {
		this.dt = dt;
	}
	
	
	public List<Email> getEmails() {
		
		List<Email> emails = new ArrayList<>();
		
		Connection con = null;
		Statement sts= null;
		
		ResultSet rs= null;
		
		
		try {
			con = dt.getConnection();
			
			String sql = "select * from emails";
			
			sts = con.createStatement();
			
			rs = sts.executeQuery(sql);
			
			while(rs.next()) {
				String empresa = rs.getString("empresa");
				String email = rs.getString("email");
				
				Email tempEmail = new Email();
				tempEmail.setEmail(email);
				tempEmail.setEmpresa(empresa);
				emails.add(tempEmail);
				
				
			}
			
			return emails;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(con, sts, rs);
		}
		
		return emails;
		
	}

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();

		Connection con = null;
		ResultSet rs = null;
		Statement state = null;

		try {

			// GET CONNECTION
			con = dt.getConnection();

			// CREATE SQL
			String sql = "select * from student order by last_name";

			state = con.createStatement();

			// EXECUTE QUERY
			rs = state.executeQuery(sql);

			// PROCESS RESULT SET

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("email");

				Student tempStudent = new Student(id, firstname, lastname, email);

				students.add(tempStudent);

			}
			return students;

			// CLOSE CONNECTION
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, state, rs);
		}
		return students;

	}

	private void close(Connection con, Statement state, ResultSet rs) {
		try {
			if (con != null && state != null && rs != null) {
				con.close();
				state.close();
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addStudent(Student student) throws SQLException {
		Connection con = null;
		PreparedStatement state = null;

		try {
			con = dt.getConnection();
			String sql = "insert into student (first_name, last_name, email) values(?, ? , ?)";
			state = con.prepareStatement(sql);

			state.setString(1, student.getFirstName());
			state.setString(2, student.getLastname());
			state.setString(3, student.getEmail());

			state.execute();
		} finally {
			close(con, state, null);
		}

	}

	public Student getStudent(String id) throws Exception {

		Connection myConn = null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		int studentId;
		Student theStudent = null;
		try {
			// convert student id to int
			studentId = Integer.parseInt(id);

			// get connection to database
			myConn = dt.getConnection();

			// create sql to get selected student
			String sql = "select * from student where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// use the studentId during construction
				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}

			return theStudent;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateStudent(Student updateS) throws SQLException {
		Connection con = null;
		PreparedStatement state = null;
		try {
			// get db conecction
			con = dt.getConnection();
			// create sql update statement
			String sql = "update student set first_name=?, last_name=?, email=? where id=?";
			// prepare statement
			state = con.prepareStatement(sql);
			
			// set params
			state.setString(1, updateS.getFirstName());
			state.setString(2, updateS.getLastname());
			state.setString(3, updateS.getEmail());
			state.setInt(4, updateS.getId());
			// execute sql statement
			
			state.execute();
		} finally {
			close(con, state, null);
		}

	}

	public void deleteStudent(String theStudentId) throws SQLException {
		Connection con = null;
		PreparedStatement state = null;
		
		try {
			int id = Integer.parseInt(theStudentId);
			con = dt.getConnection();
			
			String sql = "delete from student where id=?";
			
			state = con.prepareStatement(sql);
			
			state.setInt(1, id);
			
			state.execute();
		} finally {
			close(con, state, null);
		}
		
	}

}
