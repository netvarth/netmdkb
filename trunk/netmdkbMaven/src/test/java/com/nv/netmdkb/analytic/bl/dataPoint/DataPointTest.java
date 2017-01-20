package com.nv.netmdkb.analytic.bl.dataPoint;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmdkb.analytic.bl.QuestionIdentifier;
import com.nv.netmdkb.analytic.bl.dataCluster.ValueType;
import com.nv.netmdkb.pl.dao.AnalyticDao;
import com.nv.netmdkb.questionnaire.Question;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/context.xml",  "classpath:META-INF/analytics-context.xml" })




public class DataPointTest {


	  @Autowired
      private ApplicationContext applicationContext;
	  /*ApgarScore
      giving apgarScore at 1 min and apgarScore at 5 min  values as 'Unknown'
	  expected result is {1.1.1=0, 2.2.2=1, 2.2.1=0, 1.1.2=1} 
	 */
	  @Test
      public void apgarTest1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="ApgarScore";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(129);
		  question1.setKey("baby1Apgaronemin");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(130);
		  question2.setKey("baby1Apgarfivemin");
		  question2.setAnswerValueType(ValueType.INTEGER);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  map.put(new QuestionIdentifier(question2.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("2.2.2",1);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("1.1.2",1);
	       // assertTrue(valueMap.equals(expectedMap));
		  Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }
	  
	  
	  
	  /*ApgarScore
      giving apgarScore at 1 min and apgarScore at 5 min  values as '0','2' ie first value <1 and second value <5
	  Expected result is {1.1.1=1, 2.2.2=0, 2.2.1=1, 1.1.2=0}
      */
	  @Test
      public void apgarTest2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="ApgarScore";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(129);
		  question1.setKey("baby1Apgaronemin");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(130);
		  question2.setKey("baby1Apgarfivemin");
		  question2.setAnswerValueType(ValueType.INTEGER);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question1.getId()),"0");
		  map.put(new QuestionIdentifier(question2.getId()),"2");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",1);
		  expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",1);
	      expectedMap.put("1.1.2",0);
	        assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap, valueMap);	
		  
	  }
	  
	  
	  /*ApgarScore
     giving apgarScore at 1 min and apgarScore at 5 min  values as '1','5' ie first value ! <1 and second value !<5
	 Expected result is {1.1.1=0, 2.2.2=0, 2.2.1=0, 1.1.2=0} 
	 */
	  @Test
      public void apgarTest3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="ApgarScore";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(129);
		  question1.setKey("baby1Apgaronemin");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(130);
		  question2.setKey("baby1Apgarfivemin");
		  question2.setAnswerValueType(ValueType.INTEGER);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question1.getId()),"1");
		  map.put(new QuestionIdentifier(question2.getId()),"5");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",1);
	      expectedMap.put("1.1.2",0);
	      assertTrue(valueMap.equals(expectedMap));
		  Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }
	  
	
	  
	  //birthweight baby weight <1500
	//expectedResult {1.1.1=1, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
		 
	  @Test
      public void birthweightTest1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(127);
		  question1.setKey("babyGender");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"male");
		  Question question2= new Question();
		  question2.setId(128);
		  question2.setKey("babyWeight");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"1499");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",1);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("5.5.4",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("2.2.4",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("4.4.4",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("1.1.4",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	      expectedMap.put("3.3.4",0);
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  
	  
	  //birthweight baby weight 1500 AND 2499
	//expectedResult {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
		 
	  @Test
      public void birthweightTest2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(127);
		  question1.setKey("babyGender");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"female");
		  Question question2= new Question();
		  question2.setId(128);
		  question2.setKey("babyWeight");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"2499");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  Map<String, Integer> expectedMap = new HashMap<>();
	  	   System.out.println(valueMap);   
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("1.1.4",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("5.5.4",0);
	      expectedMap.put("2.2.4",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",1);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.4",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  
	  //birthweight baby weight BETWEEN 2500 AND 3499
	//expectedResult {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=1}
		 
	  @Test
      public void birthweightTest3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(127);
		  question1.setKey("babyGender");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"male");
		  Question question2= new Question();
		  question2.setId(128);
		  question2.setKey("babyWeight");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"2500");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("1.1.4",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("5.5.4",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.4",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("4.4.4",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",1);
	      expectedMap.put("3.3.4",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  //birthweight baby weight BETWEEN 2500 AND 3499
	//expectedResult{1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=1, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
		 
	  @Test
      public void birthweightTest4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(127);
		  question1.setKey("babyGender");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"male");
		  Question question2= new Question();
		  question2.setId(128);
		  question2.setKey("babyWeight");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"3550");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);    
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("1.1.4",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("5.5.4",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.4",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.4",0);
	      expectedMap.put("4.4.1",1);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	      expectedMap.put("3.3.4",0);    
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //birthweight baby weight Unknown
	//expectedResul {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=1, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void birthweightTest5(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(127);
		  question1.setKey("babyGender");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"male");
		  Question question2= new Question();
		  question2.setId(128);
		  question2.setKey("babyWeight");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		  expectedMap.put("1.1.4",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("2.2.4",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("4.4.4",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",1);
	      expectedMap.put("5.5.4",0);
	      expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //BloodGroup  A -ve
	  //expected {1.1.1=1, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodGroupTest1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodGroup";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(9);
		  question1.setKey("bloodgroup");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"A");
		  Question question2= new Question();
		  question2.setId(10);
		  question2.setKey("rh");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"-ve");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",1);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  


	  @Test
	  //BloodGroup B +ve
	  //expected {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodGroupTest2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodGroup";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(9);
		  question1.setKey("bloodgroup");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"B");
		  Question question2= new Question();
		  question2.setId(10);
		  question2.setKey("rh");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"+ve");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",1);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //BloodGroup AB  Unknown
	  //expected {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=1, 3.3.2=0, 3.3.1=0}
      public void bloodGroupTest3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodGroup";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(9);
		  question1.setKey("bloodgroup");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"AB");
		  Question question2= new Question();
		  question2.setId(10);
		  question2.setKey("rh");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",1);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  @Test
	  //BloodGroup O +ve
	  //expected {1.1.1=0, 5.5.3=0, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=1, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodGroupTest4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodGroup";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(9);
		  question1.setKey("bloodgroup");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"O");
		  Question question2= new Question();
		  question2.setId(10);
		  question2.setKey("rh");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"+ve");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("5.5.3",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",1);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  @Test
	  //BloodGroup Unknown  Unknown
	  //expected {1.1.1=0, 5.5.3=1, 2.2.3=0, 5.5.2=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 5.5.1=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodGroupTest5(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodGroup";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(9);
		  question1.setKey("bloodgroup");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Question question2= new Question();
		  question2.setId(10);
		  question2.setKey("rh");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("5.5.3",1);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("5.5.2",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("5.5.1",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //BloodLoss <500
	  //expected {1.1.1=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=1, 1.1.3=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodLossTest1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodLoss";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(86);
		  question1.setKey("bloodLoss");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"459");
		  Question question2= new Question();
		  question2.setId(57);
		  question2.setKey("outCome");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"CS");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	     /* expectedMap.put("1.1.2",1);*/
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	      
	      
	        assertTrue(valueMap.equals(expectedMap));
		//	Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  @Test
	  //BloodLoss  between 500 and 999
	  //expected {1.1.1=0, 2.2.3=0, 2.2.2=0, 2.2.1=1, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodLossTest2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodLoss";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(86);
		  question1.setKey("bloodLoss");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"999");
		  Question question2= new Question();
		  question2.setId(57);
		  question2.setKey("outCome");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	    /*  expectedMap.put("2.2.1",1);*/
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //BloodLoss   blood Loss >=1000
	  //expected {1.1.1=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 3.3.3=0, 3.3.2=0, 3.3.1=1}
      public void bloodLossTest3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodLoss";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(86);
		  question1.setKey("bloodLoss");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"1001");
		  Question question2= new Question();
		  question2.setId(57);
		  question2.setKey("outCome");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      /*expectedMap.put("3.3.1",1);*/
	      expectedMap.put("3.3.1",0);    
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //BloodLoss   blood Loss Unknown
	  //expected {1.1.1=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 4.4.1=1, 4.4.3=0, 4.4.2=0, 1.1.2=0, 1.1.3=0, 3.3.3=0, 3.3.2=0, 3.3.1=0}
      public void bloodLossTest4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BloodLoss";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(86);
		  question1.setKey("bloodLoss");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Question question2= new Question();
		  question2.setId(57);
		  question2.setKey("outCome");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  map.put(new QuestionIdentifier(question2.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.3",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //bodyMassndex less than 20
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
      public void bodyMassIndex1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BodyMassIndex";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(8);
		  question1.setKey("bodymassIndex");
		  question1.setAnswerValueType(ValueType.FLOAT);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"18");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("7.5",0);
		  expectedMap.put("6.6",0);
		  expectedMap.put("5.5",0);
	      expectedMap.put("4.4",0);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",1);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  @Test
	  //bodyMassndex BETWEEN 20 AND 24 
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=1, 1.1=0}
      public void bodyMassIndex2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BodyMassIndex";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(8);
		  question1.setKey("bodymassIndex");
		  question1.setAnswerValueType(ValueType.FLOAT);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"23");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("7.5",0);
		  expectedMap.put("6.6",0);
		  expectedMap.put("5.5",0);
	      expectedMap.put("4.4",0);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",1);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //bodyMassndex BETWEEN 25 AND 29
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=1, 2.2=0, 1.1=0}
      public void bodyMassIndex3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BodyMassIndex";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(8);
		  question1.setKey("bodymassIndex");
		  question1.setAnswerValueType(ValueType.FLOAT);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"28");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("7.5",0);
		  expectedMap.put("6.6",0);
		  expectedMap.put("5.5",0);
	      expectedMap.put("4.4",0);
	      expectedMap.put("3.3",1);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //bodyMassndex less than BETWEEN 30 AND 34 
	  //expected result{5.5=0, 6.6=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
      public void bodyMassIndex4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BodyMassIndex";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(8);
		  question1.setKey("bodymassIndex");
		  question1.setAnswerValueType(ValueType.FLOAT);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"30");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("7.5",0);
		  expectedMap.put("6.6",0);
		  expectedMap.put("5.5",0);
	      expectedMap.put("4.4",1);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //bodyMassndex Unknown
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
      public void bodyMassIndex5(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BodyMassIndex";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(8);
		  question1.setKey("bodymassIndex");
		  question1.setAnswerValueType(ValueType.FLOAT);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("7.5",1);
		  expectedMap.put("6.6",0);
		  expectedMap.put("5.5",0);
	      expectedMap.put("4.4",0);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //bodyMassndex Unknown
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
      public void bookedStatistics1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BookedStatistics";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(2);
		  question1.setKey("bookedUnbooked");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"booked");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
	     /* expectedMap.put("4.4",0);*/
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",1);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  
	  @Test
	  //BookedStatistics Unbooked
	  //expected result{ 4.4=0, 3.3=0, 2.2=1, 1.1=0}
      public void bookedStatistics2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BookedStatistics";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(2);
		  question1.setKey("bookedUnbooked");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"unbooked");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
	   /*   expectedMap.put("4.4",0);*/
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",1);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //BookedStatistics Referred
	  //expected result{4.4=0, 3.3=1, 2.2=0, 1.1=0}
      public void bookedStatistics3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BookedStatistics";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(2);
		  question1.setKey("bookedUnbooked");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Referred");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
	   /*   expectedMap.put("4.4",0);*/
	      expectedMap.put("3.3",1);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //BookedStatistics Unknown
	  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
      public void bookedStatistics4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BookedStatistics";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(2);
		  question1.setKey("bookedUnbooked");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
	    /*  expectedMap.put("4.4",1);*/
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
	       // assertTrue(valueMap.equals(expectedMap));
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  
	  
	  @Test
	  //Episiotomy Midline Episiotomy
	  //expected result{3.3=0, 2.2=0, 1.1=1}
      public void Episiotomy1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Episiotomy";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(63);
		  question1.setKey("episiotomy");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Midline Episiotomy");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("4.4",0);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",1);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //Episiotomy Medio-Lateral Episiotomy
	  //expected result{3.3=0, 2.2=1, 1.1=0}
      public void Episiotomy2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Episiotomy";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(63);
		  question1.setKey("episiotomy");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Medio-Lateral Episiotomy");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("4.4",0);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",1);
	      expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //Episiotomy Medio-Lateral Episiotomy
	  //expected result{3.3=1, 2.2=0, 1.1=0}
      public void Episiotomy3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Episiotomy";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(63);
		  question1.setKey("episiotomy");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("4.4",1);
	      expectedMap.put("3.3",0);
	      expectedMap.put("2.2",0);
	      expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //Fetal Complications fetalStillbirth yes
	  //expected result{1.1.1=1, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0}
      public void FetalComplications1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="FetalComplications";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(161);
		  question1.setKey("fetalstillbirth");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"yes");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",1);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	  //Fetal Complications fetal NeonatalDeath unknown
	  //expected result{1.1.1=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0}
      public void FetalComplications2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="FetalComplications";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(162);
		  question1.setKey("fetalNeonatalDeath");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.2",1);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  @Test
	//Fetal Complications fetalCongAnomali yes
	  //expected result{1.1.1=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.2=0, 3.3.2=0, 3.3.1=1, 1.1.2=0}
      public void FetalComplications3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="FetalComplications";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(159);
		  question1.setKey("fetalCongAnomali");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"Yes");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("4.4.1",0);
	      expectedMap.put("4.4.2",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",1);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //Gestation between 20 and 27
	  //expected result{4.4=0,3.3=0, 2.2=0, 1.1=1}
      public void gestation1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Gestation";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(29);
		  question1.setKey("gestationWeek");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"22");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",0);
		    expectedMap.put("2.2",0);
		    expectedMap.put("1.1",1);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //Gestation in between 28 and 36
	  //expected result{4.4=0,3.3=0, 2.2=1, 1.1=0}
      public void gestation2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Gestation";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(29);
		  question1.setKey("gestationWeek");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"36");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",0);
		    expectedMap.put("2.2",1);
		    expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  @Test
	  //Gestation >=37
	  //expected result{4.4=0,3.3=1, 2.2=0, 1.1=0}
      public void gestation3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Gestation";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(29);
		  question1.setKey("gestationWeek");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"38");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",1);
		    expectedMap.put("2.2",0);
		    expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  
	  
	  
	  @Test
	  //Induction Indicated Induction and vag del <24
	  //expected result{1.1.1=1, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 3.3.4=0, 3.3.3=0, 3.3.2=0, 3.3.1=0, 1.1.4=0, 1.1.2=0, 1.1.3=0}
      public void Induction1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Induction";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(53);
		  question1.setKey("induction");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(58);
		  question2.setKey("indDelInterval");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  Question question3= new Question();
		  question3.setId(57);
		  question3.setKey("outCome");
		  question3.setAnswerValueType(ValueType.STRING);
		  question3.setMandatory(false);
		  question3.setQuestion("question3");
		  map.put(new QuestionIdentifier(question1.getId()),"Indicated induction");
		  map.put(new QuestionIdentifier(question2.getId()),"12");
		  map.put(new QuestionIdentifier(question3.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		 /* expectedMap.put("2.2.4",0);*/
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	     /* expectedMap.put("1.1.4",0);*/
	     /* expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);*/
	      //  assertTrue(valueMap.equals(expectedMap));
		 Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }
	  
	  
	  @Test
	  //Induction Indicated Induction and vag del >24
	  //expected result{1.1.1=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 3.3.4=0, 3.3.3=0, 3.3.2=0, 3.3.1=0, 1.1.4=0, 1.1.2=1, 1.1.3=0}
      public void Induction2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Induction";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(53);
		  question1.setKey("induction");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(58);
		  question2.setKey("indDelInterval");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  Question question3= new Question();
		  question3.setId(57);
		  question3.setKey("outCome");
		  question3.setAnswerValueType(ValueType.STRING);
		  question3.setMandatory(false);
		  question3.setQuestion("question3");
		  map.put(new QuestionIdentifier(question1.getId()),"Indicated induction");
		  map.put(new QuestionIdentifier(question2.getId()),"25");
		  map.put(new QuestionIdentifier(question3.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		 /* expectedMap.put("2.2.4",0);*/
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	      /*expectedMap.put("1.1.4",0);*/
	     /* expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);*/
	      //  assertTrue(valueMap.equals(expectedMap));
		 Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }
	  
	  @Test
	  //Induction Elective Induction and outcome cs
	  //expected result{1.1.1=0, 2.2.4=0, 2.2.3=1, 2.2.2=0, 2.2.1=0, 3.3.4=0, 3.3.3=0, 3.3.2=0, 3.3.1=0, 1.1.4=0, 1.1.2=0, 1.1.3=0}
      public void Induction3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Induction";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(53);
		  question1.setKey("induction");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(58);
		  question2.setKey("indDelInterval");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  Question question3= new Question();
		  question3.setId(57);
		  question3.setKey("outCome");
		  question3.setAnswerValueType(ValueType.STRING);
		  question3.setMandatory(false);
		  question3.setQuestion("question3");
		  map.put(new QuestionIdentifier(question1.getId()),"Elective Induction");
		  map.put(new QuestionIdentifier(question2.getId()),"25");
		  map.put(new QuestionIdentifier(question3.getId()),"CS");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		/*  expectedMap.put("2.2.4",0);*/
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	   /*   expectedMap.put("1.1.4",0);*/
	   /*   expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",0);
	      expectedMap.put("3.3.1",0);*/
	      //  assertTrue(valueMap.equals(expectedMap));
		 Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }
	  @Test
	  //Induction unknown and outcome vaginal and indDelInterval >24
	  //expected result{1.1.1=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 3.3.4=0, 3.3.3=0, 3.3.2=1, 3.3.1=0, 1.1.4=0, 1.1.2=0, 1.1.3=0}
      public void Induction4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="Induction";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(53);
		  question1.setKey("induction");
		  question1.setAnswerValueType(ValueType.STRING);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  Question question2= new Question();
		  question2.setId(58);
		  question2.setKey("indDelInterval");
		  question2.setAnswerValueType(ValueType.FLOAT);
		  question2.setMandatory(false);
		  question2.setQuestion("question2");
		  Question question3= new Question();
		  question3.setId(57);
		  question3.setKey("outCome");
		  question3.setAnswerValueType(ValueType.STRING);
		  question3.setMandatory(false);
		  question3.setQuestion("question3");
		  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
		  map.put(new QuestionIdentifier(question2.getId()),"25");
		  map.put(new QuestionIdentifier(question3.getId()),"Vaginal");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  HashMap<String, Integer> expectedMap = new HashMap<>();
		  expectedMap.put("1.1.1",0);
		/*  expectedMap.put("2.2.4",0);*/
	      expectedMap.put("2.2.3",0);
	      expectedMap.put("2.2.2",0);
	      expectedMap.put("2.2.1",0);
	      expectedMap.put("1.1.2",0);
	      expectedMap.put("1.1.3",0);
	     /* expectedMap.put("1.1.4",0);*/
	     /* expectedMap.put("3.3.4",0);
	      expectedMap.put("3.3.3",0);
	      expectedMap.put("3.3.2",1);
	      expectedMap.put("3.3.1",0);*/
	      //  assertTrue(valueMap.equals(expectedMap));
		 Assert.assertEquals(expectedMap, valueMap);	
  
		  
	  }

	  
	  
	  @Test
	  //IntraVenusFluid amtfluids less than 1000
	  //expected result{4.4=0,3.3=0, 2.2=0, 1.1=1}
      public void IntraVenusFluid1(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="IntraVenusFluid";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(80);
		  question1.setKey("amtFluids");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"856");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",0);
		    expectedMap.put("2.2",0);
		    expectedMap.put("1.1",1);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  

	  @Test
	  //IntraVenusFluid amtFluids  BETWEEN 1000 and 3000
	  //expected result{4.4=0,3.3=0, 2.2=1, 1.1=0}
      public void IntraVenusFluid2(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="IntraVenusFluid";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(80);
		  question1.setKey("amtFluids");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"2000");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",0);
		    expectedMap.put("2.2",1);
		    expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	  
	  

	  @Test
	  //IntraVenusFluid amtFluids > 3000
	  //expected result{4.4=0,3.3=1, 2.2=0, 1.1=0}
      public void IntraVenusFluid3(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="IntraVenusFluid";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(80);
		  question1.setKey("amtFluids");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"3001");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",0);
		    expectedMap.put("3.3",1);
		    expectedMap.put("2.2",0);
		    expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }
	 
	  
	  @Test
	  //IntraVenusFluid amtFluids unknown
	  //expected result{4.4=1,3.3=0, 2.2=0, 1.1=0}
      public void IntraVenusFluid4(){ 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="IntraVenusFluid";
          DataPoint dp=analyticDao.getDataPoint(datapoint);
		  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
		  Question question1 = new Question();
		  question1.setId(80);
		  question1.setKey("amtFluids");
		  question1.setAnswerValueType(ValueType.INTEGER);
		  question1.setQuestion("question1");
		  question1.setMandatory(false);
		  map.put(new QuestionIdentifier(question1.getId()),"unknown");
		  Map<String,Integer> valueMap = dp.getValueMap(map);
		  System.out.println(valueMap);
		  Map<String, Integer> expectedMap = new HashMap<>();
		    expectedMap.put("4.4",1);
		    expectedMap.put("3.3",0);
		    expectedMap.put("2.2",0);
		    expectedMap.put("1.1",0);
	   	      
			Assert.assertEquals(expectedMap,valueMap);	 
  
		  
	  }

	  //MaternalAge between 20 and 24
		//expectedResult {5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=1, 1.1=0}
		  @Test
	      public void maternalAgeTest1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalAge";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(4);
			  question1.setKey("age");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"24");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("6.6",0);
		      expectedMap.put("1.1",0);
		     assertTrue(valueMap.equals(expectedMap));
			 Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  //MaternalAge 35
		  //expected result{5.5=0, 6.6=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
		  @Test
	      public void maternalAgeTest2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalAge";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(4);
			  question1.setKey("age");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
		
			  map.put(new QuestionIdentifier(question1.getId()),"35");
		
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  HashMap<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1",0);
			  expectedMap.put("2.2",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("4.4",1);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      assertTrue(valueMap.equals(expectedMap));
		      Assert.assertEquals(expectedMap, valueMap);	 
	  
			  
		  }
		  //MaternalAge 25 to 29
		  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=1, 2.2=0, 1.1=0}
		  @Test
	      public void maternalAgeTest3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalAge";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(4);
			  question1.setKey("age");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
		
			  map.put(new QuestionIdentifier(question1.getId()),"25");
		
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  HashMap<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1",0);
			  expectedMap.put("2.2",0);
		      expectedMap.put("3.3",1);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		   
		      assertTrue(valueMap.equals(expectedMap));
		      Assert.assertEquals(expectedMap, valueMap);	 
	  
			  
		  }
		  //MaternalAge less than 20
		  //expected result{5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
		  @Test
	      public void maternalAgeTest4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalAge";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(4);
			  question1.setKey("age");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
		
			  map.put(new QuestionIdentifier(question1.getId()),"18");
		
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  HashMap<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1",1);
			  expectedMap.put("2.2",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		   
		      assertTrue(valueMap.equals(expectedMap));
		      Assert.assertEquals(expectedMap, valueMap);	 
	  
			  
		  }
		  
		  @Test
		  //Maternal Complications anemia yes
		  //expected {1.1.1=1, 2.2.3=0, 2.2.2=0, 2.2.1=0, 4.4.1=0, 4.4.3=0, 3.3.3=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0, 1.1.3=0}
	      public void maternalComlications1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalComplications";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(31);
			  question1.setKey("anemia");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"yes");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",1);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.3",0);
		      
		      
		      
		   
		      
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  @Test
		  //Maternal Complications  diabetesMellitus NO
		  //expected {1.1.1=0, 2.2.3=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.3=0, 3.3.3=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0, 1.1.3=0}
	      public void maternalComlications2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalComplications";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(33);
			  question1.setKey("diabetesMellitus");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"no");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		     /* expectedMap.put("2.2.2",1);*/
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("3.3.3",0);
		      /*expectedMap.put("3.3.2",0);*/
		      expectedMap.put("3.3.2",1);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.3",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  @Test
		  //Maternal Complications   heartDisease yes
		  //expected {1.1.1=0, 2.2.3=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.3=0, 3.3.3=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0, 1.1.3=0}
	      public void maternalComlications3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalComplications";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(34);
			  question1.setKey("heartDisease");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"yes");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		    /*  expectedMap.put("3.3.1",1);*/
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("4.4.1",1);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.3",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  @Test
		  //Maternal Complications   severeHyper unknown
		  //expected {1.1.1=0, 2.2.3=0, 2.2.2=1, 2.2.1=0, 4.4.1=0, 4.4.3=0, 3.3.3=0, 4.4.2=0, 3.3.2=0, 3.3.1=0, 1.1.2=0, 1.1.3=0}
	      public void maternalComlications4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalComplications";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(39);
			  question1.setKey("severeHyper");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"unknown");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.3",1);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  
		  @Test
		  //MaternalHeight less than 1.45 meter
		  //expected result{5.5=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void maternalHeight1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalHeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(6);
			  question1.setKey("height");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1.44");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	 
	  
			  
		  }
		  
		  
		  @Test
		  //MaternalHeight less  BETWEEN 1.45 AND 1.49 meter
		  //expected result{5.5=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void maternalHeight2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalHeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(6);
			  question1.setKey("height");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1.48");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //MaternalHeight less  BETWEEN 1.50 AND 1.70 meters
		  //expected result{5.5=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void maternalHeight3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalHeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(6);
			  question1.setKey("height");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1.50");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //MaternalHeight greater than 170
		  //expected result{5.5=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void maternalHeight4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalHeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(6);
			  question1.setKey("height");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1.71");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",1);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		
		  @Test
		  //MaternalHeight greater than 170
		  //expected result{5.5=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void maternalHeight5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalHeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(6);
			  question1.setKey("height");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"unknown");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5",1);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //MaternalHeight less than 40
		  //expected result{6.6=0,5.5=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void maternalWeight1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"39");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",0);
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //MaternalHeight between 40 and 49
		  //expected result{6.6=0,5.5=0, 4.4=0, 3.3=0, 2.2=1, 1.1=0}
	      public void maternalWeight2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"49");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",0);
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  
		  @Test
		  //MaternalHeight between 50 and 69
		  //expected result{6.6=0,5.5=0, 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void maternalWeight3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"55");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",0);
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //MaternalHeight between 70 and 89
		  //expected result{6.6=0,5.5=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void maternalWeight4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"70");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",0);
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",1);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //MaternalHeight greater than 90
		  //expected result{6.6=0,5.5=1, 4.4=0, 3.3=0, 2.2=0, 1.1=0}
	      public void maternalWeight5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"90");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",0);
			  expectedMap.put("5.5",1);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //MaternalHeight greater than 90
		  //expected result{6.6=1,5.5=0, 4.4=0, 3.3=0, 2.2=0, 1.1=0}
	      public void maternalWeight6(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(7);
			  question1.setKey("weight");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"unknown");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("6.6",1);
			  expectedMap.put("5.5",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //MaternalHeight greater than 90
		  //expected result{1.1.1=1, 2.2.2=0, 2.2.1=0, 1.1.2=0} 
	      public void maternalMortalityMorbidity1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalMortalityMorbidity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(112);
			  question1.setKey("maternalDeath");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"yes");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("1.1.1",1);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("2.2.3",0);
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //MaternalHeight greater than 90
		  //expected result{1.1.1=1, 2.2.2=0, 2.2.1=0, 1.1.2=0} 
	      public void maternalMortalityMorbidity2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="MaternalMortalityMorbidity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(110);
			  question1.setKey("sevMatMorbidility");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"yes");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      expectedMap.put("2.2.1",1);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("1.1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //NoOfDelivery greater than outCome Cs
		  //expected result{ 3.3=0, 2.2=0, 1.1=1}
	      public void noOfDelivery1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="NoOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(57);
			  question1.setKey("outCome");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			/*  expectedMap.put("3.3",0);*/
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
	  
	  
		  @Test
		  //NoOfDelivery greater than outCome Cs
		  //expected result{ 3.3=0, 2.2=1, 1.1=0}
	      public void noOfDelivery2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="NoOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(57);
			  question1.setKey("outCome");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Vaginal");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			/*  expectedMap.put("3.3",0);*/
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Oxytoxin extraOxytoxinUsed IV
		  //expected result{4.4=0,5.5=0,3.3=0, 2.2=1, 1.1=0}
	      public void oxytoxin1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Oxytoxin";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(72);
			  question1.setKey("extraOxytoxinUsed");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"IV");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		      expectedMap.put("4.4",0);
		   /*   expectedMap.put("5.5",0);*/
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Oxytoxin extraOxytoxinUsed IV
		  //expected result{4.4=1,5.5=0,3.3=0, 2.2=0, 1.1=0}
	      public void oxytoxin2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Oxytoxin";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(72);
			  question1.setKey("extraOxytoxinUsed");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Intra");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		     /* expectedMap.put("5.5",0);*/
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Oxytoxin extraOxytoxinUsed IV
		  //expected result{4.4=0,5.5=0,3.3=1, 2.2=0, 1.1=0}
	      public void oxytoxin3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Oxytoxin";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(72);
			  question1.setKey("extraOxytoxinUsed");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Rectal");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		     /* expectedMap.put("5.5",0);*/
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Oxytoxin extraOxytoxinUsed IM
		  //expected result{4.4=0,5.5=0,3.3=1, 2.2=0, 1.1=0}
	      public void oxytoxin4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Oxytoxin";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(72);
			  question1.setKey("extraOxytoxinUsed");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Rectal");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		  /*    expectedMap.put("5.5",0);*/
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Parity 1
		  //expected result{4.4=0,5.5=0,3.3=0,6.6=0,2.2=0, 1.1=1}
	      public void parity1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Parity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(26);
			  question1.setKey("parity");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  /*expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		   	  */    
			  
			  expectedMap.put("3.2",0);
		      expectedMap.put("2.1",1);
		      expectedMap.put("1.0",0);
		      expectedMap.put("4.3",0);
		      expectedMap.put("5.4",0);
		      expectedMap.put("6.5",0);
		      expectedMap.put("7.6",0);
			  
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Parity 1
		  //expected result{4.4=0,5.5=0,3.3=0,6.6=0 2.2=1, 1.1=0}
	      public void parity2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Parity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(26);
			  question1.setKey("parity");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"2");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.2",1);
		      expectedMap.put("2.1",0);
		      expectedMap.put("1.0",0);
		      expectedMap.put("4.3",0);
		      expectedMap.put("5.4",0);
		      expectedMap.put("6.5",0);
		      expectedMap.put("7.6",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Parity 3
		  //expected result{4.4=0,5.5=0,3.3=1,6.6=0 2.2=0, 1.1=0}
	      public void parity3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Parity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(26);
			  question1.setKey("parity");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"3");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.2",0);
		      expectedMap.put("2.1",0);
		      expectedMap.put("1.0",0);
		      expectedMap.put("4.3",1);
		      expectedMap.put("5.4",0);
		      expectedMap.put("6.5",0);
		      expectedMap.put("7.6",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Parity between 4 and 5
		  //expected result{4.4=1,5.5=0,3.3=0,6.6=0 2.2=0, 1.1=0}
	      public void parity4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Parity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(26);
			  question1.setKey("parity");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"4");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.2",0);
		      expectedMap.put("2.1",0);
		      expectedMap.put("1.0",0);
		      expectedMap.put("4.3",0);
		      expectedMap.put("5.4",1);
		      expectedMap.put("6.5",0);
		      expectedMap.put("7.6",0);
		   	      
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Parity >=6
		  //expected result{4.4=0,5.5=1,3.3=0,6.6=0 2.2=0, 1.1=0}
	      public void parity5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Parity";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(26);
			  question1.setKey("parity");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"7");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.2",0);
		      expectedMap.put("2.1",0);
		      expectedMap.put("1.0",0);
		      expectedMap.put("4.3",0);
		      expectedMap.put("5.4",0);
		      expectedMap.put("6.5",1);
		      expectedMap.put("7.6",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void perinealTear1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=1, 1.1=0}
	      public void perinealTear2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"2");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void perinealTear3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"3a");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void perinealTear4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"3b");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",1);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void perinealTear5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"3c");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",1);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //PerinealTear1
		  //expected result{7.7=0, 5.5=0, 6.6=1, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void perinealTear6(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PerinealTear";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(107);
			  question1.setKey("perinealTearType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"4");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",1);
		      expectedMap.put("7.7",0);    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //PlacentalWeight <200
		  //expected result{ 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void placentalWeight1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PlacentalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(76);
			  question1.setKey("placentalWght");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"199");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //PlacentalWeight 200 AND 399
		  //expected result{ 4.4=0, 3.3=0, 2.2=1, 1.1=0}
	      public void placentalWeight21(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PlacentalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(76);
			  question1.setKey("placentalWght");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"399");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PlacentalWeight >400
		  //expected result{ 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void placentalWeight3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PlacentalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(76);
			  question1.setKey("placentalWght");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"400");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //PlacentalWeight  unknown
		  //expected result{ 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void placentalWeight4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PlacentalWeight";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(76);
			  question1.setKey("placentalWght");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Unknown");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",1);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Presentation  Vxoccant
		  //expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void presentation1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Vxoccant");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Presentation  vxoccpost
		//expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=0, 2.2=1, 1.1=0}
	      public void presentation2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"vxoccpost");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //Presentation  Breech
		//expected result{7.7=0, 5.5=0, 6.6=0, 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void presentation3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Breech");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Presentation  Breech
		//expected result{7.7=0, 5.5=0, 6.6=0, 4.4=1, 3.3=0, 2.2=0, 1.1=0}
	      public void presentation4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Face");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Presentation  Shoulder
		//expected result{7.7=0, 5.5=1, 6.6=0, 4.4=0, 3.3=0, 2.2=0, 1.1=0}
	      public void presentation5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Shoulder");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",1);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",0);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //Presentation  Others
		//expected result{7.7=0, 5.5=0, 6.6=1, 4.4=0, 3.3=0, 2.2=0, 1.1=0}
	      public void presentation6(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="Presentation";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(191);
			  question1.setKey("babyPresentation");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Others");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		      expectedMap.put("5.5",0);
		      expectedMap.put("6.6",1);
		      expectedMap.put("7.7",0); 
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
				
	  
			  
		  }
		  
		  @Test
		  //Previous Cs  Samehospital and otherhospital=1
		//expected result{1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=1, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0}
	      public void previousCS(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PreviousCS";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(20);
			  question1.setKey("sameHospital");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"1");
			  Question question2= new Question();
			  question2.setId(21);
			  question2.setKey("otherHospital");
			  question2.setAnswerValueType(ValueType.INTEGER);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"1");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
				
	  
			  
		  }
		  
		  @Test
		  //Previous Cs  Samehospital and otherhospital=2
		//expected result{1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=1, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0}
	      public void previousCS2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PreviousCS";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(20);
			  question1.setKey("sameHospital");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"2");
			  Question question2= new Question();
			  question2.setId(21);
			  question2.setKey("otherHospital");
			  question2.setAnswerValueType(ValueType.INTEGER);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"2");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		  
		       // assertTrue(valueMap.equals(expectedMap));
		      Assert.assertEquals(expectedMap,valueMap);	  
				
	  
			  
		  }
		  
		  @Test
		  //Previous Cs  Samehospital and otherhospital >3
		//expected result{1.1.1=0, 2.2.5=0, 2.2.4=1, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.4=1, 1.1.5=0, 1.1.2=0, 1.1.3=0}
	      public void previousCS3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="PreviousCS";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(20);
			  question1.setKey("sameHospital");
			  question1.setAnswerValueType(ValueType.INTEGER);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"4");
			  Question question2= new Question();
			  question2.setId(21);
			  question2.setKey("otherHospital");
			  question2.setAnswerValueType(ValueType.INTEGER);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"4");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("1.1.1",0);
		     
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		       // assertTrue(valueMap.equals(expectedMap));
		      Assert.assertEquals(expectedMap,valueMap);	  
				
	  
			  
		  }
		  
		  
		  @Test
		  //ThirdStageDuration  <=9
		  //expected result{ 4.4=0, 3.3=0, 2.2=0, 1.1=1}
	      public void thirdStageDuration1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="ThirdStageDuration";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(73);
			  question1.setKey("thirdStageDuration");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"9");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",1);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //ThirdStageDuration 10-29
		  //expected result{ 4.4=0, 3.3=0, 2.2=1, 1.1=0}
	      public void thirdStageDuration2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="ThirdStageDuration";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(73);
			  question1.setKey("thirdStageDuration");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"10");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",0);
		      expectedMap.put("2.2",1);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //ThirdStageDuration  >= 30
		  //expected result{ 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void thirdStageDuration3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="ThirdStageDuration";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(73);
			  question1.setKey("thirdStageDuration");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"31");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  @Test
		  //ThirdStageDuration unknown
		  //expected result{ 4.4=0, 3.3=1, 2.2=0, 1.1=0}
	      public void thirdStageDuration4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="ThirdStageDuration";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(73);
			  question1.setKey("thirdStageDuration");
			  question1.setAnswerValueType(ValueType.FLOAT);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"31");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("3.3",1);
		      expectedMap.put("2.2",0);
		      expectedMap.put("1.1",0);
		      expectedMap.put("4.4",0);
		    
		       // assertTrue(valueMap.equals(expectedMap));
				Assert.assertEquals(expectedMap,valueMap);	  
	  
			  
		  }
		  
		  @Test
		  //TypeOfDelivery Spontaneous Normal
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery1(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Spontaneous");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"Normal");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			 
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  expectedMap.put("1.1.1",1);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  
		  @Test
		  //TypeOfDelivery Spontaneous Forcepsdelivery
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Spontaneous");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"Forcepsdelivery");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			 
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      
		      expectedMap.put("1.1.2",1);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Spontaneous Vacuumextraction
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Spontaneous");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"Vacuumextraction");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",1);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  @Test
		  //TypeOfDelivery Induced Breech
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Induced");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"Breech");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			 
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",1);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  @Test
		  //TypeOfDelivery Induced Caesarean
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Induced");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"Caesarean");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",1);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{2.2.7=0, 2.2.6=0, 1.1.1=1, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0, 2.2.8=0, 2.2.9=0}
	      public void typeOfDelivery6(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="TypeOfDelivery";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(116);
			  question1.setKey("deliveryType");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Induced");
			  Question question2= new Question();
			  question2.setId(117);
			  question2.setKey("deliveryName");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"VBAC");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
			  expectedMap.put("5.5.3",0);
		      expectedMap.put("5.5.2",0);
		      expectedMap.put("5.5.1",0);
		      expectedMap.put("5.5.4",0);
		      expectedMap.put("5.5.5",0);
		      expectedMap.put("5.5.6",0);
		      expectedMap.put("5.5.7",0);
		      expectedMap.put("5.5.8",0);
		      expectedMap.put("5.5.9",0);
			  
			  
			  expectedMap.put("4.4.3",0);
		      expectedMap.put("4.4.2",0);
		      expectedMap.put("4.4.1",0);
		      expectedMap.put("4.4.4",0);
		      expectedMap.put("4.4.5",0);
		      expectedMap.put("4.4.6",0);
		      expectedMap.put("4.4.7",0);
		      expectedMap.put("4.4.8",0);
		      expectedMap.put("4.4.9",0);
			  
		      expectedMap.put("3.3.3",0);
		      expectedMap.put("3.3.2",0);
		      expectedMap.put("3.3.1",0);
		      expectedMap.put("3.3.4",0);
		      expectedMap.put("3.3.5",0);
		      expectedMap.put("3.3.6",0);
		      expectedMap.put("3.3.7",0);
		      expectedMap.put("3.3.8",0);
		      expectedMap.put("3.3.9",0);
			  
			  
			  expectedMap.put("1.1.1",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",1);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=0}
	      public void robsonClass(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 1");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
			
			  
			  
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }

		  
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=1, 1.1.3=0}
	      public void robsonClass2(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 2e ( elective )");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
			
			  
			  
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass3(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 2i ( indicated )");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass4(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 3");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass5(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 4e ( elective )");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		    
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass6(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 4e ( elective )");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass7(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 5");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
		  @Test
		  //TypeOfDelivery Induced VBAC
		  //{1.1.13=0, 1.1.11=0, 1.1.12=0, 1.1.10=0, 2.2.10=0, 2.2.8=0, 2.2.11=0, 2.2.12=0, 2.2.9=0, 2.2.13=0, 2.2.7=0, 1.1.1=0, 2.2.6=0, 2.2.5=0, 2.2.4=0, 2.2.3=0, 2.2.2=0, 2.2.1=0, 1.1.8=0, 1.1.9=0, 1.1.6=0, 1.1.7=0, 1.1.4=0, 1.1.5=0, 1.1.2=0, 1.1.3=1}
	      public void robsonClass8(){ 
			  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
			  String datapoint="RobsonClass";
	          DataPoint dp=analyticDao.getDataPoint(datapoint);
			  Map<QuestionIdentifier, String> map= new HashMap<QuestionIdentifier, String>();
			  Question question1 = new Question();
			  question1.setId(119);
			  question1.setKey("robsonclass");
			  question1.setAnswerValueType(ValueType.STRING);
			  question1.setQuestion("question1");
			  question1.setMandatory(false);
			  map.put(new QuestionIdentifier(question1.getId()),"Group 6");
			  Question question2= new Question();
			  question2.setId(57);
			  question2.setKey("outCome");
			  question2.setAnswerValueType(ValueType.STRING);
			  question2.setMandatory(false);
			  question2.setQuestion("question2");
			  map.put(new QuestionIdentifier(question2.getId()),"CS");
			  Map<String,Integer> valueMap = dp.getValueMap(map);
			  System.out.println(valueMap);
			  Map<String, Integer> expectedMap = new HashMap<>();
		      
		      expectedMap.put("2.2.1",0);
		      expectedMap.put("2.2.2",0);
		      expectedMap.put("2.2.3",0);
		      expectedMap.put("2.2.4",0);
		      expectedMap.put("2.2.5",0);
		      expectedMap.put("2.2.6",0);
		      expectedMap.put("2.2.7",0);
		      expectedMap.put("2.2.8",0);
		      expectedMap.put("2.2.9",0);
		      expectedMap.put("2.2.10",0);
		      expectedMap.put("2.2.11",0);
		      expectedMap.put("2.2.12",0);
		      expectedMap.put("2.2.13",0);
		      expectedMap.put("1.1.1",0);
		      expectedMap.put("1.1.2",0);
		      expectedMap.put("1.1.3",0);
		      expectedMap.put("1.1.4",0);
		      expectedMap.put("1.1.5",0);  
		      expectedMap.put("1.1.6",0);
		      expectedMap.put("1.1.7",0);
		      expectedMap.put("1.1.8",0);
		      expectedMap.put("1.1.9",0);
		      expectedMap.put("1.1.10",0);
		      expectedMap.put("1.1.11",0);
		      
		      expectedMap.put("1.1.12",0);
		      expectedMap.put("1.1.13",0);
		       // assertTrue(valueMap.equals(expectedMap));
		     Assert.assertEquals(expectedMap,valueMap);
		  }
}
