package com.nv.netmdkb.pl.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.pl.dao.AnalyticDao;
import com.nv.netmdkb.questionnaire.Question;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/persistence-context.xml",  "classpath:META-INF/analytics-context.xml" })



public class AnalyticDaoImplTest {

    	@Autowired
	    private ApplicationContext applicationContext;

	
		
		
		
		@Test
		public void getDataPoints() {
			//System.out.println("iniside questionnaireTest");
			AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");     
			String questionnaire="Survey1";
			List<DataPoint> response = analyticDao.getDataPoints(questionnaire);
			//System.out.println("finished");
			//System.out.println("executed");
		}
		
		
		@Test
		public void getAnswerTest(){
			AnalyticDao  analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");  
			Question question = new Question();
			question.setId(129);
			analyticDao.getUltimateAnswer(1, question);
			
			
		}
		
}
