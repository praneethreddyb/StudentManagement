function validateForm() {
	var username =document.forms["myForm"]["user"].value;
	var userpassword = document.forms["myForm"]["password"].value;;
	var mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (username == "" || userpassword == "") {
		alert("Details Can't be empty");
		return false;
	} else if ((!username.match(mailformat))||(userpassword.length<5)) {
		alert("invalid credentials");
		return false;
	} 
}
