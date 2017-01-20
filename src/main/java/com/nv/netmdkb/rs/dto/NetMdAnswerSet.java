package com.nv.netmdkb.rs.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.netmdkb.analytic.bl.ActionEnum;
import com.nv.netmdkb.questionnaire.Question;

public class NetMdAnswerSet {
	
	
	private String questionRelevantDate;
	private ActionEnum actionName;
	private int branchId;
    private int answerSetLocalId;	
	private int answerSetGlobalId;
	private List<NetMdAnswer> answers=new ArrayList<NetMdAnswer>();

	public boolean contains(Question question){
		boolean find = false;
		
		for (NetMdAnswer answer: answers){
			if (answer.getQuestionKey().equals(question.getKey())){
			  find=true;	
				
			}
		}
		
		return find;
	}
	
		
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public String getQuestionRelevantDate() {
		return questionRelevantDate;
	}


	public void setQuestionRelevantDate(String questionRelevantDate) {
		this.questionRelevantDate = questionRelevantDate;
	}


	public int getAnswerSetLocalId() {
		return answerSetLocalId;
	}
	public void setAnswerSetLocalId(int answerSetLocalId) {
		this.answerSetLocalId = answerSetLocalId;
	}
	public int getAnswerSetGlobalId() {
		return answerSetGlobalId;
	}
	public void setAnswerSetGlobalId(int answerSetGlobalId) {
		this.answerSetGlobalId = answerSetGlobalId;
	}
	public List<NetMdAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<NetMdAnswer> answers) {
		this.answers = answers;
	}
	public ActionEnum getActionName() {
		return actionName;
	}
	public void setActionName(ActionEnum actionName) {
		this.actionName = actionName;
	}
	
	
	
 

    	
	

}
