package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TesDbServlet
 */
@WebServlet("/TesDbServlet")
public class TesDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TesDbServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set up Connection variables
			String user = "springstudent";
			String password = "springstudent";
			String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&allowPublicKeyRetrieval=true";
			String driver = "com.mysql.cj.jdbc.Driver";
			
		// Get connection to database
			try {
				PrintWriter out = response.getWriter();
				out.println("Connection to database: " + jdbcUrl);
				Class.forName(driver);
				Connection myConn = DriverManager.getConnection(jdbcUrl,user, password);
				out.println("Connection Successful");
				myConn.close();
			} catch(Exception ex) {
				   ex.printStackTrace();
			}
	}

}
