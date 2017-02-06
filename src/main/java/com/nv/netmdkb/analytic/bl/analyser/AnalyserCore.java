package com.nv.netmdkb.analytic.bl.analyser;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.netmdkb.analytic.bl.AnalyticAnswer;
import com.nv.netmdkb.analytic.bl.AuthenticableAnswerSet;
import com.nv.netmdkb.analytic.bl.DataNovelty;
import com.nv.netmdkb.analytic.bl.InformationExtractor;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.Analyser;
import com.nv.netmdkb.facade.MutualAuthenticable;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswer;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;


public class AnalyserCore  implements Analyser, Runnable {



	private Thread thread ;
	private static final Log log = LogFactory.getLog(AnalyserCore.class);
	
	
	LinkedBlockingQueue<AuthenticableAnswerSet> queue =new LinkedBlockingQueue<AuthenticableAnswerSet>();
	
	Map<String, List<? extends InformationExtractor>> informationListeners ;

	
	public AnalyserCore(Map<String,List<? extends InformationExtractor>> informationListeners) {		
		this.informationListeners = informationListeners;
		thread = new Thread(this);
		thread.setName("analyserThread");
		thread.start();
	}


	

	@Override
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet,Questionnaire questionnaire,CorporateOrganisation corporate) {
		AuthenticableAnswerSet auAnswerSet=null;
			if(thread==null || !thread.isAlive()){
				thread = new Thread(this);
				thread.start();
			}	
		auAnswerSet =createAnswerList(answerSet,questionnaire, corporate );
		for (InformationExtractor informationExtractor:informationListeners.get(questionnaire.getUid())){
			informationExtractor.enhanceAnswerSet(auAnswerSet);
		}
	return auAnswerSet;
		
	 
	}


	public  AuthenticableAnswerSet createAnswerList(NetMdAnswerSet answerSet,Questionnaire questionnaire,CorporateOrganisation corporate){

		AuthenticableAnswerSet auAnswerSet=new AuthenticableAnswerSet();
		if (thread.isAlive())
			auAnswerSet.authenticate();
		else 
			throw new ServiceException(ErrorCodeEnum.APIERROR); 
		auAnswerSet.setId(answerSet.getAnswerSetGlobalId());
		auAnswerSet.setAction(answerSet.getActionName());   
		auAnswerSet.setHospital(corporate.getHelathCare(answerSet.getBranchId()));
		auAnswerSet.setQuestionnaireId(questionnaire.getUid());
		AnalyticAnswer analyticAnswer;
		for(NetMdAnswer answer:answerSet.getAnswers()){
			analyticAnswer = new AnalyticAnswer();
			analyticAnswer.setDataNovelty(DataNovelty.ULTIMATE);
			analyticAnswer.setQuestion(questionnaire.getQuestion(answer.getQuestionKey()));	
			
			 if(answerSet.getQuestionRelevantDate()!=null){
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			        Date convertedDate = null;
						try {
							convertedDate = (Date) formatter.parse(answerSet.getQuestionRelevantDate().trim());
						} catch (ParseException e) {
							e.printStackTrace();
							log.error(e);
						}
						analyticAnswer.setQuestionRelevantDate(convertedDate);
			    }	
			 
			analyticAnswer.setAnswer(answer.getAnswer());
			analyticAnswer.setIndex(answer.getIndex());
			auAnswerSet.add(analyticAnswer);
		}
		return auAnswerSet;	
	}



	@Override
	public void addToWrokList(MutualAuthenticable answerSet) {
		try{
		 AuthenticableAnswerSet auAnswerSet=(AuthenticableAnswerSet) answerSet;
		 
		 if (auAnswerSet.isAuthenticated()){
		  queue.put(auAnswerSet);
		  //System.out.println(queue.size());
		 }
		 else{
		    throw new ServiceException(ErrorCodeEnum.INVALIDDATA);	 
		 }
		 } catch (InterruptedException e) {
			  log.error(e);
			  throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
		}

	}


	@Override
	public void run() {

		AuthenticableAnswerSet answerSet=null;
		
		while (true){
			
			try {
				answerSet =queue.take();
				for (InformationExtractor informationExtractor:informationListeners.get(answerSet.getQuestionnaireId())){
					informationExtractor.extractInformation(answerSet);
				}

			} catch (RuntimeException re) {
				re.printStackTrace();
				log.error(re);
			}
			
			catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
			

			
		}

	}




	@Override
	public MutualAuthenticable authenticate(NetMdAnswerSet answerSet) {
		return null;
	}


}
