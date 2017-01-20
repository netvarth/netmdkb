package com.nv.netmdkb.bl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;






import javax.servlet.http.HttpServletRequest;

import com.nv.netmdkb.rs.dto.Credentials;
import com.nv.netmdkb.rs.dto.Graph;
import com.nv.netmdkb.rs.dto.NetMdQuestionAnswerBundle;
import com.nv.netmdkb.rs.dto.SyncResponse;

public interface AnalyticService {

	
	public List<SyncResponse> processQuestionAnswerBatch(NetMdQuestionAnswerBundle questionAnswerBundle);
	public Map<Integer, String> getHospitalList(String questionnareId);
	public Object createReport(Map<String, Object> model,ServletContext context);
	boolean authenticateCredentials(Credentials credentials,
			HttpServletRequest request);
	public Map<Integer, String> getFilterList(String questionnaireId);
	public Map<String, Object> getGraphCoordinates(Graph graph);
	
	
	
}
