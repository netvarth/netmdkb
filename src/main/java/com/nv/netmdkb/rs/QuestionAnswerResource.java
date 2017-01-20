package com.nv.netmdkb.rs;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.bl.service.AnalyticService;
import com.nv.netmdkb.exception.ServiceExceptionHandler;

import com.nv.netmdkb.rs.dto.CommonSyncResponse;
import com.nv.netmdkb.rs.dto.NetMdQuestionAnswerBundle;
import com.nv.netmdkb.rs.dto.SyncResponse;


@Controller
@RequestMapping("/qa")
public class QuestionAnswerResource extends ServiceExceptionHandler{
	
	AnalyticService qaService;
	
	
	@RequestMapping(value = "/{department}/{questionnareId}", method = RequestMethod.POST)
	@ResponseBody
	public CommonSyncResponse processQA(@RequestBody NetMdQuestionAnswerBundle questionAnswerBundle){
	
	ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	HttpServletRequest req = t.getRequest();
	qaService.authenticateCredentials(questionAnswerBundle.getCredentials(), req);	
	List<SyncResponse> response =qaService.processQuestionAnswerBatch(questionAnswerBundle);
	CommonSyncResponse syncResponse = new CommonSyncResponse();
	syncResponse.setResponses(response);
	return syncResponse;	
	}



	@RequestMapping(value = "/hospitals/{questionnaireId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getNetmd(@PathVariable String questionnaireId){
		Map<Integer,String> response =qaService.getHospitalList(questionnaireId);
	return response;	
	}
	
	
	@RequestMapping(value = "/datapoints/{questionnaireId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getFilterList(@PathVariable String questionnaireId){
		
		Map<Integer,String> response =qaService.getFilterList(questionnaireId);
	
	return response;	
	}
	public AnalyticService getQaService() {
		return qaService;
	}


	
	
	public void setQaService(AnalyticService qaService) {
		this.qaService = qaService;
	}

	
	
	
}
