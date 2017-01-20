function GlobalToolBarProcessor() {
	this.globalService = new GlobalServiceImpl();
	
	this.netlimsAccbranchlist="#leftPaneNetlimsBranch";
	this.netlimsAccbranchorders="#leftPaneNetlimsOrders";
	this.leftPaneTransferredOrders="#leftPaneTransferredOrders";
	this.leftPaneTransferredResults="#leftPaneTransferredResults";
	this.netlimsAccbranchsettings="#leftPaneNetlimsSettings";
	this.newnetlimsBranch="#ribbonNewNetlimsBranch";
	this.netlimsdownLoad="#ribbonNetlimsDownload";
	
	this.netmdAccbranchlist="#leftPaneNetMdBranch";
	this.netmdAccbranchsettings="#leftPaneNetMdSettings";
	this.newnetmdBranch="#ribbonNewNetMdBranch";
	this.netmddownLoad="#ribbonDownload";
	
	this.netrxAccbranchlist="#leftPaneNetRxBranch";
	this.netrxAccbranchsettings="#leftPaneNetrxSettings";
	this.newnetrxBranch="#ribbonNewNetRxBranch";
	this.netrxdownLoad="#ribbonNetRxDownload";
	
	/*
	this.filter = $j('#filter');
	this.filterBench=$j('#filterWorkBench');
	this.ftbContainer=$j('#filterToolBar-Container');
	this.ptbContainer=$j('#pageToolBar-Container');
	 */
}
GlobalToolBarProcessor.prototype.getGlobalService = function() {
	return this.globalService;
}
GlobalToolBarProcessor.prototype.init = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindLeftPaneEvents();
	self.bindRibbonTBEvents();
	
}
GlobalToolBarProcessor.prototype.netMdAccinit = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getnetMdAccRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getnetMdAccLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindnetMdAccLeftPaneEvents();
	self.bindnetMdAccRibbonTBEvents();
	
}
GlobalToolBarProcessor.prototype.netRxAccinit = function() {
	var self=this;
	var globalService = self.getGlobalService();
	var globalTbInfo = globalService.getnetRxAccRibbonTBData();
	self.createGlobalTB(globalTbInfo);
	var leftTBInfo=globalService.getnetRxAccLeftPaneTBData();
	self.createLeftPaneTB(leftTBInfo);
	self.bindnetRxAccLeftPaneEvents();
	self.bindnetRxAccRibbonTBEvents();
	
}
GlobalToolBarProcessor.prototype.createGlobalTB=function(globalTbInfo) {
	var globalTB = new GlobalToolBar(globalTbInfo);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
GlobalToolBarProcessor.prototype.createLeftPaneTB=function(leftTBInfo) {
	var leftpaneTB = new leftpaneToolBar(leftTBInfo.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

GlobalToolBarProcessor.prototype.bindRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetlimsBranch)) {
		$j(self.newnetlimsBranch).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
	var netlimsAccUI;
	if(pageHandler.getHomePage()==constants.NETLIMSACCPAGE)
			{
				netlimsAccUI = pageHandler.getActivePage();
			}
	else
			{
				netlimsAccUI = new netlimsAccountUIStartup();	
			}			
					netlimsAccUI.createBranchModal(obj);
		});
	}
	
	if($j(self.netlimsdownLoad)) {
		$j(self.netlimsdownLoad).die('click').live("click",function() {		
			$j.ajax({
			type: 'GET',
			url: "/youNeverWait/ws/ui/lab/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
			});
		});
	}
	
	
}
GlobalToolBarProcessor.prototype.bindnetMdAccRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetmdBranch)) {
		$j(self.newnetmdBranch).die('click').live("click",function() {		
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetmdAccClassLoaded()!=true){
				var netmdAccClass = new netmdAccClassLoader();
				netmdAccClass.load();
				pageHandler.setnetmdAccClassLoaded(true);
			}
	var netmdAccUI;
	if(pageHandler.getHomePage()==constants.NETMDACCPAGE)
			{
				netmdAccUI = pageHandler.getActivePage();
			}
	else
			{
				netmdAccUI = new netmdAccountUIStartup();	
			}			
					netmdAccUI.createBranchModal(obj);
		});
	}
	
	if($j(self.netmddownLoad)) {
		$j(self.netmddownLoad).die('click').live("click",function() {		
			$j.ajax({
			type: 'GET',
			url: "/youNeverWait/ws/ui/netMd/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
			});
		});
	}
	
	
}
GlobalToolBarProcessor.prototype.bindnetRxAccRibbonTBEvents=function() {
	var self=this;
	if($j(self.newnetrxBranch)) {
		$j(self.newnetrxBranch).die('click').live("click",function() {
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			if(pageHandler.isnetrxAccClassLoaded()!=true){
				var netrxAccClass = new netrxAccClassLoader();
				netrxAccClass.load();
				pageHandler.setnetrxAccClassLoaded(true);
			}
	var netrxAccUI;
	if(pageHandler.getHomePage()==constants.NETRXACCPAGE)
			{
				netrxAccUI = pageHandler.getActivePage();
			}
	else
			{
				netrxAccUI = new netrxAccountUIStartup();	
			}				
					netrxAccUI.createBranchModal(obj);
		});
	}
	
	if($j(self.netrxdownLoad)) {
		$j(self.netrxdownLoad).die('click').live("click",function() {		
			$j.ajax({
			type: 'GET',
			url: "/youNeverWait/ws/ui/netRx/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
			});
		});
	}
	
	
}

GlobalToolBarProcessor.prototype.bindLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netlimsAccbranchlist)) {
		$j(self.netlimsAccbranchlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETLIMSACCPAGE);
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.init();
		});
	}	
	
	if($j(self.netlimsAccbranchorders)) {
		$j(self.netlimsAccbranchorders).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("")
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.branchorderlist(); 
		});
	}	
	
	if($j(self.leftPaneTransferredOrders)) {
		$j(self.leftPaneTransferredOrders).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("")
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.transferorderlist(); 
		});
	}	
	if($j(self.leftPaneTransferredResults)) {
		$j(self.leftPaneTransferredResults).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("")
			if(pageHandler.isnetlimsAccClassLoaded()!=true){
				var netlimsAccClass = new netlimsAccClassLoader();
				netlimsAccClass.load();
				pageHandler.setnetlimsAccClassLoaded(true);
			}
			var netlimsAccUI = new netlimsAccountUIStartup();	
			netlimsAccUI.transferresultlist(); 
		});
	}	
	
	if($j(self.netlimsAccbranchsettings)) {
		$j(self.netlimsAccbranchsettings).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("");
			var adminUI = new SettingsToolBarProcessor();
			adminUI.init(); 
		});
	}	
	
}
GlobalToolBarProcessor.prototype.bindnetMdAccLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netmdAccbranchlist)) {
		$j(self.netmdAccbranchlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETMDACCPAGE);
			if(pageHandler.isnetmdAccClassLoaded()!=true){
				var netmdAccClass = new netmdAccClassLoader();
				netmdAccClass.load();
				pageHandler.setnetmdAccClassLoaded(true);
			}
			var netmdAccUI = new netmdAccountUIStartup();			
			netmdAccUI.init();
		});
	}
	if($j(self.netmdAccbranchsettings)) {
		$j(self.netmdAccbranchsettings).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("");
			var adminUI = new SettingsToolBarProcessor();
			adminUI.netmdAccinit(); 
		});
	}	
	
	
	
}

GlobalToolBarProcessor.prototype.bindnetRxAccLeftPaneEvents=function() {
	var self=this;
	
	if($j(self.netrxAccbranchlist)) {
		$j(self.netrxAccbranchlist).die('click').live("click",function() {	
			var obj=$j(this);
			commonMethodInvoker.removeErrors();
			pageHandler.setHomePage(constants.NETRXACCPAGE);
			if(pageHandler.isnetrxAccClassLoaded()!=true){
				var netrxAccClass = new netrxAccClassLoader();
				netrxAccClass.load();
				pageHandler.setnetrxAccClassLoaded(true);
			}
			var netrxAccUI = new netrxAccountUIStartup();			
			netrxAccUI.init();
		});
	}
	 if($j(self.netrxAccbranchsettings)) {
		$j(self.netrxAccbranchsettings).die('click').live("click",function() {
			 var obj=$j(this);
			commonMethodInvoker.removeErrors();
			$j('#pageToolBar-Container').html("");
			var adminUI = new SettingsToolBarProcessor();
			adminUI.netrxAccinit(); 
		});
	}	 
	
	
	
}