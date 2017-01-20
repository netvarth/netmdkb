function ServerUrlProcessor() {	
	this.setUrl=function(url) {
		this.url = url;
	}

	this.post = function(param) {
		var postResponse;
		jQuery.ajax({
			type: "POST",
			url: this.url,
			data:param,
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){
					return false;
				}	
				else{
					postResponse = response;
				}	
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(thrownError);
				postResponse = false;	
				 
			}
		});	
		return postResponse;	
	}
    
	
	this.getRequest = function(param) {
		var postResponse;
		jQuery.ajax({
			type: "GET",
			url: this.url,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){
					return false;
				}	
				else{
					postResponse = response;
				}	
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(thrownError);
				postResponse = false;	
				 
			}
		});	
		return postResponse;	
	}

		this.getHtml = function(param) {
		var postResponse;
		jQuery.ajax({
			type: "GET",
			url: this.url,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "html",
			async: false,
			success:function(response) {
				if(response==null){
					return false;
				}	
				else{
					postResponse = response;
				}	
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(thrownError);
				postResponse = false;	
				 
			}
		});	
		return postResponse;	
	}
	this.get = function() {
		var response;
		var getCall=jQuery.getJSON(this.url,function(result){
			response=result;
			return response;
		});
         
		if(response==undefined) {
			response=false;
			
		}
		return response;	
	}
}	