package com.nv.netmdkb.rs;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.nv.netmdkb.bl.service.AnalyticService;
import com.nv.netmdkb.rs.dto.Graph;
import com.nv.netmdkb.rs.view.PDFReportView;




@Controller
@RequestMapping("report")
public class ReportResource  {
	
	AnalyticService analyticService;
	
	private PDFReportView view;
	
	/**
	 * @return the analyticService
	 */
	public AnalyticService getAnalyticService() {
		return analyticService;
	}


	/**
	 * @param analyticService the analyticService to set
	 */
	public void setAnalyticService(AnalyticService analyticService) {
		this.analyticService = analyticService;
	}


	/**
	 * @return the view
	 */
	public PDFReportView getView() {
		return view;
	}


	/**
	 * @param view the view to set
	 */
	public void setView(PDFReportView view) {
		this.view = view;
	}


	@RequestMapping(value ="/{questionnaireId}",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getGraphics(@RequestBody Graph graph ){
	  Map<String,Object> model = new HashMap<String, Object>();
	  model= analyticService.getGraphCoordinates(graph);
		return model;
	}
	

	@RequestMapping(value ="/{questionnaireId}/{reportName}",method=RequestMethod.POST)
	public ModelAndView generate(@PathVariable String reportName) {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		ServletContext context =	request.getSession().getServletContext();	
    
		Map<String, Object> model = new HashMap<String, Object>();
	    model.put("reportName", reportName);
		model.put("startMonth",request.getParameter("startmonthselected"));
		model.put("startYear", request.getParameter("startyearselected"));
		model.put("endMonth", request.getParameter("endmonthselected"));
		model.put("endYear", request.getParameter("endyearselected"));
		model.put("hospital", request.getParameter("hospitalselected"));
		model.put("datapoints", request.getParameter("datapoints"));
	    Object jPrint =analyticService.createReport(model, context);
	    model.put("jPrint",jPrint );		
	    return new ModelAndView(view, model);
	}
		
		
	
		
	}

	
	
	


