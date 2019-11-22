<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body onload='document.myForm.user.focus()'>
<form action="do-login" method="POST" name="myForm" onsubmit="return validateForm()"> 
<label for="user">USER ID:</label>
<input name="user" size="25" placeholder="someexample@gmail.com" value="${param.user}" id="user"/><br>
<label for="password">PASSWORD:</label>
<input  type="password" name="password" size="25"  id="password"/><br><br>
<button type="submit" value="submit">Login</button>
</form>
<p>${param.msg}</p>
<p>${msg}</p>
<script src="javascript/loginValidation.js"></script>
</body>
</html>


