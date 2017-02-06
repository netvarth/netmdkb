package com.nv.netmdkb.analytic.obstetrics.listener;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nv.netmdkb.analytic.bl.AnalyticAnswer;
import com.nv.netmdkb.analytic.bl.AuthenticableAnswerSet;
import com.nv.netmdkb.analytic.bl.DataNovelty;
import com.nv.netmdkb.analytic.bl.InformationExtractor;
import com.nv.netmdkb.analytic.bl.QuestionIdentifier;
import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.analytic.bl.report.InformationExplorer;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.pl.dao.AnalyticDao;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.rs.dto.Inference;
import com.nv.netmdkb.rs.dto.Measure;



public  class InformationListener   implements InformationExplorer , InformationExtractor  {

	private DataPoint dataPoint;
	private AnalyticDao  analyticDao; 

	/**
	 * @return the analyticDao
	 */
	public AnalyticDao getAnalyticDao() {
		return analyticDao;
	}

	/**
	 * @param analyticDao the analyticDao to set
	 */
	public void setAnalyticDao(AnalyticDao analyticDao) {
		this.analyticDao = analyticDao;
	}

	public InformationListener(DataPoint dataPoint){
		this.dataPoint=dataPoint;
	}

	@Override
	public Inference getInference( String fmonth, String fyear,
			String toMonth, String toYear) {

		Inference inference = new Inference();
		inference.setDataPoint(dataPoint.getName());
		inference.setId(dataPoint.getOrderId());
		List<DataBucket> entityList =analyticDao.getDataStat(dataPoint.getId(),Integer.parseInt(fmonth),Integer.parseInt(fyear), Integer.parseInt(toMonth), Integer.parseInt(toYear),dataPoint.getTreeNodes());

		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);
		return inference;
	}






	@Override
	public Inference getInference(String fmonth, String fyear,
			String toMonth, String toYear, Integer[] hospitalList) {

		Inference inference = new Inference();
		inference.setDataPoint(dataPoint.getName());
		inference.setId(dataPoint.getOrderId());
		List<DataBucket> entityList  =analyticDao.getDataStat(dataPoint.getId(),Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospitalList,dataPoint.getTreeNodes());
		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);
		return inference;
	}



	private List<Measure> getMeasures(List<DataBucket> entityList){
		String hospital="";
		List<Measure> totalMeasures=new ArrayList<Measure>();
		List<DataBucket> sublist=null;
		for (DataBucket dataBucket: entityList){

			if (!dataBucket.getHospital().equals(hospital)){

				if (sublist!=null){
					List<Measure> measures = getMeasuresPerHospital(sublist);
					totalMeasures.addAll(measures);
				}
				hospital =dataBucket.getHospital();
				sublist = new ArrayList<DataBucket>();

			}

			sublist.add(dataBucket);	

		}
		if (sublist!=null){
			List<Measure> measures = getMeasuresPerHospital(sublist);
			totalMeasures.addAll(measures);
		}

		return totalMeasures;
	}



	private  List<Measure>  getMeasuresPerHospital(List<DataBucket> data){
		List<Measure> measures = new LinkedList<Measure>();
		Measure measure;
		List<String[][]> matrix = dataPoint.getClusterMatrix();
		String rowName;
		String rowId;
		String columnName;
		String columnId;
		Integer value;
		Integer columnTypeOrder=0;
		Integer rowTypeOrder=0;
		Map<String, Integer> map;



		for(String[][]rowColumn: matrix){


			rowName=rowColumn[0][0];
			rowId=rowColumn[1][0];
			columnTypeOrder=0;

			for (DataBucket entity :data){

				map = entity.getClusterMap();


				//column loop
				for(int i=1;i<=(rowColumn[0].length-1);i++){
					columnName=rowColumn[0][i];
					columnId=rowColumn[1][i];
					value =map.get(columnId);
					//System.out.println("value"+value);
					measure = new Measure();
					measure.setRow(rowName);
					measure.setRowId(rowTypeOrder);
					measure.setColumId(++columnTypeOrder);
					measure.setColumn(columnName);
					measure.setMeasure(value);
					measure.setMonth(entity.getMonth());
					measure.setYear(entity.getYear());
					measure.setHospital(entity.getHospital());
					measures.add(measure);

				}

				value =map.get(rowId);

				if (value !=null){
					measure = new Measure();
					measure.setRow(rowName);
					measure.setRowId(rowTypeOrder);
					measure.setColumId(++columnTypeOrder);
					measure.setMeasure(value);
					measure.setMonth(entity.getMonth());
					measure.setYear(entity.getYear());
					measure.setHospital(entity.getHospital());

					if (columnTypeOrder>0){
						columnName="Total";
						measure.setColumn(columnName);
					}
					measures.add(measure);
				}


			}
			rowTypeOrder++; 
		}
		return measures;
	}


	@Override
	public AuthenticableAnswerSet enhanceAnswerSet(AuthenticableAnswerSet answerSet) {

		Question[] relatedQuestions = getRelatedQuestions();
		
		if (!answerSet.isLinked(relatedQuestions)) return answerSet;

		List<AnalyticAnswer> analyticAnswerlist;

		switch (answerSet.getAction()) {

		case CREATE :
			for(Question question:relatedQuestions){        
				if (answerSet.isUltimateNull(question)){

					throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
				}	
			}
			break;

		case UPDATE :
		case DELETE :	
			for(Question question:relatedQuestions){
				analyticAnswerlist =analyticDao.getUltimateAnswer(answerSet.getId(),question);
				for(AnalyticAnswer analyticAnswer:analyticAnswerlist){
					
				if (answerSet.isUltimateNull(question,analyticAnswer)){                     
					analyticAnswer.setDataNovelty(DataNovelty.ULTIMATE);
					answerSet.add(analyticAnswer);
				}

				if (answerSet.isPenultimateNull(question,analyticAnswer)){                     
					analyticAnswer.setDataNovelty(DataNovelty.PENULTIMATE);
					answerSet.add(analyticAnswer);
				}
				}
			}      

			break;
		}

		return answerSet;
	}











	@Override
	public Question[] getRelatedQuestions() {

		return dataPoint.getQuestions();
	}



	@Override
	public void extractInformation(AuthenticableAnswerSet answerSet) throws ServiceException {
		Map<QuestionIdentifier, String> answers;
		Map<String,Integer> valueMap;

			List<String> clusterList = dataPoint.getTreeNodes();
            Question[] relatedQuestions = getRelatedQuestions();
		switch(answerSet.getAction()){

		case CREATE :  
			answers = answerSet.getUltimateAnswer(relatedQuestions);
   			valueMap= dataPoint.getValueMap(answers);

			if (analyticDao.exists(dataPoint.getId(),answerSet.getUltimateDate(),answerSet.getHospital().getId())){
				analyticDao.addToBucket(dataPoint.getId(),answerSet.getUltimateDate(),answerSet.getHospital().getId(),valueMap,clusterList);
			} else{
				DataBucket dataBucket = new DataBucket();
				dataBucket.setDatapoint(dataPoint.getId());
				dataBucket.setDate(answerSet.getUltimateDate());
				dataBucket.setBranchId(answerSet.getHospital().getId());
				dataBucket.setQuestionnaire(answerSet.getQuestionnaireId());
				dataBucket.setClusterMap(valueMap);
				analyticDao.createBucket(dataBucket,clusterList);
			}

			break; 
		case UPDATE :
			Map<QuestionIdentifier, String> penultimateAnswers = answerSet.getPenultimateAnswer(relatedQuestions);
			Map<String, Integer> penultimateValueMap = dataPoint.getValueMap(penultimateAnswers);
			Map<QuestionIdentifier, String> ultimateAnswers = answerSet.getUltimateAnswer(getRelatedQuestions());
			Map<String, Integer> ultimatevalueMap = dataPoint.getValueMap(ultimateAnswers);
		
			analyticDao.updateBucket(dataPoint.getId(),answerSet.getHospital().getId(), clusterList,answerSet.getPenultimateDate(), penultimateValueMap,answerSet.getUltimateDate(),ultimatevalueMap);
			
			break;

		case DELETE :
			answers = answerSet.getPenultimateAnswer(relatedQuestions);
			valueMap= dataPoint.getValueMap(answers);
			analyticDao.removeFromBucket(dataPoint.getId(),answerSet.getPenultimateDate(),answerSet.getHospital().getId(),valueMap,clusterList);

			break;
		}

	}




	@Override
	public String getName() {

		return dataPoint.getName();
	}

	@Override
	public int getId() {

		return dataPoint.getId();
	}

	@Override
	public Inference getInferenceForGraphCoordinates(String fMonth,
			String fYear, String toMonth, String toYear, Integer[] hospitalList) {
	
		Inference inference = new Inference();
		inference.setDataPoint(dataPoint.getName());
		List<DataBucket> entityList  =analyticDao.getDataStatForGraph(dataPoint.getId(),Integer.parseInt(fMonth),Integer.parseInt(fYear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospitalList,dataPoint.getTreeNodes());
		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);
		return inference;
	}

	@Override
	public Inference getInferenceForGraphCoordinates(String fMonth,
			String fYear, String toMonth, String toYear) {
		Inference inference = new Inference();
		inference.setDataPoint(dataPoint.getName());
		List<DataBucket> entityList =analyticDao.getDataStatForGraph(dataPoint.getId(),Integer.parseInt(fMonth),Integer.parseInt(fYear), Integer.parseInt(toMonth), Integer.parseInt(toYear),dataPoint.getTreeNodes());

		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);
		return inference;
	}

















}
