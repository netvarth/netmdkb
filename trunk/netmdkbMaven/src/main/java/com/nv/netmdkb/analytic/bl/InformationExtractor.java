package com.nv.netmdkb.analytic.bl;

import com.nv.netmdkb.questionnaire.Question;




public interface InformationExtractor {
	
	
	public Question[] getRelatedQuestions();
	public void extractInformation(AuthenticableAnswerSet answerList);
    public AuthenticableAnswerSet enhanceAnswerSet(AuthenticableAnswerSet answerSet);
	

}
