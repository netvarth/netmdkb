function Validator() {
	this.isEmpty = function(value) {
		if(value.trim().length==0)
			return true;
		else
			return false;
	}	
	this.isEmptyList = function(list) {
		if(list.length==0)
			return true;
		else
			return false;
	}	
	this.validateName = function(name) {
		var regexp = /^[0-9A-Za-z_-]'?[ 0-9A-Za-z#$%=@!{},`~&*()'<>?.:;_|^ +\t\r\n\[\]"-]*/;
		if(regexp.test(name.trim()))
			return true;
		else
			return false;
	}	
	//This function returns true if the email is valid else false
	//email - email id for validation
	this.validateEmail = function (email) { 
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
		if(email.match(mailformat))  
			return true;  
		return false;
	}	
	this.validatePhone = function(phone){
		var phoneFormat = /\(?([0-9]{4})\)?([ .-]?)([0-9])\d{6}$/;  
		if(phone.match(phoneFormat))  
			return true;  
		return false;
	}
	this.validateMobile = function(mobile){
		var phoneFormat = /^[0-9]\d{9}$/;  
		if(mobile.match(phoneFormat))  
			return true;  
		return false;
	}
	
}