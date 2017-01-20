package com.nv.netmdkb.analytic.obstetrics;


import java.util.List;
import com.nv.netmdkb.analytic.bl.ActionEnum;
import com.nv.netmdkb.analytic.bl.AnswerSetValidator;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.corporate.HealthCare;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.Analyser;
import com.nv.netmdkb.facade.MutualAuthenticable;
import com.nv.netmdkb.pl.dao.QuestionAnswerDao;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswer;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;


public class ObstetricsAnalyser  implements Analyser,AnswerSetValidator  {
	public Analyser source;	
	private QuestionAnswerDao questionAnswer;
	private String questionnaireId;
	private Questionnaire questionnaire;
	private CorporateOrganisation corporate;

	protected  void init(){

		this.questionnaire=questionAnswer.getQuestionnaire(questionnaireId);
		this.corporate = questionAnswer.getCorporteByQuesionnaire(questionnaireId);
	}

	@Override
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet) {

		if (! this.validate(answerSet)) throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
		return	source.authenticate(answerSet,questionnaire,corporate);
	}
	@Override
	public void addToWrokList(MutualAuthenticable answerSet) {
		source.addToWrokList(answerSet);
	}

	@Override
	public boolean validate(NetMdAnswerSet answerSet) {

		//for(NetMdAnswerSet answerSet:bundle.getNetMdAnswerSetList()){
		List<NetMdAnswer> netMdAnswers = answerSet.getAnswers();
		/* check for invalid quesions
		 */
		for(NetMdAnswer netMdAnswer :netMdAnswers ){

			questionnaire.getQuestion(netMdAnswer.getQuestionKey());
		}

		/* check for mandatory answers
		 * 
		 */
		if (answerSet.getActionName().equals(ActionEnum.CREATE)){
			for(Question question: questionnaire.mandatoryQuestions()){

				if (!answerSet.contains(question)){
					throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
				}

			}	


			if ((questionAnswer.exists(answerSet.getAnswerSetLocalId(), answerSet.getBranchId()))>0){
				throw new ServiceException(ErrorCodeEnum.LOCALANSWERSETEXISTS);
			}

		}

		if (answerSet.getActionName().equals(ActionEnum.UPDATE) ||answerSet.getActionName().equals(ActionEnum.DELETE)){


			if (answerSet.getAnswerSetGlobalId()==0){
				throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
			}



		}


		try{
			corporate.getHelathCare(answerSet.getBranchId());
		} catch(ServiceException e){
			HealthCare healthCare =questionAnswer.getHealthCareById(answerSet.getBranchId(),corporate.getId());
			if (healthCare !=null){
				corporate.addToAttachedHealthCares(healthCare);
			}else{
				throw new ServiceException(ErrorCodeEnum.INVALIDDATA);	
			}	
		}
		return true;
	}

	/**
	 * @param questionAnswer the questionAnswer to set
	 */
	public void setQuestionAnswer(QuestionAnswerDao questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	/**
	 * @return the source
	 */
	public Analyser getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Analyser source) {
		this.source = source;
	}

	/**
	 * @return the questionnaireId
	 */
	public String getQuestionnaireId() {
		return questionnaireId;
	}

	/**
	 * @param questionnaireId the questionnaireId to set
	 */
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	@Override
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet,
			Questionnaire questionnaire, CorporateOrganisation corporate) {

		throw new ServiceException(ErrorCodeEnum.INVALIDDATA);

	}

}
