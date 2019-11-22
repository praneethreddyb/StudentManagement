function validate() {
	var name = document.forms["form"]["name"].value;
	var nameFormat = /^[a-zA-Z ]{2,30}$/;
	if (name == "") {
		alert("fields Can't be empty");
	} else if (!name.match(nameFormat)) {
		alert("only characters are allowed");
	}
}