package com.nv.netmdkb.facade;

import java.util.List;
import java.util.Map;

import com.nv.netmdkb.analytic.bl.InformationExtractor;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;


public interface Analyser  {
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet);
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet,Questionnaire questionnaire,CorporateOrganisation corporate);
	public void  addToWrokList( MutualAuthenticable answerSet);
	
	

	
}
