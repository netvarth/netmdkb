/**
 * 
 */
package com.nv.netmdkb.questionAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.corporate.HealthCare;
import com.nv.netmdkb.facade.QuestionAnswerFacade;
import com.nv.netmdkb.pl.dao.QuestionAnswerDao;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;


/**
 * @author net varth
 *
 */
public class QuestionAnswerHandler implements QuestionAnswerFacade{
 private QuestionAnswerDao  answerDao;
 
	/**
 * @return the answerDao
 */
public QuestionAnswerDao getAnswerDao() {
	return answerDao;
}

/**
 * @param answerDao the answerDao to set
 */
public void setAnswerDao(QuestionAnswerDao answerDao) {
	this.answerDao = answerDao;
}

	public Map<Integer, String> getHospitals(String questionnaireId){
		
		CorporateOrganisation corporate=answerDao.getCorporteByQuesionnaire(questionnaireId);
		Map<Integer,String> map=new HashMap<Integer, String>();
		for(HealthCare healthCare :corporate.attachedHelathCares() ){
			map.put(healthCare.getId(),healthCare.getName());
		}
		
		return map;
	}

	@Override
	public int saveAnswerSet(NetMdAnswerSet answerSet,
			String questinnareId) {
		
		int globalId =answerDao.saveAnswerSet(answerSet, questinnareId);
	return globalId;
	}

	@Override
	public int updateAnswerSet(NetMdAnswerSet answerSet,
			String questionnareId) {
		int globalId=answerDao.updateAnswerSet(answerSet, questionnareId);
	return globalId;	
	}

	@Override
	public int deleteAnswerSet(NetMdAnswerSet answerSet,
			String questinareUId) {
		int globalId =answerDao.deleteAnswerSet(answerSet, questinareUId);
	return globalId;	
	}

	@Override
	public Questionnaire  getQuestionnaire(String questionnareId) {
		Questionnaire questionnaire=new Questionnaire();
		questionnaire=	answerDao.getQuestionnaire(questionnareId);
		return questionnaire;
		
	}

	@Override
	public List<DataPoint> getDataPoints(String questionnaireId) {
		List<DataPoint> datapoints = answerDao.getDataPoints(questionnaireId);
		return datapoints;
	}

	@Override
	public int exists(int localId, int branchId) {
		int localAnsSetId=answerDao.exists(localId, branchId);
		return localAnsSetId;
	}


}
