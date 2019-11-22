<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>
<link rel="stylesheet" type="text/css" href="css/studentoperations.css">
</head>
<body>
${msg}
<p>${mode } Student</p>
<form action="do-student" method="post" autocomplete="off" name="studentForm" onsubmit="return validateFields()">
	<input type="hidden" name="id" value="${student.pk_id==null?param.id:student.pk_id}" autocomplete="false" /> 
	<label for="name">NAME:</label> 
	<input type="text"  ${mode == 'View' ? 'disabled="disabled"' : ''} name="name" value="${student.name==null?param.name:student.name}" autocomplete="false" /> 
	<label for="standards"  class="labels_right">CLASS:</label>
	   <select name="standards" ${mode == 'View' ? 'disabled="disabled"' : ''} id="drop" class="inputs_right">
	   <option value=0>----select----</option>
          <c:forEach var="standards" items="${standards}">
           <option value="${standards.pk_id}" <c:if test="${fn:indexOf(student.standard, standards.name)>=0}">selected="selected"</c:if>>${standards.name}</option>
           </c:forEach>
        </select><br>
        <label for="gender">GENDER:</label> 
	<input type="radio" ${mode == 'View' ? 'disabled="disabled"' : ''} name="gender" value="male" ${student.gender == 'male' ? 'checked' : ''} /><span>male</span>
	<input type="radio" ${mode == 'View' ? 'disabled="disabled"' : ''} name="gender" value="female" ${student.gender == 'female' ? 'checked' : ''}  /><span>female</span>
	<label for="dob" class="labels_right">DATE OF BIRTH:</label> 
	<input type="text" class="inputs_right" id="text-line" ${mode == 'View' ? 'disabled="disabled"' : ''} name="dob" value="${student.dob==null?param.dob:student.dob}" autocomplete="false" placeholder="YYYY-MM-DD" /><br>
	<label for="hobbies">HOBBIES:</label>
	<c:forEach var="hobbies" items="${hobbies}">
           <input type="checkbox" name="hobbies"  ${mode == 'View' ? 'disabled="disabled"' : ''} <c:if test="${fn:indexOf(student.hobbies, hobbies.name)>=0}">checked="checked"</c:if> value="${hobbies.pk_id}"/><span>${hobbies.name}</span>
    </c:forEach>
    <label for="message" class="labels_right">REMARKS:</label>
   <textarea class="inputs_right" id="drop" rows="5" cols="30" placeholder="Add Description" name="message" ${mode == 'View' ? 'disabled="disabled"' : ''}>
   <c:out value="${student.description}"></c:out>
   </textarea><br>
    <button type="submit" ${mode == 'View' ? 'hidden="hidden"' : ''} >${mode == 'Add' ? 'Save' : 'Update'}</button>
</form>
<script src="javascript/formValidation.js"></script>
</body>