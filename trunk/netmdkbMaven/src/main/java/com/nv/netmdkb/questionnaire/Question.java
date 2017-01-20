package com.nv.netmdkb.questionnaire;

import com.nv.netmdkb.analytic.bl.dataCluster.ValueType;

public class Question {
	
	
	private int id;
	private String key;
	private String question;
	private ValueType AnswerValueType; 
	private boolean mandatory;
	private boolean indexed;
	
	
	
	/**
	 * @return the indexed
	 */
	public boolean isIndexed() {
		return indexed;
	}
	/**
	 * @param indexed the indexed to set
	 */
	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}
	/**
	 * 
	 */
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValueType getAnswerValueType() {
		return AnswerValueType;
	}
	public void setAnswerValueType(ValueType answerValueType) {
		AnswerValueType = answerValueType;
	}
	
	
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Question(int id){
		this.setId(id);
		this.setQuestion("");
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public int hashCode(){
	
	        int hashcode = 0;
	        hashcode = id*20;
	        hashcode += question.hashCode();
	        return hashcode;
	    }
	     
	   
	public boolean equals(Object obj){
	
	        if (obj instanceof Question) {
	            Question pp = (Question) obj;
	            return (pp.question.equals(this.question) && pp.id == this.id);
	        } else {
	            return false;
	        }
	}
	
    
	
	
	
}
