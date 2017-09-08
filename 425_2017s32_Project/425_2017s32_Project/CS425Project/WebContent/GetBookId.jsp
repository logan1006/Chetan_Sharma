<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<title>Get Book ID</title>
</head>
<body>


<%
HttpSession session2 = request.getSession(true);
String t2 = request.getParameter("task");
session2.setAttribute("task", t2);
%>

<div class="wrap">
  <header>
    <div class="container">
      <h1><a href="#">CS-425 University Site</a></h1>
      <nav>
        <ul>
          <li class="current"><a href="AdminHome.jsp" class="m1">Home Page</a></li>
        </ul>
      </nav>
   </div>
  </header>
  <div class="container">
    <aside>
      <h3>Menu Options</h3>
      <ul class="categories">
       <li><span><a href="StudentData.jsp">Student Info</a></span></li>
        <li><span><a href="InstructorData.jsp">Instructor Info</a></span></li>
        <li><span><a href="BookData.jsp">Library Book Info</a></span></li>
        <li><span><a href="Borrowed.jsp">Book Borrowed Info</a></span></li>
        <li><span><a href= "Admin_logout?method=get">Logout</a></span></li>
       </ul>
    </aside>
	<section id="content">
      <form action="BookDataDetails" method="get">
	<ul class="form-style-1">
	<li>
        <label>Enter Book ID <span class="required">*</span></label>
        <input type="text" name="ID" class="field-long" required />
    </li>
    <li>
        <input type="submit" value="Submit"  />
    </li>
  </ul>
  </form>

  </div>  
<footer>
  <div class="footerlink">
    <p class="lf">Copyright &copy; 2010 <a href="#">SiteName</a> - All Rights Reserved</p>
    <p class="rf">Design by <a href="http://www.templatemonster.com/">CS-425 Grp - 32</a></p>
    <div style="clear:both;"></div>
  </div>
</footer>
</div>
</form>
</body>
</html>