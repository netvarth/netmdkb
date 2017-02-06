
function organizationStartup() {
 this.organizationService = new OrganizationServiceImpl();
 this.current_year;
 this.current_month;
}

organizationStartup.prototype.getOrganizationService = function() {
	return this.organizationService;
}
organizationStartup.prototype.init = function() {
	var self=this;
	var organizationService=self.getOrganizationService();
	var userDetails=organizationService.getUserDetails();
	var userName=userDetails.userName;
	var questionnaireId="Survey1";
	var hospitalList=organizationService.getNetmdBrachesInOrganisation(questionnaireId);
	var filterList=organizationService.getReportDataPoints(questionnaireId);
	var filterCheckboxlist= new FilterCheckbox(filterList,"datapoints");
	var hospitalResult=new FilterCheckbox(hospitalList,"hospitals");
	var hospital=hospitalResult.result();
	var result=filterCheckboxlist.result();
	$j('#tabs-1').append(result);
	$j('#tabs-2').append(hospital);
	var parameters=self.getParametersForGraph();
	var graph=organizationService.getGraphicalRepresentation(questionnaireId,parameters);
	self.setGraphCoordinates(graph);
	$j('#userName').html("Dr. "+userName);
	self.fillMonth("#report #startMonth");
	self.fillMonth("#report #endMonth");
	self.fillYear("#report #startYear");
	self.fillYear("#report #endYear");
	$j("#tabs-1 :input, #tabs-2 :input").each(function(){
	$j(this).attr('checked','checked');
	});
    
	self.bindEvents();
	
}

/*organizationStartup.prototype.getParametersForGraph = function() {
    var cdt = new Date();  
	var month = cdt.getMonth();  
	current_month =month+1;
	current_year =cdt.getFullYear(); 
	var  parameters='{"startMonth":"1","startYear":"'+current_year+'","endYear":"'+current_year+'","endMonth":"'+current_month+'","datapoints":"12"}';
    return parameters;
	}*/
	
	organizationStartup.prototype.getParametersForGraph = function() {
    var cdt = new Date();  
	var month = cdt.getMonth();  
	current_month =month+1;
	current_year =cdt.getFullYear(); 
	var  parameters='{"startMonth":"1","startYear":"'+2013+'","endYear":"'+current_year+'","endMonth":"'+current_month+'","datapoints":"12"}';
    return parameters;
	}

organizationStartup.prototype.bindEvents = function() {
var self=this;
   
   $j("#selectAlldatapoints").click(function () {
          $j('.filterdatapoints').attr('checked', this.checked);
    });
 
    $j(".filterdatapoints").click(function(){
        if($j(".filterdatapoints").length == $j(".filterdatapoints:checked").length) {
            $j("#selectAlldatapoints").attr("checked", "checked");
        } else {
            $j("#selectAlldatapoints").removeAttr("checked");
        }
 
    });
	  $j("#selectAllhospitals").click(function () {
          $j('.filterhospitals').attr('checked', this.checked);
    });
 
    $j(".filterhospitals").click(function(){
        if($j(".filterhospitals").length == $j(".filterhospitals:checked").length) {
            $j("#selectAllhospitals").attr("checked", "checked");
        } else {
            $j("#selectAllhospitals").removeAttr("checked");
        }
 
    });

	$j('#closediv').click(function(){
		$j('#filterContent').hide();
	});

	$j('#btnLogout').click(function(){
     var organizationService=self.getOrganizationService();
	response=organizationService.logout();
	if(response.statusCode=="SUCCESS"){
			location.reload();
		}
	});

$j("#report #show").die('click').live('click',function(){	
	var validate=self.validateMonthandYear();
	if(validate==true){
		$j('#startyearselected').val($j('#startYear').val());
		$j('#startmonthselected').val($j('#startMonth').val());
	    $j('#endyearselected').val($j('#endYear').val());
	    $j('#endmonthselected').val($j('#endMonth').val());
		
 		var report= $j('input:radio[name=report]:checked').val();
		var filterList=self.getFilters();
		$j('#datapoints').val(filterList);
		var hospital=self.getHospitals();
		$j('#hospitalselected').val(hospital);
		 $j("#report").attr('method','post');
		$j("#report").attr('action',"/analytics/report/Survey1/"+report);
		$j("#report").attr('target','_blank');
		$j("#report").submit();  
	}else{
	  event.preventDefault();
      return false; 
     }
	 $j('.filterhospitals').attr('checked', 'checked');
	 $j('.filterdatapoints').attr('checked','checked');
	 $j("#selectAlldatapoints").attr("checked", "checked");
	 $j("#selectAllhospitals").attr("checked", "checked");	
	 $j('#closediv').trigger('click');
	});	
 

}

	
organizationStartup.prototype.getFilters = function() {
	var filterArray=[];
	$j("#tabs-1 input:checked").each(function(){
		var id=$j(this).attr("id");
			if(id=="selectAlldatapoints"){
			  filterArray.push(id);
			  return false;
			}
			else
				filterArray.push(id);
	});
	return filterArray;

}
organizationStartup.prototype.getHospitals = function() {
	var hospital=[];
	$j("#tabs-2 input:checked").each(function(){
		var id=$j(this).attr("id");
			if(id=="selectAllhospitals"){
			  hospital.push(id);
			  return false;
			}
			else
				hospital.push(id);
	});
	return hospital;

}		
organizationStartup.prototype.fillMonth = function(controlObj) {
	var numbers = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
	"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	$j.each(numbers, function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(val+1).html(text) )
            });
}	

organizationStartup.prototype.fillYear = function(controlObj) {
	var firstYear = 2013;
	var lastYear = 2034;
	for(var i =firstYear; i<=lastYear; i++) {
        $j(controlObj).append( $j('<option></option>').val(i).html(i) )
	}	
}	
	
organizationStartup.prototype.validateMonthandYear = function() {

var self=this;
	var bValid=true;
	var startMonth=$j("#report #startMonth").val();
	var endMonth=$j("#report #endMonth").val();
	var startYear=$j("#report #startYear").val();
	var endYear=$j("#report #endYear").val();

	
	if(startMonth.trim()=="select"||endMonth.trim()=="select"||startYear.trim()=="select"||endYear.trim()=="select"){
		self.showTip("Invalid Report Query");
		bValid=false;
		
	}
	else if(startYear==endYear){
		if(startMonth<=endMonth){
			bValid=true;
		}
		else{
		self.showTip("Invalid Report Query");
			bValid=false;
			return;
		}
	}
	 if(startYear>endYear){
		self.showTip("Invalid Report Query");
		bValid=false;
		return;		
	}
	return bValid;
}	

organizationStartup.prototype.showTip=function(message) {
	$j('#BeeperBox .UIBeep_Title').html(message);
	$j("#BeeperBox").show();
	timerId = setTimeout(function () {
	$j("#BeeperBox").hide();
	}, 7000);
}
organizationStartup.prototype.setGraphCoordinates=function(graphCoordinates) {
 var xValues=[];
 var yValues=[];
 var datapoint;
 var ymaxValue=0;

	  $j.each( graphCoordinates, function( key, value ) {
		   datapoint=key;
		  $j.each(value,function(subkey,subvalue){
		   xValues.push(subkey);
		   yValues.push(subvalue);
		  });
	  });
 

  var maxValueInArray = Math.max.apply(Math, yValues);
  var remainder = (maxValueInArray % 5) / 100;
  
	if (remainder === 0) 
		ymaxValue=maxValueInArray;
	else 
	var	a = Math.ceil(maxValueInArray/5)*5;ymaxValue=a;
	var data=yValues;
 
 

        var bar = new RGraph.Bar('myCanvas', data)
            .set('labels',xValues )
            .set('gutter.left', 35)
			.set('ymin', 0)
			 .set('hmargin', 30)
			.set('ymax',ymaxValue)
			.set('title'," No of Caesareans - 2017")
			.set('title.color',"Green")
			.set('title.font',"Times New Roman,Georgia,Serif")
			.set('title.yaxis',"No of Patients")
			.set('title.xaxis.size',10)
			.set('title.yaxis.size',10)
			.set('title.xaxis.pos',0.165)
			.set('bevel',false)
		    .draw();  

}