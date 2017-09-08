package com.Student;

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

@WebServlet("/StudentDetails")
public class StudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession(true);
		String uname = session.getAttribute("username").toString();
		
		String t=request.getParameter("task");
		if(t==null)
		{
			t = session.getAttribute("task").toString();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","MyNewPass"); 
			Statement st= con.createStatement();
			ResultSet rs;
			String str = null;
			String q;
			String id;
			
			switch (t){
			
			case "viewC":	q="select C_ID, C_Name, C_credit from courses where C_ID IN (select C_ID from enrollment where S_ID='"+uname+"')";
							rs=st.executeQuery(q); 
							 str = "<table border=1><tr><th>ID</th><th>CourseName</th><th>Course credit</th></tr>";
							while(rs.next()) 
							{ 		
								str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>";
							}
							str += "</table>";
							break;
			
			case "addC":	 id = request.getParameter("ID");
							 q="insert into enrollment values('"+uname+"', '"+id+"', curdate(), null, null, null)";
							st.executeUpdate(q); 
							str="Your course with id '"+id+"' has been added";
							break;
				
			case "dropC":	  id = request.getParameter("ID");
							  q="delete from enrollment where S_ID='"+uname+"' and C_ID='"+id+"' ";
							  st.executeUpdate(q); 
							   str="Your course with id '"+id+"' has been dropped";
							  break;

			case "viewB":	q="select b.B_ID, b.B_Name, r.DOI, r.DueDate from book as b, "
								+ "(select B_ID, DueDate, DOI from borrowed where S_ID='"+uname+"' and DOR = null) as r"
										+ " where r.B_ID=b.B_ID ";
						
							rs=st.executeQuery(q); 
							 str = "<table border=1><tr><th>Book ID</th><th>Name</th><th>Issued On</th><th>Due Date</th></tr>";
							while(rs.next()) 
							{ 		
								str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>";
							}
							str += "</table>";
							break;
			
			}
			
			out.println(str);			
			}catch(Exception e){
				System.err.println(e);
			}finally{
				out.close();
			}

	}

}
