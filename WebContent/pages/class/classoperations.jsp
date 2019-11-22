<body>
${msg}
<p>${mode } Class</p>
<form action="do-class" method="post" autocomplete="off" name="form" onsubmit="return validate()">
	<input type="hidden" name="id" value="${standard.pk_id==null?param.id:standard.pk_id}" autocomplete="false" /> 
	<label for="name">NAME:</label> 
	<input type="text" id="text-line" ${mode == 'View' ? 'disabled="disabled"' : ''} name="name" value="${standard.name==null?param.name:standard.name}" autocomplete="false" /><br> 
	<button type="submit" ${mode == 'View' ? 'hidden="hidden"' : ''}>${mode == 'Add' ? 'Save' : 'Update'}</button>
</form>
<script src="javascript/singlefieldvalidator.js"></script>
</body>