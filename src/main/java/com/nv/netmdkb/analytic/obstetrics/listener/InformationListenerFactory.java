package com.nv.netmdkb.analytic.obstetrics.listener;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;






import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.pl.dao.AnalyticDao;


public class InformationListenerFactory  implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	private AnalyticDao analyticDao;
	
    private List<String> questionnaires;
    
    
	
	
    public InformationListenerFactory(List<String> questionnaires, AnalyticDao analyticDao ){
    	this.questionnaires=questionnaires;
    	this.analyticDao= analyticDao;
    }
    

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public List<InformationListener> getListeners(String questionnaireId){

			List<DataPoint> datapoints= analyticDao.getDataPoints(questionnaireId);		
			List<InformationListener> informationListeners=new ArrayList<InformationListener>();	
			for(DataPoint datapoint:datapoints){

				InformationListener informationListener=(InformationListener)applicationContext.getBean("information.listener",datapoint);
				informationListeners.add(informationListener);
			}

	return 	informationListeners; 	
	}
 
	
    

	public Map<String,List<InformationListener>> getTotalListeners(){
        
		Map<String,List<InformationListener>> listenerMap=new HashMap<String,List<InformationListener>>();
		for(String questionnaireId:questionnaires) {		

			List<DataPoint> datapoints= analyticDao.getDataPoints(questionnaireId);		
			List<InformationListener> informationListeners=new ArrayList<InformationListener>();	
			for(DataPoint datapoint:datapoints){

				InformationListener informationListener=(InformationListener)applicationContext.getBean("information.listener",datapoint);
				informationListeners.add(informationListener);
			}

			listenerMap.put(questionnaireId, informationListeners);
		}  

		return listenerMap;
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}

	public AnalyticDao getAnalyticDao() {
		return analyticDao;
	}

	public void setAnalyticDao(AnalyticDao analyticDao) {
		this.analyticDao = analyticDao;
	}


	/**
	 * @return the questionnaires
	 */
	public List<String> getQuestionnaires() {
		return questionnaires;
	}


	/**
	 * @param questionnaires the questionnaires to set
	 */
	public void setQuestionnaires(List<String> questionnaires) {
		this.questionnaires = questionnaires;
	}
	
	
	

}







