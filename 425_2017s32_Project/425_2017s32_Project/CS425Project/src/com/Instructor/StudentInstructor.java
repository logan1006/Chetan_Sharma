package com.Instructor;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/StudentInstructor")
public class StudentInstructor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String t = request.getParameter("task");
		HttpSession session = request.getSession();
		String uname = session.getAttribute("username").toString();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","MyNewPass"); 
			Statement st= con.createStatement();
		
			if(t.equals("Students"))
			{
				ResultSet rs=st.executeQuery("select S_ID, first_name, last_name, s_email_id  from student where Staff_ID='"+uname+"'"); 
				String str = "<table border=1><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email ID</th></tr>";
				while(rs.next()) 
				{ 		
					str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
				}

				con.close();
				str += "</table>";
				out.println(str);
			}
			else if(t.equals("Courses"))
			{
				ResultSet rs=st.executeQuery("select C_id, C_Name, c_credit from courses where staff_id='"+uname+"'"); 
				String str = "<table border=1><tr><th>Course ID</th><th>Course Name</th><th>Credit</th></tr>";
				while(rs.next()) 
				{ 		
					str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
				}

				con.close();
				str += "</table>";
				out.println(str);

			}
			
			}catch(Exception e){
				System.err.println(e);
			}finally{
				out.close();
			}

	
	}

}
