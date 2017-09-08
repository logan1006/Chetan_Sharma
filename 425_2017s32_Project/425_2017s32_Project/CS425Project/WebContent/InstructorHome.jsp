<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<title>Instructor Home</title>
</head>
<body>
<div class="wrap">
  <header>
    <div class="container">
      <h1><a href="#">CS-425 University Site</a></h1>
      <nav>
        <ul>
          <li class="current"><a href="InstructorHome.jsp" class="m1">Home Page</a></li>
        </ul>
      </nav>
      
    </div>
  </header>
  <div class="container">
    <aside>
      <h3>Menu Options</h3>
      <ul class="categories">
         <li><span><a href="StudentInstructor?method=get&task=Students">View Students</a></span></li>
      <li><span><a href="StudentInstructor?method=get&task=Courses">View courses</a></span></li>
       <li><span><a href= "Logout?method=get">Logout</a></span></li>
        </ul>
    </aside>
	<section id="content">
      <div id="banner">
        <h2>Illinois Tech <span>CS-425 DBO <span>Spring 2017</span></span></h2>
      </div>
    </section>
  </div>  
<footer>
  <div class="footerlink">
    <p class="lf">Copyright &copy; 2010 <a href="#">SiteName</a> - All Rights Reserved</p>
    <p class="rf">Design by <a href="http://www.templatemonster.com/">CS-425 Grp - 32</a></p>
    <div style="clear:both;"></div>
  </div>
</footer>	
</body>
</html>