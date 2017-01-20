function CookieProcessor() {	
	this.bake_cookie=function(name, value){
		if(new String(name).valueOf() != new String("None").valueOf()){
			document.cookie=name+"="+value ;
		}
	}
	this.deleteAllCookies=function() {
		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
		}
	}
	this.saveToCookie=function(printerListArray){
		for (var i = 0; i < printerListArray.length; i += 2) {
			var name=printerListArray[i];
			var value=printerListArray[i+1];
			this.bake_cookie(name, value);
			
		}
	} 	
	this.getCookie = function(name){
		var keyValue = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)'); 
		return keyValue ? keyValue[2] : null;  
	}
	
}