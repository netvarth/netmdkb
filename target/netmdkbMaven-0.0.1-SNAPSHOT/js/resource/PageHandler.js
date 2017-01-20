function PageHandler() {
	this.getHomePageUrl=function() {
		return "youNeverWait/ws/ui/lab/startUp";
	}
	
	this.create = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$j('#tabs-1').empty().html(page.result);
	}
	
	this.buttons = function(url, parent) {
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.get();
		var buttonsDiv = new buttonsContainer(response);
		$j(parent).empty().html(buttonsDiv.result);
	}
	
	this.createSection = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page= new Container(pageContent);
		return page.result;
	}
	this.getHomePage = function() {
		return this.homePage;
	}
	this.setHomePage = function(homePage) {
		this.homePage = homePage;
	}
	this.getActivePage = function() {
		return this.activePage;
	}
	this.setActivePage = function(activePage) {
		this.activePage = activePage;
	}
	this.isnetlimsAccClassLoaded=function(){
		return this.netlimsAccClassLoaded;
	}
	this.setnetlimsAccClassLoaded=function(status){
		this.netlimsAccClassLoaded=status;
	}
	this.isnetmdAccClassLoaded=function(){
		return this.netmdAccClassLoaded;
	}
	this.setnetmdAccClassLoaded=function(status){
		this.netmdAccClassLoaded=status;
	}
	this.isnetrxAccClassLoaded=function(){
		return this.netrxAccClassLoaded;
	}
	this.setnetrxAccClassLoaded=function(status){
		this.netrxAccClassLoaded=status;
	}
	this.isnetlimsClassLoaded=function(){
		return this.netlimsClassLoaded;
	}
	this.setnetlimsClassLoaded=function(status){
		this.netlimsClassLoaded=status;
	}
	
	this.issuperadminSettingsClassLoaded=function(){
		return this.superadminClassLoaded;
	}
	this.setsuperadminSettingsClassLoaded=function(status){
		this.superadminClassLoaded=status;
	}
	
	this.isnetmdClassLoaded=function(){
		return this.netmdClassLoaded;
	}
	this.setnetmdClassLoaded=function(status){
		this.netmdClassLoaded=status;
	}
	
	this.isnetmdControlClassLoaded=function(){
		return this.netmdControlClassLoaded;
	}
	this.setnetmdControlClassLoaded=function(status){
		this.netmdControlClassLoaded=status;
	}
	
	this.isnetrxClassLoaded=function(){
		return this.netrxClassLoaded;
	}
	this.setnetrxClassLoaded=function(status){
		this.netrxClassLoaded=status;
	}
	this.isnetposClassLoaded=function(){
		return this.netposClassLoaded;
	}
	this.setnetposClassLoaded=function(status){
		this.netposClassLoaded=status;
	}
	this.isorganizationLoaded=function(){
		return this.organizationClassLoaded;
	}
	this.setorganizationClassLoaded=function(status){
		this.organizationClassLoaded=status;
	}
	
	this.isnetlimsAccSettingsClassLoaded=function(){
		return this.netlimsAccSettingsClassLoaded;
	}
	this.setnetlimsAccSettingsClassLoaded=function(status){
		this.netlimsAccSettingsClassLoaded=status;
	}
	this.isnetmdAccSettingsClassLoaded=function(){
		return this.netmdAccSettingsClassLoaded;
	}
	this.setnetmdAccSettingsClassLoaded=function(status){
		this.netmdAccSettingsClassLoaded=status;
	}
	this.isnetrxAccSettingsClassLoaded=function(){
		return this.netrxAccSettingsClassLoaded;
	}
	this.setnetrxAccSettingsClassLoaded=function(status){
		this.netrxAccSettingsClassLoaded=status;
	}
	this.isSpecimenClassLoaded=function(){
		return this.specimenClassLoaded;
	}
	this.setSpecimenClassLoaded=function(status){
		this.specimenClassLoaded=status;
	}
	/* 
	
	
	this.setSettingClassLoaded=function(status){
		this.settingClassLoaded=status;
	}
	
	this.isTestClassLoaded=function(){
		return this.testClassLoaded;
	}
	this.setTestClassLoaded=function(status){
		this.testClassLoaded=status;
	}
	 */
}