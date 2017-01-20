package com.nv.netmdkb.analytic.bl;

import java.util.Date;

import com.nv.netmdkb.questionnaire.Question;



public class AnalyticAnswer {
	

	private Question question;
	private String answer;
	private DataNovelty dataNovelty;
	private Date questionRelevantDate;
	private int index;
	

	
	

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
	public DataNovelty getDataNovelty() {
		return dataNovelty;
	}
	public void setDataNovelty(DataNovelty dataNovelty) {
		this.dataNovelty = dataNovelty;
	}
	
	
	public AnalyticAnswer() {
		super();
	}
	public Date getQuestionRelevantDate() {
		return questionRelevantDate;
	}
	public void setQuestionRelevantDate(Date questionRelevantDate) {
		this.questionRelevantDate = questionRelevantDate;
	}
	
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	

	
	
}
