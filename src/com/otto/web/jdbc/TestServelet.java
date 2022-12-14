package com.otto.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServelet
 */
@WebServlet("/TestServelet")
public class TestServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// connect pool for resource
	
	@Resource(name ="jdbc/web_student_tracker")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SET UP THE PRINTWRITER
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// GET CONNECTION DATABASE
		
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		
		try {
			// CREATE SQL STATEMENT
			con = dataSource.getConnection();
			String sql = "select * from student";
			
			state = con.createStatement();
			// EXECUTE SQL QUERY
			rs = state.executeQuery(sql);
			
			// PROCESSE THE RESULTSET
			
			while(rs.next()) {
				String email = rs.getString("email");
				out.print(email);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
