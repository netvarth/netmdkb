package com.nv.netmdkb.rs.dto;


import java.util.List;

public class NetMdQuestionAnswerBundle {
	
	private Credentials credentials;
	private String department;
	private String questionnaire;
	List<NetMdAnswerSet> netMdAnswerSetList;
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}
	public List<NetMdAnswerSet> getNetMdAnswerSetList() {
		return netMdAnswerSetList;
	}
	public void setNetMdAnswerSetList(List<NetMdAnswerSet> netMdAnswerSetList) {
		this.netMdAnswerSetList = netMdAnswerSetList;
	}
	

	
}
