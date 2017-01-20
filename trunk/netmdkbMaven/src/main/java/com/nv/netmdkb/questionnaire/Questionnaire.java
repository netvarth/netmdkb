package com.nv.netmdkb.questionnaire;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;

public class Questionnaire {
private int id;	
private CorporateOrganisation corporate;
private String uid;
private String name;
 private List<Question> questions;


public  List<Question> mandatoryQuestions(){
	ArrayList<Question> mandataryList = new ArrayList<Question>();
	for(Question question:questions){
		
		if (question.isMandatory()){
			mandataryList.add(question);
		}
	}
return mandataryList;     	
}
	

public Question getQuestion(String key){

	
	for(Question question:questions){
		
		if (question.getKey().trim().equals(key.trim())){
		    return question;	
		}
		
	}
	
    throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
	
}
	


public Question getQuestion(int questionId) throws ServiceException{
	
for ( Question question:questions){
	
	if (question.getId()==questionId){
		return question;
	}
	
}
throw new ServiceException(ErrorCodeEnum.INVALIDDATA ) 	;
	
}







public int getId() {
	return id;
}




public void setId(int id) {
	this.id = id;
}




public CorporateOrganisation getCorporate() {
	return corporate;
}




public void setCorporate(CorporateOrganisation corporate) {
	this.corporate = corporate;
}




public String getUid() {
	return uid;
}




public void setUid(String uid) {
	this.uid = uid;
}








public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Question> getQuestions() {
	return questions;
}
public void setQuestions(List<Question> questions) {
	this.questions = questions;
}




	
}
