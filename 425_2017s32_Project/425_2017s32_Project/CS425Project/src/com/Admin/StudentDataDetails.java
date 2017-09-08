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
 * Servlet implementation class StudentDataDetails
 */
@WebServlet("/StudentDataDetails")
public class StudentDataDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private String str = null;
	private String q;
	private String id;
	private PrintWriter out;
	private Connection con;
	private Statement st;
       
    public StudentDataDetails() {
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
    
  //View all student data
    protected void viewAll(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="select * from student";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>"
		 		+ "<th>DOB</th><th>Prog ID</th><th>Advisor ID</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"
					+rs.getString(2)+"</td><td>"+rs.getString(11)+","+rs.getString(9)+","+rs.getString(8)+"</td><td>"+
					rs.getString(5)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(14)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
			out.close();
		}
 
    }

    
    //View one student data
    protected void view(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="select * from student where S_ID='"+id+"' ";
		try{
			rs=st.executeQuery(q);  
		
		str = "<table border=1><tr>"
		 		+ "<th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Address</th>"
		 		+ "<th>DOB</th><th>Prog ID</th><th>Advisor ID</th></tr>";
		while(rs.next()) 
		{ 		
			str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"
					+rs.getString(2)+"</td><td>"+rs.getString(11)+","+rs.getString(9)+","+rs.getString(8)+"</td><td>"+
					rs.getString(5)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(14)+"</td></tr>";
		}
		str += "</table>";
		
		out.println(str);	
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
    }
    
    
    //delete student data
    protected void delete(HttpServletRequest request, Statement st) throws ServletException, IOException {
    	 id = request.getParameter("ID");
		 q="delete from student where S_ID='"+id+"' ";
		try{
			st.executeUpdate(q);  
		str="Student with id '"+id+"' has been removed";
		out.println(str);	
		
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
    }

  //Add student data
    protected void Add(HttpServletRequest request, Statement st) throws ServletException, IOException {
    		
    	String sid = request.getParameter("ID");
    	String fN = request.getParameter("fname");
    	String lN = request.getParameter("lname");
    	String em = request.getParameter("email");
    	String DOB = request.getParameter("dob");
    	String advisor = request.getParameter("aID");
    	String pID = request.getParameter("pID");
    	String yoj = request.getParameter("yoj");
    	String pgmID = request.getParameter("pgmID");
    	//String S_type = request.getParameter("type");
    	String street = request.getParameter("street");
    	String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	String pin = request.getParameter("pin");
    	String S_type = request.getParameter("type");
    	
		 q="insert into student (S_ID, S_Email_ID, pgm_ID, P_ID, S_DOB, First_Name, Last_Name, State, city, pin, street, S_yoj, S_type, staff_ID) "
		 		+ "values ('"+sid+"', '"+em+"', '"+pgmID+"', '"+pID+"', '"+DOB+"', '"+fN+"', '"+lN+"', '"+state+"', '"+city+"', '"+pin+"', '"+street+"', '"+yoj+"', '"+S_type+"', '"+advisor+"')";
		try{
			st.executeUpdate(q);  
		str="Student with id '"+sid+"' has been added";
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
    	
    	String sid = request.getParameter("ID");
    	String em = request.getParameter("email");
    	String advisor = request.getParameter("aID");
    	String pID = request.getParameter("pID");
    	String yoj = request.getParameter("yoj");
    	String pgmID = request.getParameter("pgmID");
    	String S_type = request.getParameter("type");
    	System.out.println(sid+"<--> "+em+"<--> "+yoj);
    	
  // 	if(!sid.isEmpty()) cols+="S_ID='"+sid+"'" ;
    	if(!em.isEmpty()) {cols+=" S_Email_ID='"+em+"'"; count++ ;}
    	
    	if(!advisor.isEmpty()) {
    		if(count>0)	cols+=" , Staff_ID='"+advisor+"'" ;
    		else {cols+=" Staff_ID='"+advisor+"'" ; count++;}
    	}
    	
    	if(!pID.isEmpty()){
    		if(count>0)cols+=" , P_ID='"+pID+"'" ;
    		else {cols+=" p_ID='"+pID+"'" ; count++;}
    	   	}
    	
    	if(!yoj.isEmpty()){
    		if(count>0)cols+=" , S_YOJ='"+yoj+"'" ;
    		else {cols+=" S_YOJ='"+yoj+"'" ; count++;}
    	   	}
    	
    	if(!pgmID.isEmpty()){
    		if(count>0)cols+=" , pgm_ID='"+pgmID+"'" ;
    		else {cols+=" pgm_ID='"+pgmID+"'" ; count++;}
    	}
    	
    	if(!S_type.isEmpty()){
    		if(count>0)cols+=" , S_Type='"+S_type+"'" ;
    		else {cols+=" S_Type='"+S_type+"'" ; count++;}
    	}
    	
		 q=" update student set "+cols+" where S_ID='"+sid+"'";
		 
		 
		try{
		
			st.executeUpdate(q);  
		str="Student with id '"+sid+"' has been updated with below data \n";
		out.println(str);	
		
		}catch(Exception e){
			System.err.println(e);
		}finally{
				out.close();
		}
   }

    
    
}
