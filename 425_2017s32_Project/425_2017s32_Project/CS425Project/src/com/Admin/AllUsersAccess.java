package com.Admin;

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

/**
 * Servlet implementation class AllUsersAccess
 */
@WebServlet("/AllUsersAccess")
public class AllUsersAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AllUsersAccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","MyNewPass"); 
			Statement st= con.createStatement();
			ResultSet rs;
			String str = null, home="AdminHome.jsp";
			String q1, q2, q3;
			
		/*	out.println("<html><body>");
			out.println("<form action="+home+">");
			out.println("</body></html>");
			
			<div align="center">
			<input type="submit" value="Home">

			</div>
			</form>
*/
			
			q1="select S_ID, first_name, last_name from Student where S_ID IN (select ID from login_student)";
			rs=st.executeQuery(q1); 
			str="<Strong>Student Level Access<Strong><br><br>"; 
			str += "<table border=1><tr><th>User ID</th><th>Name</th></tr>";
			while(rs.next()) 
			{ 		
				str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td></tr>";
			}
			str += "</table>";
			
			q2="select Staff_ID, first_name, last_name from Staff where Staff_ID IN (select ID from login_staff where role='I')";
			rs=st.executeQuery(q2); 
			str+="<br><br><Strong>Instructor Level Access<Strong><br><br>"; 
			str += "<table border=1><tr><th>User ID</th><th>Name</th></tr>";
			while(rs.next()) 
			{ 		
				str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td></tr>";
			}
			str += "</table>";
			

			q3="select Staff_ID, first_name, last_name from Staff where Staff_ID IN (select ID from login_staff where role='A')";
			rs=st.executeQuery(q3); 
			str+="<br><br><Strong>Admin Level Access<Strong><br><br>"; 
			str += "<table border=1><tr><th>User ID</th><th>Name</th></tr>";
			while(rs.next()) 
			{ 		
				str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td></tr>";
			}
			str += "</table>";
			
			
		out.println(str);			
	}catch(Exception e){
		System.err.println(e);
	}finally{
		out.close();
		}
	}

}
