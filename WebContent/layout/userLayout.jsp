<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student</title>
<link type="text/css" rel="stylesheet" href="css/userLayout.css" />
</head>
<body>
	<header>
		<img src="./images/main logo.jpg">
		<p class="heading">Student</p>
		<p class="user">${sessionUser.email}!</p>
			<a class="logout_button" href="logout"  onclick="myFunction()">Logout</a>
	</header>
	<section>
	<nav>
  <div class="div" id="about"><a id="homep" href="manage-students" class="p">Student</a>
  </div>
  <div class="div" id="contact"><a href="manage-class"class="p">Class</a>
  </div>
  <div class="div" id="gallery"><a href="manage-hobbies"class="p">Hobbies</a>
  </div>
  </nav>
		<article>
			<jsp:include page="${page}" />
		</article>
	</section>
	<footer>
		<p>
			<i>copyrights 2019@All rights reserved</i>
		</p>
	</footer>
	<script>
	function myFunction() {
		  setTimeout(function(){ alert("Hello"); }, 500);
		}
</script>
</body>
</html>

