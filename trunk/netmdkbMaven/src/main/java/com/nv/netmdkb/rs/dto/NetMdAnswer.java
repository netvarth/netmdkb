package com.nv.netmdkb.rs.dto;



public class NetMdAnswer {
	  private String questionKey;
	  private String answer;
	  private int questionIndex;
	  private int index;

	/**
	 * @return the questionIndex
	 */
	public int getQuestionIndex() {
		return questionIndex;
	}
	/**
	 * @param questionIndex the questionIndex to set
	 */
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}
	/**
	 * @return the questionKey
	 */
	public String getQuestionKey() {
		return questionKey;
	}
	/**
	 * @param questionKey the questionKey to set
	 */
	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
