function PageToolBarProcessor() {
	this.create = function(category, url) {
		//Creating Page Tool Bar
		ajaxProcessor.setUrl(url);
		var ptbdata =ajaxProcessor.post();
		var ptbContainer = $j('<div id="' + category + 'PTBContainer"/>');
		var ptb = new PageToolBar(ptbdata);
		$j(ptbContainer).append(ptb.result);
		$j('#pageToolBar-Container').html(ptbContainer);
		return ('"#' + category + 'PTBContainer"');
	}
}