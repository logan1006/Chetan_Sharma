package com.Instructor;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InstructorLogin
 */
@WebServlet("/InstructorLogin")
public class InstructorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String uname = request.getParameter("uname");
	String pass = request.getParameter("pwd");
	
	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","MyNewPass"); 
	Statement st= con.createStatement();
	ResultSet rs=st.executeQuery("select * from login_Staff where ID='"+uname+"'"); 
	if(rs.next()) 
	{ 	
		if(rs.getString(2).equals(pass)){
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("InstructorHome.jsp");
		
			}else
				{con.close();
				response.sendRedirect("Instructor_Login.jsp");
				}
							
	}else
		{con.close();
		response.sendRedirect("Instructor_Login.jsp");
		}
	
	}catch(Exception e){
		System.err.println(e);
	}
	
}
}
