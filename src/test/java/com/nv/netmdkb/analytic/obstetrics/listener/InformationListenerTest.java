package com.nv.netmdkb.analytic.obstetrics.listener;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.pl.dao.AnalyticDao;
import com.nv.netmdkb.rs.dto.Inference;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/testDataSource.xml","classpath:META-INF/persistence-context.xml",  "classpath:META-INF/analytics-context.xml" })


public class InformationListenerTest {
	
 	  @Autowired
      private ApplicationContext applicationContext;

	  @Test
      public void getInferenceTest(){
    	 
		  AnalyticDao analyticDao = (AnalyticDao) applicationContext.getBean("analytic.dao");
		  String datapoint="BirthWeight";
		  DataPoint dp=analyticDao.getDataPoint(datapoint);
		  InformationListener listener =(InformationListener)applicationContext.getBean("information.listener",dp);
		  Inference inference =listener.getInference("1", "2014", "6", "2014");
		  assertNotNull(inference);	
		
	  }
	  
	  

	  @Test
      public void getTotalListeners(){
    	 
		 InformationListenerFactory factory =  (InformationListenerFactory) applicationContext.getBean("obstetrics.listener.factory");
		 Map<String, List<InformationListener>> response = factory.getTotalListeners();
		 assertNotNull(response);
	  }
	  
	 
	
	
	

}
