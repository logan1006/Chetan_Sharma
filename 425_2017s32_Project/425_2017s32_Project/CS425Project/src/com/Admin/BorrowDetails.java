package com.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/BorrowDetails")
public class BorrowDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private String str = null;
	private String q;
	private String id;
	private PrintWriter out;
	private Connection con;
	private Statement st;
   
    public BorrowDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String t=request.getParameter("task");
		if(t==null)
		{
			t = session.getAttribute("task").toString();
		}
		response.setContentType("text/html");
		 out = response.getWriter();
			
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","MyNewPass"); 
			 st= con.createStatement();
			
			switch (t){
			
			case "viewC": viewCheckedOut(request, st);
							break;
			
			case "viewG":	viewCheckedOutWithClass(request, st);
							break;
							
			case "viewA":	viewCheckedOutWithDue(request, st);
							break;
							
			case "borrow": add(request, st);
							break;
			
			case "returnB": ReturnBorrowed(request, st);
							break;
						
			}
					
			}catch(Exception e){
				System.err.println(e);
			}finally{
				out.close();
			}


	}
	
	 private void ReturnBorrowed(HttpServletRequest request, Statement st2) {
		 String datediff;
		int amt = 0 ;	
		 String bid = request.getParameter("bID");
	    	String sid = request.getParameter("sID");
	    	
	    	datediff="select datediff(curdate(), DueDate), DueDate from borrowed where b_id='"+bid+"'";
	    	
			 q="update borrowed set DOR=curdate(), Amt=? where b_id=? ";
			
			 try{
				 
				 rs=st.executeQuery(datediff);
				 rs.next();
				 if(Integer.parseInt(rs.getString(1))>0) amt=2;
			
				 PreparedStatement pst=con.prepareStatement(q);
				 pst.setInt(1, amt);
				 pst.setString(2, bid);
				// pst.setString(3, bid);
				
				pst.executeUpdate();  
				if(amt>0)
					str="Student with id '"+sid+"' has returned book '"+bid+"' after due date"+rs.getString(2)+" and fine is $"+amt+" ";
				else
					str="Student with id '"+sid+"' has returned book '"+bid+"'";
			out.println(str);	
			
			}catch(Exception e){
				System.err.println(e);
			}finally{
					out.close();
			}


		
	}

	private void add(HttpServletRequest request, Statement st2) {
		String bgrp;	
		 String bid = request.getParameter("bID");
	    	String sid = request.getParameter("sID");
	    //	System.out.println(pin);
	    	bgrp="select B_Grp_ID from book where b_id='"+bid+"'";
	    	
			 q="insert into borrowed (DOI, DueDate, B_Grp_ID, S_ID, B_ID) "
			 		+ "values (curdate(), DATE_ADD(CURDATE(), INTERVAL 10 DAY),?,?,? )";
			
			 try{
				 
				 rs=st.executeQuery(bgrp);
				 rs.next();
				 
				 PreparedStatement pst=con.prepareStatement(q);
				 pst.setString(1, rs.getString(1));
				 pst.setString(2, sid);
				 pst.setString(3, bid);
				
				pst.executeUpdate();  
			str="Student with id '"+sid+"' has borrowed book '"+bid+"'";
			out.println(str);	
			
			}catch(Exception e){
				System.err.println(e);
			}finally{
					out.close();
			}

	}

	protected void viewCheckedOut(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	// id = request.getParameter("ID");
		 q="select s.S_ID, s.first_name, s.last_name, b.b_id, b.b_name, br.doi, br.duedate, br.DOR from "
		 		+ "(borrowed br natural join student s) inner join book b on br.b_id=b.b_id";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>Student ID</th><th>Name</th><th>Book ID</th><th>Book Name</th><th>DOI</th>"
		 		+ "<th>Due Date</th><th>DOR</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td>"
					+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
			out.close();
		}
 
    }

	 protected void viewCheckedOutWithClass(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="select  c.C_ID, c.C_Name,b.b_id, b.b_name, b.B_Edition, b.B_Authour"
		 		+ " from (borrowed br inner join book b on br.b_id=b.b_id)"
		 		+ " inner join courses c on br.B_Grp_ID=c.C_ID "
		 		+ "group by b.B_Grp_ID, b.b_id, b.B_Edition, b.B_Authour";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>Course ID</th><th>Course Name</th><th>Book ID</th><th>Book Name</th><th>Edition</th><th>Author</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"
					+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
			out.close();
		}
 
    }

	 protected void viewCheckedOutWithDue(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	// id = request.getParameter("ID");
		 q="select s.S_ID, s.first_name, s.last_name, s.S_email_ID, p.P_ID, p.first_name, p.last_name, p.P_email_ID, s.street,s.city, sum(br.amt)"
		 		+ " from ((select * from borrowed where amt>0) br natural join student s)"
		 		+ " inner join parent p on p.P_ID=s.P_ID group by s.S_ID";
	
		 
		 try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>Student ID</th><th>Student Name</th><th>Student Email</th><th>Parent ID</th><th>Parent Name</th>"
		 		+ "<th>Parent email</th><th>Address</th><th>Amount Due</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"
					+rs.getString(5)+"</td><td>"+rs.getString(6)+" "+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+", "+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td></tr>";
			
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
