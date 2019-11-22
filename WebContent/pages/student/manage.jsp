<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java" contentType="text/html charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1"><title>Students</title>
</head>
<body>
<p class="list_heading">LIST OF STUDENTS</p>
<a href="student-operations" class="add_button" >Add</a>
<display:table class="table" name="students" id="row" pagesize="6" requestURI="">
		<display:column title="Name" property="name" />
		<display:column title="Standard" property="standard" />
		<display:column title="Date of Birth" property="dob" />
		<display:column title="Gender" property="gender"/>
		<display:column title="Action" value="${param.id}" >
			<a class="button" href="student-operations?id=${row.pk_id }&mode=edit">Edit</a>
			<a class="delete_button" href="./do-studentdelete?id=${row.pk_id }"
				onclick="return confirm('Are you sure?')">Delete</a>
			<button onclick="showCustomer(${row.pk_id })">View</button>
			<%-- <a class="button" href="student-operations?id=${row.pk_id }">View</a> --%>
		</display:column>
	</display:table>
	<div id="txtHint">Customer info will be listed here...</div>
<script src="javascript/ajax.js"></script>
</body>
</html>


