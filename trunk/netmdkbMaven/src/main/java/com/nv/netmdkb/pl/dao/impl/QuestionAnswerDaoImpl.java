/**
 * 
 */
package com.nv.netmdkb.pl.dao.impl;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.analytic.bl.dataCluster.ValueType;
import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;

import org.springframework.transaction.annotation.Transactional;
import com.nv.netmdkb.corporate.CorporateOrganisation;
import com.nv.netmdkb.corporate.HealthCare;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.pl.Query;
import com.nv.netmdkb.pl.dao.QuestionAnswerDao;
import com.nv.netmdkb.pl.entity.AnswerSetTbl;
import com.nv.netmdkb.pl.entity.AnswerTbl;
import com.nv.netmdkb.pl.entity.DataPointTbl;

import com.nv.netmdkb.pl.entity.HealthCareOrganisationTbl;
import com.nv.netmdkb.pl.entity.OrganisationNetmdTbl;

import com.nv.netmdkb.pl.entity.QuestionTbl;
import com.nv.netmdkb.pl.entity.QuestionnaireTbl;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.questionnaire.Questionnaire;
import com.nv.netmdkb.rs.dto.NetMdAnswer;
import com.nv.netmdkb.rs.dto.NetMdAnswerSet;




/**
 * @author netvarth
 *
 */
public class QuestionAnswerDaoImpl extends GenericDaoHibernateImpl implements QuestionAnswerDao {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext()
	private EntityManager em;
	
	@Override
	@Transactional (readOnly=false)
	public int updateAnswerSet(NetMdAnswerSet answerSet,String questionnaire) {     
		AnswerSetTbl aset = getById(AnswerSetTbl.class,answerSet.getAnswerSetGlobalId());
	    aset.setLocalAnswerSet(answerSet.getAnswerSetLocalId());
	    HealthCareOrganisationTbl healthOrgTbl=getById(HealthCareOrganisationTbl.class,answerSet.getBranchId());
	    aset.setHealthCareOrganisationTbl(healthOrgTbl);
	    QuestionnaireTbl questionnaireTbl =getByUID(QuestionnaireTbl.class,questionnaire);
	    aset.setQuestionnaireTbl(questionnaireTbl);
	    
	    if(answerSet.getQuestionRelevantDate()!=null){
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date convertedDate = null;
				try {
					convertedDate = (Date) formatter.parse(answerSet.getQuestionRelevantDate().trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    aset.setSDate(convertedDate);
	    }
	    update(aset);
	    
	    for(NetMdAnswer answer:answerSet.getAnswers()){
	    	AnswerTbl aTbl=getAnswerByQuestion(answer.getQuestionKey(),answerSet.getAnswerSetGlobalId(),answer.getIndex());
		    	if(aTbl!=null) { 		
		    	aTbl.setQuestionTbl(getQuestionByKey(answer.getQuestionKey(),questionnaireTbl.getId()));
		    	aTbl.setAnswer(answer.getAnswer());
		    	aTbl.setAnswerSetTbl(getById(AnswerSetTbl.class,aset.getId()));
		    	update(aTbl);
		    	}else{
		    	    aTbl=new AnswerTbl();
		    	    aTbl.setQuestionTbl(getQuestionByKey(answer.getQuestionKey(),questionnaireTbl.getId()));
			    	aTbl.setAnswer(answer.getAnswer());
			    	aTbl.setIndx(answer.getIndex());
			    	aTbl.setAnswerSetTbl(getById(AnswerSetTbl.class,aset.getId()));
			    	save(aTbl);		
		    	}
	    	}
	    
		
		return answerSet.getAnswerSetGlobalId();
	}
	
	@Override
	@Transactional(readOnly=false)
	public int deleteAnswerSet(NetMdAnswerSet answerSet,
			String questionnaire) {
		
		AnswerSetTbl aset = getById(AnswerSetTbl.class,answerSet.getAnswerSetGlobalId());
	    delete(aset);
		return answerSet.getAnswerSetGlobalId();
	}

	@Override
	@Transactional(readOnly=false)
	public int saveAnswerSet(NetMdAnswerSet answerSet,String questionnaire) {
	    AnswerSetTbl aset = new AnswerSetTbl();
	    aset.setLocalAnswerSet(answerSet.getAnswerSetLocalId());
 	    HealthCareOrganisationTbl hlthOrgTbl=getById(HealthCareOrganisationTbl.class,answerSet.getBranchId());
	    aset.setHealthCareOrganisationTbl(hlthOrgTbl);
	    QuestionnaireTbl questionnaireTbl=(QuestionnaireTbl)getRecordByQuestionnaire(questionnaire);
	    aset.setQuestionnaireTbl(questionnaireTbl);
	    
	    if(answerSet.getQuestionRelevantDate()!=null){
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        Date convertedDate = null;
				try {
					convertedDate = (Date) formatter.parse(answerSet.getQuestionRelevantDate().trim());
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    aset.setSDate(convertedDate);
	    }
	    save(aset);
	    AnswerTbl aTbl;
	    for(NetMdAnswer answer:answerSet.getAnswers()){
	    	 	aTbl=new AnswerTbl();
	    	 	//QuestionTbl questionTbl=new QuestionTbl();
	    	 	QuestionTbl  questionTbl =getQuestionByKey(answer.getQuestionKey(),questionnaireTbl.getId());
	    	    aTbl.setAnswer(answer.getAnswer());
	    	    aTbl.setQuestionTbl(questionTbl);
	    	    aTbl.setIndx(answer.getIndex());
		    	aTbl.setAnswerSetTbl(getById(AnswerSetTbl.class,aset.getId()));
		    save(aTbl);
	    	
	    }
	    
		return aset.getId();
	}

	private QuestionnaireTbl getRecordByQuestionnaire(String questionnaire) {
		javax.persistence.Query query = em.createQuery(Query.GET_QUESTIONNAIRE_BY_UID);
		   query.setParameter("param1", questionnaire);
		   return executeUniqueQuery(QuestionnaireTbl.class,query);
	}
	

	private QuestionTbl getQuestionByKey(String key,int questionnareId){
		javax.persistence.Query query = em.createQuery(Query.GET_QSTN_BY_QUESTION_KEY);
		   query.setParameter("param1", key);
		 //  query.setParameter("param2",questionnareId);
		   return executeUniqueQuery(QuestionTbl.class,query);
	}
	
	
	private AnswerTbl getAnswerByQuestion(String qid,int answerSetId,int qIndex){
	   javax.persistence.Query query = em.createQuery(Query.GET_ANSWER_BY_QSTN);
	   query.setParameter("param1", qid);
	   query.setParameter("param2",answerSetId);
	   query.setParameter("param3",qIndex);
	   return executeUniqueQuery(AnswerTbl.class,query);
   }

	
	
	private List<OrganisationNetmdTbl> getNetmdBranches(int orgId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BY_ORGANISATION);
		query.setParameter("param1", orgId);
	return executeQuery(OrganisationNetmdTbl.class, query);
		
	}

	@Override
	@Transactional
	public CorporateOrganisation getCorporteByQuesionnaire(String questionnaireId) {
		
		QuestionnaireTbl questionnaireTbl=getQuestionnaireTbl(questionnaireId);
		if(questionnaireTbl==null){
			ServiceException se=new ServiceException(ErrorCodeEnum.FILENOTFOUND);
			se.setDisplayErrMsg(true);
			throw se;
		}
		// TODO Auto-generated method stub
		int orgId=questionnaireTbl.getOrganisationTbl().getId();
		CorporateOrganisation corporate=getCorporateById(orgId);
		return corporate;		
	}

	
	@Transactional
	public CorporateOrganisation getCorporateById(int orgId) {
		CorporateOrganisation corporate=new CorporateOrganisation();
		HealthCare corporateHealth=getHealthCareById(orgId);
		corporate.setId(corporateHealth.getId());
		corporate.setName(corporateHealth.getName());
		
		List<OrganisationNetmdTbl> OrgNetmdTables=getNetmdBranches(orgId);
		List<HealthCare> healthCareList=new ArrayList<HealthCare>();
		for(OrganisationNetmdTbl OrgNetmd:OrgNetmdTables){
			HealthCare healthcare=new HealthCare();
			Integer id =OrgNetmd.getNetmdBranchTbl().getId();
			healthcare=getHealthCareById(id);
			if(healthcare!=null)
			healthCareList.add(healthcare);
			
		}
		corporate.setAttachedHelathCares(healthCareList);
		return corporate; 
		
	}

	
	@Override
	@Transactional
	
	public HealthCare getHealthCareById(int id, int orgId){
		List<OrganisationNetmdTbl> OrgNetmdTables=getNetmdBranches(orgId);
		
		HealthCare healthCare;
		Integer healthCareId;
		boolean contains=false;
		for(OrganisationNetmdTbl OrgNetmd:OrgNetmdTables){
			healthCareId =OrgNetmd.getNetmdBranchTbl().getId();
		    if (id==healthCareId){
		    	contains=true;
		    	break;
		    }
		}
		
		if (contains){
		healthCare=	getHealthCareById(id);
		} else {
		  healthCare=null;	
		}
		
	return healthCare;	
	}
	
	
	
	
	@Transactional
	private HealthCare getHealthCareById( int id){
		HealthCareOrganisationTbl healthCareOrganisationTbl=getById(HealthCareOrganisationTbl.class, id);
		HealthCare healthcare=new HealthCare();
		if(healthCareOrganisationTbl.getStatus()!="inactive"){
			healthcare.setId(healthCareOrganisationTbl.getId());
			healthcare.setName(healthCareOrganisationTbl.getName());
		}
		return healthcare;
	}
	
	
	@Override
	@Transactional
	public Questionnaire getQuestionnaire(String questionnareId){
		Questionnaire questionnaire= new Questionnaire();
		
		
		QuestionnaireTbl questionnaireTbl=getQuestionnaireTbl(questionnareId);
		if(questionnaireTbl==null){
			ServiceException se=new ServiceException(ErrorCodeEnum.FILENOTFOUND);
			se.setDisplayErrMsg(true);
			throw se;
			
		}
		List<QuestionTbl> questiontbls=getQuestionListByQuestionnaireId(questionnaireTbl.getId());
		List<Question> questionList=new ArrayList<Question>();
	      if(questiontbls!=null){
	    	  for(QuestionTbl questiontbl:questiontbls){
	    		  Question question=new Question();
	    		  question.setKey(questiontbl.getQuestionKey());
	    		  question.setMandatory(questiontbl.isMandatory());
	    		  question.setId(questiontbl.getId());
	    		  question.setAnswerValueType(ValueType.valueOf(questiontbl.getAnswerType()));
	    		  question.setIndexed(questiontbl.isIndexed());
	    		  question.setQuestion(questiontbl.getQuestion());
	    		  questionList.add(question);
	    	  }
	      }
	      questionnaire.setQuestions(questionList);
	      questionnaire.setName(questionnaireTbl.getOrganisationTbl().getName());
	      questionnaire.setUid(questionnaireTbl.getUid());
		// to be completed
		
	return questionnaire;
	}
	





	private List<QuestionTbl> getQuestionListByQuestionnaireId(
			Integer questionnareId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUESTIONS_BY_QUESTIONNAIREID);
		query.setParameter("param1",questionnareId);
		return executeQuery(QuestionTbl.class, query);
		
	}




	private QuestionnaireTbl getQuestionnaireTbl(String questionnaireId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUESTIONNAIRE_BY_UID);
		query.setParameter("param1", questionnaireId);
		return executeUniqueQuery(QuestionnaireTbl.class, query);
		
	}


	
	
	


	public EntityManager getEm() {
		return em;
	}




	public void setEm(EntityManager em) {
		this.em = em;
	}




	@Override
	@Transactional(readOnly=true)
	public int exists(int localId, int branchId) {
		AnswerSetTbl row;
		if((row =getAnswerSetByLocalId(localId,branchId))==null)
		return 0;
		else 
		return row.getId();	
	}
	
	
	@Transactional(readOnly=true)
	private AnswerSetTbl getAnswerSetByLocalId(int localId,int branchId){
		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWERSET_BY_LOCALID);
		query.setParameter("param1",localId);
		query.setParameter("param2",branchId);
		return executeUniqueQuery(AnswerSetTbl.class, query);
	}

	@Override
	public List<DataPoint> getDataPoints(String questionnaireId) {
		List<DataPoint> dataPoints=new ArrayList<DataPoint>();
		QuestionnaireTbl questionnaireTbl =getByUID(QuestionnaireTbl.class,questionnaireId);
	    List<DataPointTbl> dps=	getDataPointTbls(questionnaireTbl.getId());
	   
	    
	for(DataPointTbl dpTbl:dps){
		DataPoint dataPoint = new DataPoint();
		dataPoint.setId(dpTbl.getId());
		dataPoint.setName(dpTbl.getName());
		dataPoint.setUid(dpTbl.getUid());
		dataPoints.add(dataPoint);
		
	}
	return dataPoints;
	}

	
	@Transactional(readOnly=true)
	private List<DataPointTbl> getDataPointTbls(int questionId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DATAPOINTS_BY_QUESTIONNAIRE);
		query.setParameter("param1", questionId);
		return executeQuery(DataPointTbl.class, query);
	
	}

}
