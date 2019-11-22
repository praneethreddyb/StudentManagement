${msg}
<p>${mode } Hobby</p>
<body onload=document.myform.user.focus>
<form action="do-hobbies" method="post" autocomplete="off" name="form" onsubmit="return validate()">
	<input type="hidden" name="id" value="${hobbies.pk_id==null?param.id:hobbies.pk_id}" autocomplete="false" /> 
	<label for="name">NAME:</label> 
	<input type="text" id="text-line" ${mode == 'View' ? 'disabled="disabled"' : ''} name="name" value="${hobbies.name==null?param.name:hobbies.name}" autocomplete="false" /><br> 
	<button type="submit" ${mode == 'View' ? 'hidden="hidden"' : ''}>${mode == 'Add' ? 'Save' : 'Update'}</button>
</form>
<script src="javascript/singlefieldvalidator.js"></script>
</body>