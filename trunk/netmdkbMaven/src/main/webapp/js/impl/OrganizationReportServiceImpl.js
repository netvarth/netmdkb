function OrganizationServiceImpl () {
	

}
OrganizationServiceImpl.prototype.getUserDetails=function () {
	ajaxProcessor.setUrl("/analytics/login/user");
	var response=ajaxProcessor.getRequest();
	return response;
}
OrganizationServiceImpl.prototype.getReportDataPoints=function (questionnaireId) {
	ajaxProcessor.setUrl("/analytics/qa/datapoints/"+questionnaireId);
	var response=ajaxProcessor.getRequest();
	return response;
}
OrganizationServiceImpl.prototype.getGraphicalRepresentation=function (questionnaireId,parameters) {
	ajaxProcessor.setUrl("/analytics/report/"+questionnaireId);
	var response=ajaxProcessor.post(parameters);
	return response;
}
OrganizationServiceImpl.prototype.getNetmdBrachesInOrganisation=function (questionnaireId) {
	ajaxProcessor.setUrl("/analytics/qa/hospitals/"+questionnaireId);
	var response=ajaxProcessor.getRequest();
	return response;
}

OrganizationServiceImpl.prototype.logout=function () {
	ajaxProcessor.setUrl("/analytics/login/logout");
	var response=ajaxProcessor.getRequest();
	return response;
}
