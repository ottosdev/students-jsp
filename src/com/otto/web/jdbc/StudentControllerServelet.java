package com.otto.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServelet
 */
@WebServlet("/StudentControllerServelet")
public class StudentControllerServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDb;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dt;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			studentDb = new StudentDbUtil(dt);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

//			String command = request.getParameter("command");
//
//			if (command == null) {
//				command = "LIST";
//			}
//			switch (command) {
//			case "LIST":
//				listStudents(request, response);
//				break;
//			case "ADD":
//				addStudent(request, response);
//				break;
//			case "LOAD":
//				loadStudent(request, response);
//				break;
//			case "UPDATE":
//				updateStudent(request, response);
//				break;
//			case "DELETE":
//				deleteStudent(request, response);
//				break;
//			case "EMAIL":
//				listEmails(request, response);
//				break;
//			default: 
//				listStudents(request, response);
//			}
			listEmails(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theStudentId = request.getParameter("studentID");
		
		studentDb.deleteStudent(theStudentId);
		
		listStudents(request, response);
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String theStudentId = request.getParameter("studentID");
			
			// get student from database (db util)
			Student theStudent = studentDb.getStudent(theStudentId);
			
			// place student in the request attribute
			request.setAttribute("THE_STUDENT", theStudent);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/update-student-form.jsp");
			dispatcher.forward(request, response);		
		}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// read student info from form
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new Student object
		Student updateS = new Student(id, firstName, lastName, email);
		// updpate on database
		studentDb.updateStudent(updateS);
		// send back to list page
		listStudents(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student(firstName, lastName, email);
		
		studentDb.addStudent(student);
		
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students = studentDb.getStudents();

		request.setAttribute("STUDENTS_LIST", students);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);

	}
	
	private void listEmails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Email> emails = studentDb.getEmails();

		request.setAttribute("EMAIL_LIST", emails);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/emails.jsp");
		dispatcher.forward(request, response);

	}

}
