<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p  class="list_heading">LIST OF CLASSES </p>
<a href="class-operations" class="add_button" >ADD</a>
<display:table class="table" name="standards" id="row" pagesize="3" requestURI="">
		<display:column property="name" />
		<display:column title="Action" value="${param.id}" >
			<a class="button" href="class-operations?id=${row.pk_id }&mode=edit">Edit</a>
			<a class="delete_button" href="./do-classdelete?id=${row.pk_id }"
				onclick="return confirm('Are you sure?')">Delete</a>
			<a class="button" href="class-operations?id=${row.pk_id }">View</a>
		</display:column>
	</display:table>
	${msg}
</body>
</html>