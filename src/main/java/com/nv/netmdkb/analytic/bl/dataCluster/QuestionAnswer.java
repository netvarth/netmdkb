package com.nv.netmdkb.analytic.bl.dataCluster;

import com.nv.netmdkb.questionnaire.Question;

public class QuestionAnswer {
	private Question question;
	
	private String answer;
	
	public QuestionAnswer(Question question){
		
	this.question=question;	
	}
	
	
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	
	
	
	
	
	
	

}
