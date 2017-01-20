


package com.nv.netmdkb.bl.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.nv.netmdkb.analytic.bl.ActionEnum;
import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.analytic.obstetrics.report.ReportHandler;
import com.nv.netmdkb.bl.service.AnalyticService;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.Analyser;
import com.nv.netmdkb.facade.MutualAuthenticable;
import com.nv.netmdkb.facade.QuestionAnswerFacade;
import com.nv.netmdkb.facade.SecurityFacade;
import com.nv.netmdkb.rs.dto.Credentials;
import com.nv.netmdkb.rs.dto.Graph;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;
import com.nv.netmdkb.rs.dto.NetMdQuestionAnswerBundle;
import com.nv.netmdkb.rs.dto.SyncResponse;


public class AnalyticServiceManager implements AnalyticService {

	public QuestionAnswerFacade answerHandler;

	public ReportHandler reportHandler;

	public SecurityFacade securityHandler;



	Map<String,Analyser> analyserMap = new HashMap<String,Analyser>();

	@Override
	public List<SyncResponse> processQuestionAnswerBatch(
			NetMdQuestionAnswerBundle bundle) {


		Credentials credentials = bundle.getCredentials();

		if (!securityHandler.isAuthenticated(credentials)){

			throw new ServiceException(ErrorCodeEnum.AUTHORIATIONERROR);
		}
		int globalId=0;

		Analyser analyser =analyserMap.get(bundle.getQuestionnaire());
		List<SyncResponse> responseList= new ArrayList<SyncResponse>();


		for(NetMdAnswerSet answerSet:bundle.getNetMdAnswerSetList()){
			SyncResponse response = new SyncResponse();
			try{
				response.setLocalId(answerSet.getAnswerSetLocalId());
				response.setActionName(answerSet.getActionName());
				MutualAuthenticable singlePassedanswerSet= analyser.authenticate(answerSet);

				if (answerSet.getActionName().equals(ActionEnum.CREATE)){
					globalId =answerHandler.saveAnswerSet(answerSet, bundle.getQuestionnaire());
				}

				if (answerSet.getActionName().equals(ActionEnum.UPDATE)){
					globalId=answerHandler.updateAnswerSet(answerSet, bundle.getQuestionnaire());
				}

				if (answerSet.getActionName().equals(ActionEnum.DELETE)){
					globalId=answerHandler.deleteAnswerSet(answerSet,  bundle.getQuestionnaire());
				}

				response.setGlobalId(globalId);


				singlePassedanswerSet.authenticate();

				analyser.addToWrokList(singlePassedanswerSet);

				response.setStatusCode("200");
			}


			catch (ServiceException e){	

				if(e.getError()==ErrorCodeEnum.LOCALANSWERSETEXISTS){
					globalId= answerHandler.exists(answerSet.getAnswerSetLocalId(),answerSet.getBranchId());
					if(globalId>0){
						response.setGlobalId(globalId);
						response.setStatusCode("200");	

					} 	
					else{

						e.printStackTrace();
						response.setStatusCode("500");

						}

				}


			}
			responseList.add(response);    
		}
		return responseList;
	}

	/**
	 * @return the securityHandler
	 */
	public SecurityFacade getSecurityHandler() {
		return securityHandler;
	}

	/**
	 * @param securityHandler the securityHandler to set
	 */
	public void setSecurityHandler(SecurityFacade securityHandler) {
		this.securityHandler = securityHandler;
	}

	@Override
	public Map<Integer, String> getHospitalList(String questionnaireId) {
		Map <Integer,String> map=new HashMap<Integer,String>();
		map=answerHandler.getHospitals(questionnaireId);
		return map;
	}

	@Override
	public Object createReport(Map<String, Object> model, ServletContext context) {
		InputStream inputStream = reportHandler.getJRXml(model, context);
		return reportHandler.createReport(inputStream, model);
	}


	/**
	 * @return the analyserMap
	 */
	public Map<String, Analyser> getAnalyserMap() {
		return analyserMap;
	}


	/**
	 * @param analyserMap the analyserMap to set
	 */
	public void setAnalyserMap(Map<String, Analyser> analyserMap) {
		this.analyserMap = analyserMap;
	}

	/**
	 * @return the answerHandler
	 */
	public QuestionAnswerFacade getAnswerHandler() {
		return answerHandler;
	}


	/**
	 * @param answerHandler the answerHandler to set
	 */
	public void setAnswerHandler(QuestionAnswerFacade answerHandler) {
		this.answerHandler = answerHandler;
	}


	/**
	 * @return the reportHandler
	 */
	public ReportHandler getReportHandler() {
		return reportHandler;
	}


	/**
	 * @param reportHandler the reportHandler to set
	 */
	public void setReportHandler(ReportHandler reportHandler) {
		this.reportHandler = reportHandler;
	}

	@Override
	public boolean authenticateCredentials(
			Credentials credentials,HttpServletRequest request) {

		if (!securityHandler.isAuthenticated(credentials)){
			request.removeAttribute("user");
			securityHandler.getLock(credentials).bindSession(request);;

		}


		return true;
	}

	@Override
	public Map<Integer, String> getFilterList(String questionnaireId) {
		Map <Integer,String> map=new HashMap<Integer,String>();
		List<DataPoint> datapoints = answerHandler.getDataPoints(questionnaireId);
		for(DataPoint datapoint:datapoints){
			map.put(datapoint.getId(),datapoint.getName());
		}
		return map;
	}

	@Override
	public Map<String, Object> getGraphCoordinates(Graph graph) {
		return reportHandler.getGraphCoordinates(graph);
	}

}
