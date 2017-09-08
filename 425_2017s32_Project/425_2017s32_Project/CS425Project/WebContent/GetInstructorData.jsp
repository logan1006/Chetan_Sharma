<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<title>Get Instructor Data</title>
</head>
<body>


<%
HttpSession session2 = request.getSession(true);
String t2 = request.getParameter("task");
session2.setAttribute("task", t2);
System.out.print(t2);
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
	<strong>Enter Instructor Details</strong><br>
    <form action="InstructorDataDetails" method="get">
	<ul class="form-style-1">
	<li>
        <label>Instructor ID <span class="required">*</span></label>
        <input type="text" name="ID" class="field-long" required/>
    </li>
    <li><label>Full Name <span class="required">*</span></label><input type="text" name="fname" class="field-divided" placeholder="First" required/>&nbsp;<input type="text" name="lname" class="field-divided" placeholder="Last" required/></li>
    <li>
        <label>Email <span class="required">*</span></label>
        <input type="email" name="email" class="field-long" placeholder="@hawk.iit.edu" required/>
    </li>
    <li>
        <label>Date of Birth <span class="required">*</span></label>
        <input type="text" name="dob" class="field-long" placeholder="YYYY-MM-DD" required/>
    </li>
	<li>
        <label>Date of Joining <span class="required">*</span></label>
        <input type="text" name="yoj" class="field-long" placeholder="YYYY-MM-DD" required/>
    </li>
	<li>
        <label>Department ID<span class="required">*</span></label>
        <input type="text" name="aID" class="field-long" required/>
    </li>
    <li>
        <label>Street</label>
        <input type="text" name="street" class="field-long" placeholder="Street No."/>
    </li>
    <li>
        <label>City</label>
        <input type="text" name="city" class="field-long" placeholder="City"/>
    </li>
    <li>
        <label>State</label>
        <input type="text" name="state" class="field-long" placeholder="State"/>
    </li>
	<li>
        <label>Zipcode<span class="required">*</span></label>
        <input type="text" name="pin" class="field-long" placeholder="Pincode" required/>
    </li>
	<li>
        <label>Phone  Number</label>
        <input type="text" name="pID" class="field-long" placeholder="(XXX)XXX-XXXX"/>
    </li>
    <li>
        <input type="submit" value="Submit" />
    </li>
  </ul>
  </form>
    </section>
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