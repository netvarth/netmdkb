package com.nv.netmdkb.analytic.bl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nv.netmdkb.corporate.HealthCare;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.MutualAuthenticable;
import com.nv.netmdkb.questionnaire.Question;

public class AuthenticableAnswerSet  implements MutualAuthenticable  {

	/**
	 * 
	 */
    private boolean onePass=true;
    private boolean twoPass=false;
	private ActionEnum action;
	private HealthCare hospital;
	private int id;
	private String questionnaireId;
	private Date ultimateDate=null;
	private Date penultimateDate=null;
	private Map<Question,AnalyticAnswer> ultimateValues=new HashMap<Question,AnalyticAnswer>();
	private Map<Question,AnalyticAnswer> penultimateValues=new HashMap<Question,AnalyticAnswer>();
	private Map<Question,List<AnalyticAnswer>> indexedUltimatValues =new HashMap<Question,List<AnalyticAnswer>>();
	private Map<Question,List<AnalyticAnswer>> indexedPenultimatValues =new HashMap<Question,List<AnalyticAnswer>>();
	public boolean lock(){
		return twoPass=false;
		
	}
	
    public boolean authenticate(){
		return twoPass=true;
    	
    }

    public boolean isAuthenticated(){

    return onePass&&twoPass;	
    }
  
	public boolean isUltimateNull(Question question){
		
      return ultimateValues.get(question)==null && indexedUltimatValues.get(question)==null;
	}
    
	public boolean isUltimateNull(Question question,AnalyticAnswer answer){
		
	      return ultimateValues.get(question)==null && isNullindexedUltimatValues(question,answer);
		}
	    
	private boolean isNullindexedUltimatValues(Question question,
			AnalyticAnswer answer) {
		   List<AnalyticAnswer > answerlist=indexedUltimatValues.get(question);
		    if(answerlist==null) return true;
			for(AnalyticAnswer Analyticanswer:answerlist){
				if(Analyticanswer.getIndex()==answer.getIndex()) return false;
				
			}
				return true;
	}

	public boolean isPenultimateNull(Question question){
		
	      return penultimateValues.get(question)==null && indexedPenultimatValues.get(question)==null;
		}
	
	
	public boolean isPenultimateNull(Question question,AnalyticAnswer answer){
		
		 return penultimateValues.get(question)==null && isNullindexedPenultimatValues(question,answer);
				 	 
				 
		}
	
	
    private boolean  isNullindexedPenultimatValues(
			Question question, AnalyticAnswer answer) {
    List<AnalyticAnswer > answerlist=indexedPenultimatValues.get(question);
    if(answerlist==null) return true;
	for(AnalyticAnswer analyticanswer:answerlist){
		if(analyticanswer.getIndex()==answer.getIndex()) return false;
		
	}
		return true;
	}

	public boolean isLinked(Question...questions ){
    	boolean linked =false;
    	for (Question question: questions){
    		if (ultimateValues.get(question)!=null) linked=true;
    		if(indexedUltimatValues.get(question)!=null) linked=true;
    	}
    	
    return linked;
    }
	
	
	
	
	public Map<QuestionIdentifier,String> getUltimateAnswer(Question... questions){

		Map<QuestionIdentifier,String> map= new HashMap<QuestionIdentifier,String>();
		
        System.out.println("getUltimateAnswer");
        AnalyticAnswer analyticAnswer;
		for (Question question: questions){
                if(question.isIndexed()==true){
       
                	List<AnalyticAnswer>  answerlist = indexedUltimatValues.get(question);
                	if(answerlist!=null){
                		for(AnalyticAnswer ans:answerlist){
                			if (ans.getQuestion().getId()==question.getId() && ans.getDataNovelty()==DataNovelty.ULTIMATE){		
                				map.put(new QuestionIdentifier(question.getId(), ans.getIndex()),ans.getAnswer());
                			}
                    	}
                		
                	}
                	
                	
                }else{
                	 analyticAnswer=ultimateValues.get(question);     
     				if (analyticAnswer.getQuestion().getId()==question.getId() && analyticAnswer.getDataNovelty()==DataNovelty.ULTIMATE){
     					map.put(new QuestionIdentifier(question.getId()),analyticAnswer.getAnswer());	
     				}
                }
               
		}

		return map ;
	}


	public Map<QuestionIdentifier,String> getPenultimateAnswer(Question... questions){
        
		AnalyticAnswer analyticAnswer;
		Map<QuestionIdentifier,String> map= new HashMap<QuestionIdentifier,String>();
		for (Question question: questions){
			
			
	        if(question.isIndexed()==true){
	            
            	List<AnalyticAnswer>  answerlist = indexedPenultimatValues.get(question);
            	if(answerlist!=null){
            		for(AnalyticAnswer ans:answerlist){
            			map.put(new QuestionIdentifier(question.getId(), ans.getIndex()),ans.getAnswer());
                	}
            		
            	}
            	
            	
            }else{
			analyticAnswer=penultimateValues.get(question);
			
			if (analyticAnswer.getQuestion().getId()==question.getId() && analyticAnswer.getDataNovelty()==DataNovelty.PENULTIMATE){
					map.put(new QuestionIdentifier(question.getId()),analyticAnswer.getAnswer())	;	
			}
		}
	        
		}

		return map ;
	}

	
	


	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	

	
	
	


	public int getId(){

		return   this.id;
	}


	public void setId(int answerSetId){

		this.id=answerSetId;
	}


	public  ActionEnum getAction() {
		return action;
	}


	public void add(AnalyticAnswer analyticAnswer) throws ServiceException{

		if (analyticAnswer.getDataNovelty()==null)
			throw new ServiceException(ErrorCodeEnum.APIERROR);
		
			
		if (analyticAnswer.getDataNovelty()==DataNovelty.ULTIMATE){
			if (this.ultimateDate==null) ultimateDate= analyticAnswer.getQuestionRelevantDate();
			List<AnalyticAnswer> previouslist;
		   if(analyticAnswer.getQuestion().isIndexed()){
			   
				   previouslist=indexedUltimatValues.get(analyticAnswer.getQuestion());
				   if(previouslist==null) previouslist=new ArrayList<AnalyticAnswer>();
				   previouslist.add(analyticAnswer);
				   indexedUltimatValues.put(analyticAnswer.getQuestion(),previouslist);
			   
		   }else
			ultimateValues.put(analyticAnswer.getQuestion(), analyticAnswer);
		}
		
		if (analyticAnswer.getDataNovelty()==DataNovelty.PENULTIMATE){
			if (this.penultimateDate==null) penultimateDate= analyticAnswer.getQuestionRelevantDate();
			List<AnalyticAnswer> previouslist;
			   if(analyticAnswer.getQuestion().isIndexed()){
					   previouslist=indexedPenultimatValues.get(analyticAnswer.getQuestion());
					   if(previouslist==null) previouslist=new ArrayList<AnalyticAnswer>();
					   previouslist.add(analyticAnswer);
					   indexedPenultimatValues.put(analyticAnswer.getQuestion(),previouslist);
			   }else
			penultimateValues.put(analyticAnswer.getQuestion(), analyticAnswer);
		}
	}

	public void setAction(ActionEnum action) {
		this.action = action;
	}


	public HealthCare getHospital() {
		return hospital;
	}

	public void setHospital(HealthCare hospital) {
		this.hospital = hospital;
	}

	public Date getUltimateDate() {
		return ultimateDate;
	}

	public Date getPenultimateDate() {
		return penultimateDate;
	}

	

	
    




}

