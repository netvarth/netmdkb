package com.nv.netmdkb.pl.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmdkb.pl.dao.QuestionAnswerDao;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.questionnaire.Questionnaire;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/persistence-context.xml",  "classpath:META-INF/analytics-context.xml" })




public class QuestionAnswerDaoImplTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void questionnaireTest() {
		//System.out.println("iniside questionnaireTest");
					QuestionAnswerDao qaDao = (QuestionAnswerDao) applicationContext.getBean("qa.dao");     
					  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
					  String questionnaireId="Survey1";
					  Questionnaire questionnaire =qaDao.getQuestionnaire(questionnaireId);	
					  
					 // System.out.println(questionnaire.getUid());
					  
					  for( Question question:questionnaire.getQuestions()){
						 // System.out.println(question.getKey());
					 }
					  
					 // System.out.println("Robsonclass test :"+questionnaire.getQuestion("robsonclass").getKey() );
	}


	@Test
	public void ifExistTest(){
		
		     QuestionAnswerDao qaDao = (QuestionAnswerDao) applicationContext.getBean("qa.dao");
		     qaDao.exists(1, 8);
	}
		
//	
//	@Test
//	public void saveAnswerSet(){
//		
//		     QuestionAnswerDao qaDao = (QuestionAnswerDao) applicationContext.getBean("qa.dao");
//		     String questionnnaire="Survey1";
//		     NetMdAnswerSet answerSet=  new NetMdAnswerSet();
//		     List<NetMdAnswer> answers=new ArrayList<NetMdAnswer>();
//		       NetMdAnswer ans=new NetMdAnswer();
//		       ans.setAnswer("leo");
//		       ans.setIndex(0);
//		       ans.setQuestionKey("name");
//		       answers.add(ans);
//		       
//		      answerSet.setQuestionRelevantDate(new Date());
//		      answerSet.setAnswers(answers);
//		      answerSet.setBranchId(172);
//		      answerSet.setActionName(ActionEnum.CREATE);
//		      
//		     qaDao.saveAnswerSet(answerSet,questionnnaire);
//	}
	

}
