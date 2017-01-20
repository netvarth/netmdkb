/**
 * 
 */
package com.nv.netmdkb.facade;

import java.util.List;
import java.util.Map;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;

/**
 * @author netvarth
 *
 */
public interface QuestionAnswerFacade {
	
	public int saveAnswerSet(NetMdAnswerSet answerSet, String QuestinareUId);

	public int updateAnswerSet(NetMdAnswerSet answerSet, String QuestinareUId);
	
	public int deleteAnswerSet(NetMdAnswerSet answerSet, String QuestinareUId);

	public Map<Integer, String> getHospitals(String questionnaireId);

	public Questionnaire getQuestionnaire(String questionnareId);

	public List<DataPoint> getDataPoints(String questionnaireId);

	public int exists(int localId, int branchId);
	
	
}