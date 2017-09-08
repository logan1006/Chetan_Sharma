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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InstructorDataDetails
 */
@WebServlet("/InstructorDataDetails")
public class InstructorDataDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private String str = null;
	private String q;
	private String id;
	private PrintWriter out;
	private Connection con;
	private Statement st;
       

    public InstructorDataDetails() {
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
			
			case "viewAll": viewAll(request, st);
							break;
			
			case "viewS":	view(request, st);
							break;
							
			case "delete":	delete(request, st);
							break;
			
			case "addS":	  Add(request, st);
							  break;

			case "update":	Update(request, st);
							break;
			
			}
					
			}catch(Exception e){
				System.err.println(e);
			}finally{
				out.close();
			}
	}

  //View all Instructor data
    protected void viewAll(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="select * from staff where Staff_iD in (select id from login_staff where role='I') ";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>"
		 		+ "<th>DOB</th><th>Dept ID</th><th>DOJ</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"
					+rs.getString(10)+"</td><td>"+rs.getString(8)+","+rs.getString(5)+","+rs.getString(6)+"</td><td>"+
					rs.getString(11)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(12)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
			out.close();
		}
 
    }

    
    //View one Instructor data
    protected void view(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="select * from staff where Staff_ID='"+id+"' ";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>"
		 		+ "<th>DOB</th><th>Dept ID</th><th>Advisor ID</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"
					+rs.getString(10)+"</td><td>"+rs.getString(8)+","+rs.getString(5)+","+rs.getString(6)+"</td><td>"+
					rs.getString(11)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(12)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
    }
    
    
    //delete Instructor data
    protected void delete(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="delete from staff where Staff_ID='"+id+"' ";
		try{
			st.executeUpdate(q);  
		str="Instructor with id '"+id+"' has been removed";
		out.println(str);	
		
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
    }

  //Add Instructor data
    protected void Add(HttpServletRequest request, Statement st) throws ServletException, IOException {
    		
    	String Iid = request.getParameter("ID");
    	String fN = request.getParameter("fname");
    	String lN = request.getParameter("lname");
    	String em = request.getParameter("email");
    	String DOB = request.getParameter("dob");
    	String Did = request.getParameter("aID");
    	String pn = request.getParameter("pID");
    	String yoj = request.getParameter("yoj");
    	String street = request.getParameter("street");
    	String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	String pin = request.getParameter("pin");
    //	System.out.println(pin);
    	 q="insert into staff (Staff_ID, D_ID, First_Name, Last_Name, City, State, Pin, Street, Staff_PhoneNumber, Staff_Email_ID, Staff_DOB, Staff_DOJ)"
 		 		+ "values ('"+Iid+"', '"+Did+"',  '"+fN+"', '"+lN+"', '"+city+"', '"+state+"', '"+pin+"', '"+street+"', '"+pn+"', '"+em+"', '"+DOB+"', '"+yoj+"')";
		try{
			st.executeUpdate(q);  
		str="Instructor with id '"+Iid+"' has been added";
		out.println(str);	
		
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
   }

    
  //update student data
    protected void Update(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	int count=0;
    	String cols=" ";
    	
    	String Iid = request.getParameter("ID");
    	String em = request.getParameter("email");
    	String Did = request.getParameter("aID");
    	String yoj = request.getParameter("yoj");
    	
  // 	if(!sid.isEmpty()) cols+="S_ID='"+sid+"'" ;
    	if(!em.isEmpty()) {cols+=" Staff_Email_ID='"+em+"'"; count++ ;}
    	
    	if(!Did.isEmpty()) {
    		if(count>0)	cols+=" , D_ID='"+Did+"'" ;
    		else {cols+=" D_ID='"+Did+"'" ; count++;}
    	}
    	
    	if(!yoj.isEmpty()){
    		if(count>0)cols+=" , Staff_DOJ='"+yoj+"'" ;
    		else {cols+=" Staff_DOJ='"+yoj+"'" ; count++;}
    	   	}
    	
		 q=" update staff set "+cols+" where Staff_ID='"+Iid+"'";
		 
		 
		try{
		
			st.executeUpdate(q);  
		str="Instructor with id '"+Iid+"' has been updated with below data \n";
		out.println(str);	
		
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
   }
    
}
