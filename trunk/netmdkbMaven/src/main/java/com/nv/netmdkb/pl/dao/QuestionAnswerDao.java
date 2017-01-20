package com.nv.netmdkb.pl.dao;

import java.util.List;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.corporate.HealthCare;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;

public interface QuestionAnswerDao extends GenericDao {
	
     
      
      public int updateAnswerSet(NetMdAnswerSet answers,String questionnaire);

      public int deleteAnswerSet(NetMdAnswerSet answers,String questionnaire);

	  public int saveAnswerSet(NetMdAnswerSet answerSet,String questionnaire);
	  
	  public CorporateOrganisation getCorporteByQuesionnaire(String questionnaireId);

	  public Questionnaire getQuestionnaire(String questionnaireId);

      public int exists(int localId,int brachId);
      
      public HealthCare getHealthCareById(int id, int orgId);

      public List<DataPoint> getDataPoints(String questionnaireId);
	
      
     
    	  
      
		
	  

	  
	
	  
	  
	  
	 
	  
	  
	  

	 
	  
	  
}
