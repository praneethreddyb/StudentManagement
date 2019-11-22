function validateFields() {
	var name = document.forms["studentForm"]["name"].value;
	var dob = document.forms["studentForm"]["dob"].value;
	var standard = document.forms["studentForm"]["standards"].value;
	var gender = document.forms["studentForm"]["gender"].value;
	var description = document.forms["studentForm"]["message"].value;
	 var nameFormat=/^[a-zA-Z ]{2,30}$/;
	if (name == "" || dob == "" || standard ==0 || gender == "") {
		alert("Details Can't be empty");
		return false;
	}else if(!dob.match("([0-9]{4})-([0-9]{2})-([0-9]{2})")||(!name.match(nameFormat))){
		alert("Incorrect credentials");
		return false;
	}
}
