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

@WebServlet("/BookDataDetails")
public class BookDataDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private String str = null;
	private String q;
	private String id;
	private PrintWriter out;
	private Connection con;
	private Statement st;
       
    public BookDataDetails() {
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
			 q="select * from book";
			try{
				rs=st.executeQuery(q);  
			
			str = "<table border=1><tr>"
			 		+ "<th>Book ID</th><th>Book Group ID</th><th>Book Name</th><th>Edition</th><th>Author</th>"
			 		+ "<th>Pblisher</th><th>Price</th></tr>";
			while(rs.next()) 
			{ 		
				str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"
						+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>";
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
			 q="select * from book where B_ID='"+id+"' ";
			try{
				rs=st.executeQuery(q);  
			
				str = "<table border=1><tr>"
				 		+ "<th>Book ID</th><th>Book Group ID</th><th>Book Name</th><th>Edition</th><th>Author</th>"
				 		+ "<th>Pblisher</th><th>Price</th></tr>";
				while(rs.next()) 
				{ 		
					str +="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"
							+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>";
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
			 q="delete from book where B_ID='"+id+"' ";
			try{
				st.executeUpdate(q);  
			str="Book with id '"+id+"' has been removed";
			out.println(str);	
			
			}catch(Exception e){
				System.err.println(e);
			}finally{
					out.close();
			}
	    }

	  //Add student data
	    protected void Add(HttpServletRequest request, Statement st) throws ServletException, IOException {
	    		
	    	String Bid = request.getParameter("ID");
	    	String gid = request.getParameter("G_ID");
	    	String Name = request.getParameter("name");
	    	String ed = request.getParameter("ed");
	    	String author = request.getParameter("aname");
	    	String pub = request.getParameter("pb");
	    	String Price = request.getParameter("price");
	    	
	    //	System.out.println(pin);
			 q="insert into book (B_ID, B_Grp_ID, B_Name, B_Edition, B_Authour, B_Publisher, B_Price) "
			 		+ "values ('"+Bid+"', '"+gid+"', '"+Name+"', '"+ed+"', '"+author+"', '"+pub+"', '"+Price+"')";
			try{
				st.executeUpdate(q);  
			str="Book with id '"+Bid+"' has been added";
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
	    	
	    	String bid = request.getParameter("ID");
	    	String gid = request.getParameter("G_ID");
	    	String Name = request.getParameter("name");
	    	String Ed = request.getParameter("ed");
	    	String author = request.getParameter("aname");
	    	String pub = request.getParameter("pb");
	    	String Price = request.getParameter("price");
	    	
	    	
	  // 	if(!sid.isEmpty()) cols+="S_ID='"+sid+"'" ;
	    	if(!gid.isEmpty()) {cols+=" B_Grp_ID='"+gid+"'"; count++ ;}
	    	
	    	if(!Name.isEmpty()) {
	    		if(count>0)	cols+=" , B_Name='"+Name+"'" ;
	    		else {cols+=" B_Name='"+Name+"'" ; count++;}
	    	}
	    	
	    	if(!Ed.isEmpty()){
	    		if(count>0)cols+=" , B_Edition='"+Ed+"'" ;
	    		else {cols+=" B_Edition='"+Ed+"'" ; count++;}
	    	   	}
	    	
	    	if(!author.isEmpty()){
	    		if(count>0)cols+=" , B_Authour='"+author+"'" ;
	    		else {cols+=" B_Authour='"+author+"'" ; count++;}
	    	   	}
	    	
	    	if(!pub.isEmpty()){
	    		if(count>0)cols+=" , B_Publisher='"+pub+"'" ;
	    		else {cols+=" B_Publisher='"+pub+"'" ; count++;}
	    	}
	    	
	    	if(!Price.isEmpty()){
	    		if(count>0)cols+=" , B_Price='"+Price+"'" ;
	    		else {cols+=" B_Price='"+Price+"'" ; count++;}
	    	}
	    	
			 q=" update book set "+cols+" where B_ID='"+bid+"'";
			 
			 
			try{
			
				st.executeUpdate(q);  
			str="Book with id '"+bid+"' has been updated with below data \n";
			out.println(str);	
			
			}catch(Exception e){
				System.err.println(e);
			}finally{
					out.close();
			}
	   }
	    

}
