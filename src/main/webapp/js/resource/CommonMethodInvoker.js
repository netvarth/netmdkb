function CommonMethodInvoker() {
	ajaxProcessor.setUrl(constants.GETERRORCODEURL);
	this.errorCode = ajaxProcessor.get();
		
	this.createError = function(obj,msg) {
		var errorDiv=$j('<div/>');
		errorDiv.attr('class',"errorStyle");
		errorDiv.text(msg);
		obj.closest('p').append(errorDiv);
	}
	
	//Error Which Coming from the server
	this.createServerError=function(objParent, obj, msg) {
		objParent.show();
		obj.attr('class',"errorHeaderStyle");
		obj.text(msg);
	}
	
	this.removeErrors=function () {
		$j('.errorStyle').remove();
		$j('.error').removeClass('error');
		$j('#errorDivHeader').hide();
	}
	
	//function for removing error color while clicking in that field
	this.removeErrorColor = function (col){
		$j(col).click(function() {
			$j(this).closest('p').children('div').hide().end();
			$j(col).removeClass('error');
		});
	}
	
	this.getErrorName = function (error) {
		var errorcode = error.errCode;
		var errorName;
		$j(this.errorCode.error).each(function(index,errordet){  	
			if(errorcode==errordet.errCode) {
				errorName=errordet.errMsg;
				$j(error.params).each(function(indexerror, err) {
					var toReplace='{' + err.parameterName + '}';
					var valuetoReplace = " " + err.value + " ";
					errorName=errorName.replace(toReplace,valuetoReplace);
				});
				return false;
			}
		});
		return errorName;
	}
	
	this.br2nl = function(str) {
		return str.replace(/<br\s*\/?>/mg,"\n");
	}
	this.nl2br = function(str) {
		return str.replace(/[\r\n]/g, "<br/>");
	}
	this.validateNumber = function(p)	{
		$j(p).keydown(function (e) {
			if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
				e.preventDefault();         // Prevent character input
			} else {
				var n = e.keyCode;
				if (!((n == 8) 				// backspace
					|| (n == 46)                // delete
					|| (n >= 35 && n <= 40)     // arrow keys/home/end
					|| (n >= 48 && n <= 57)     // numbers on keyboard
					|| (n >= 96 && n <= 105)   // number on keypad
					|| (n==109 || n==189)	//minus sign
					|| (n==107 )			//plus sign
					|| (n==187 )			//plus sign
					||	(n==32 )				//Space
					||(n==13 || n==9))      // enter and tab	
				) {
					e.preventDefault();     // Prevent character input
				}
			}
		});
	}
	this.validateNumberDot = function(p)	{
		$j(p).keydown(function (e) {
            if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
                e.preventDefault();         // Prevent character input
            } else {
                var n = e.keyCode;
                if (!((n == 8) 				// backspace
                    || (n == 46)                // delete
                    || (n >= 35 && n <= 40)     // arrow keys/home/end
                    || (n >= 48 && n <= 57)     // numbers on keyboard
                    || (n >= 96 && n <= 105)   // number on keypad
					|| (n==190|| n==110)			//dot
					||(n==13 || n==9))      // enter and tab
                ) {
                    e.preventDefault();     // Prevent character input
				} else{
					if(n==190||n==110) {
						var total =$j(p).val();//fixed to 2 decimal points
						(total).toFixed(2); 
					}
				}
			}
        });
	}
	this.setViewTable = function(tableName) {
		tableObj = jQuery(tableName);
		jQuery(tableObj).dataTable( {
			"sPaginationType": "full_numbers",
			"bFilter":false,
			"bInfo":false,
			"bPaginate":false,
			"bSort": false,
			"bRetrieve":true,
			"sDom": '<"top"Hip>'
		});
	}
	this.setDateFormat = function(date){
		newDate = date.split('-');
		newDate=newDate[2]+'-'+newDate[1]+'-'+newDate[0];
		return newDate;
	}
	
	this.getCustomDate = function(date) {
		var month = date.getMonth()+1;
		if(month<10)
			month='0' + month;
		var day = date.getDate();
		if (day<10)
			day='0' + day;
		var year = date.getFullYear();
		var curdate = "" + day + '-' + month + '-' + year + "";
		return curdate;
	}
	this.makeautoComplete=function(controlName,sourceData){
		sourceData.sort();
		$j(controlName).autocomplete({
			source: function( request, response ) {
				var matches = $j.map( sourceData, function(tag) {
					if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
						return tag;
					}
				});
				response(matches);
			}			
		});		
		return;
	}	
	this.makeautoCompleteDefault=function(className,sourceData,parent){
		var target = "."+className;
		if(parent!=undefined)
			target = parent + " ."+className;
		$j(target).autocomplete({
			source: sourceData			
		});		
		return;
	}	
	this.isIndexExists=function(source, index) {
		var foundStatus = false;
		for(ind = 0; ind<source.length;ind++){
			if(source[ind]==index) {
				foundStatus=true;
				return foundStatus;
			}	
		}	
		return foundStatus;
	}
	this.isEqual=function(value1,value2){
		var stat = false;
		if(value1==value2)
			stat = true;
		return stat;
	}
	this.checkRowIdDuplication=function(tableObj, rowId) {
		var status=false;
		if($j(tableObj).dataTable().fnGetData().length>0) {
			var curTable = $j(tableObj + " tr:gt(0)"); //this will not include the header row
			curTable.each(function() {
				var curId=$j(this).attr('id');
				if(rowId==curId) {
					status=true;
					return false;
				}
			});	
		}	
		return status;
	}
}