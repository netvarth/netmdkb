package com.nv.netmdkb.bl.service.impl;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.netmdkb.bl.service.AnalyticService;
import com.nv.netmdkb.rs.dto.NetMdQuestionAnswerBundle;
import com.nv.netmdkb.rs.dto.SyncResponse;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/context.xml",  "classpath:META-INF/analytics-context.xml" })
*/



public class AnalyticServiceManagerTest {
	
	 @Autowired
     private ApplicationContext applicationContext;

/*	 @Test
     public void questionAnswerSyncTest(){
		  
	     ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean("jacksonObjectMapper");  
	     Resource resource = applicationContext.getResource("file:C:/netmd/netmdkbMaven/systemTest/createquestans.txt"); 
	     try {
			
	    	 AnalyticService analyticService = (AnalyticService)applicationContext.getBean("analytic.service");
	    	 NetMdQuestionAnswerBundle bundle =objectMapper.readValue(resource.getInputStream() , NetMdQuestionAnswerBundle.class);
			SyncResponse response =(SyncResponse) analyticService.processQuestionAnswerBatch(bundle);
			assertEquals("200",response.getStatusCode());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     
	     
	
	 }
	 
	 @Test
	 public void questionAnswerSyncDeleteTest(){
		  
	     ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean("jacksonObjectMapper");  
	     Resource resource = applicationContext.getResource("file:C:/netmd/netmdkbMaven/systemTest/deletequestans.txt"); 
	     try {
			
	    	 AnalyticService analyticService = (AnalyticService)applicationContext.getBean("analytic.service");
	    	 NetMdQuestionAnswerBundle bundle =objectMapper.readValue(resource.getInputStream() , NetMdQuestionAnswerBundle.class);
			 SyncResponse response =(SyncResponse) analyticService.processQuestionAnswerBatch(bundle);
			 assertNotNull(response);
	     } catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     
	
	 }
	 
	 
	 @Test
     public void questionAnswerUpdateSyncTest(){
		  
	     ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean("jacksonObjectMapper");  
	     Resource resource = applicationContext.getResource("file:C:/netmd/netmdkbMaven/systemTest/updatequestans.txt"); 
	     try {
			
	    	 AnalyticService analyticService = (AnalyticService)applicationContext.getBean("analytic.service");
	    	 NetMdQuestionAnswerBundle bundle =objectMapper.readValue(resource.getInputStream() , NetMdQuestionAnswerBundle.class);
			 SyncResponse response =(SyncResponse) analyticService.processQuestionAnswerBatch(bundle);
			 assertEquals("200",response.getStatusCode());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     
	     
	
	 }
    */


	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
	

}
