package com.nv.netmdkb.analytic.bl.report;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.netmdkb.bl.service.AnalyticService;
import com.nv.netmdkb.rs.dto.Graph;
import com.nv.netmdkb.rs.dto.SyncResponse;


//@RunWith(SpringJUnit4ClassRunner.class)
//
//@ContextConfiguration(locations = {"file:resource/context.xml",
//		"file:resource/testDataSource.xml",
//"file:resource/analytics-context.xml"})


//public class ReportTest {
//	 @Autowired
//     private ApplicationContext applicationContext;
//
////	 @Test
////     public void reportGraphTest(){
////		 
////		    	 AnalyticService analyticService = (AnalyticService)applicationContext.getBean("analytic.service");
////		    	  Graph graph=new Graph();
////		    	  graph.setDatapoints("12");
////		    	  graph.setStartMonth("1");
////		    	  graph.setEndMonth("9");
////		    	  graph.setStartYear("2014");
////		    	  graph.setEndYear("2014");
////		    	  List<SyncResponse> response =(List<SyncResponse>) analyticService.getGraphCoordinates(graph);
////				  assertNotNull(response);
////		
////		 
////		 
////	 }
//	 
//	 
//	
//
//}
