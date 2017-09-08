<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<title>Instructor Login</title>
</head>
<body>
<div class="wrap">
  <header>
    <div class="container">
      <h1><a href="#">CS-425 University Site</a></h1>
    </div>
  </header>
  <div class="container">
  	<div class="login-wrap">
	<div class="login-html">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"></label>
		<input id="tab-2" type="radio" name="tab" class	="sign-up"><label for="tab-2" class="tab"></label>
		<div class="login-form">
			<div class="sign-in-htm">
				<div class="group">
				<form action="InstructorLogin" method="post">
					<label for="user" class="label">Username</label>
					<input name="uname" type="text" class="input" placeholder="username">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input name="pwd" type="password" class="input" data-type="password" placeholder="password">
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign In">
				</div>
				<div class="hr"></div>
				<div class="foot-lnk">
					<a href="#forgot">Forgot Password?</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	</div>
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