package com.nv.netmdkb.pl.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmdkb.analytic.bl.AnalyticAnswer;
import com.nv.netmdkb.analytic.bl.DataNovelty;
import com.nv.netmdkb.analytic.bl.dataCluster.Cluster;
import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.analytic.obstetrics.listener.DataBucket;
import com.nv.netmdkb.facade.Parser;
import com.nv.netmdkb.pl.Query;
import com.nv.netmdkb.pl.dao.AnalyticDao;
import com.nv.netmdkb.pl.entity.AnswerStatTbl;
import com.nv.netmdkb.pl.entity.AnswerTbl;
import com.nv.netmdkb.pl.entity.DataPointTbl;
import com.nv.netmdkb.pl.entity.HealthCareOrganisationTbl;
import com.nv.netmdkb.pl.entity.QuestionnaireTbl;
import com.nv.netmdkb.questionnaire.Question;
import com.nv.netmdkb.rs.dto.MonthlyAnswerStat;


public class AnalyticDaoImpl  extends GenericDaoHibernateImpl  implements AnalyticDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1034217746449224984L;

	@PersistenceContext()
	private EntityManager em;

	private Parser parser;

	@Override
	@Transactional(readOnly=true)
	public List<DataBucket> getDataStat(int datapoint, int fmonth, int fyear,
			int toMonth, int toYear, List<String> list) {
		GregorianCalendar gc = new GregorianCalendar(fyear, fmonth-1, 1);
		Date fromDate = new java.util.Date(gc.getTime().getTime());
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.YEAR, toYear);
		calendar.set(Calendar.MONTH, toMonth);  
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.add(Calendar.DATE, -1);  
		Date toDate = calendar.getTime();
		List<MonthlyAnswerStat> answerStats = getAnswerStatTbl(datapoint, fromDate, toDate);
		List<DataBucket> dataBuckets= getDataBuckets(answerStats,list);
		return dataBuckets;
	}

	private List<MonthlyAnswerStat> getAnswerStatTbl(int dp, Date fromDate, Date toDate){
		javax.persistence.Query query = em.createQuery(Query.GET_ANSWERSTAT_TBL);
		query.setParameter("param1", dp);
		query.setParameter("param2", fromDate,TemporalType.TIMESTAMP);
		query.setParameter("param3", toDate,TemporalType.TIMESTAMP);
		// query.setParameter("param4", list);
		return executeQuery(MonthlyAnswerStat.class, query);

	}

	private List<MonthlyAnswerStat> getAnswerStatTbl(int dp, Date fromDate, Date toDate,Integer[] hospitalList){
		javax.persistence.Query query = em.createQuery(Query.GET_ANSWERSTAT_TBL_BY_HOSPITAL);
		query.setParameter("param1", dp);
		query.setParameter("param2", fromDate,TemporalType.TIMESTAMP);
		query.setParameter("param3", toDate,TemporalType.TIMESTAMP);
		List<Integer> hospitals = Arrays.asList(hospitalList);
		query.setParameter("param4", hospitals);
		// query.setParameter("param4", list);
		return executeQuery(MonthlyAnswerStat.class, query);

	}


	private List<DataBucket>getDataBuckets(List<MonthlyAnswerStat> answerStats,List<String>clusters){
		List<DataBucket> monthlyDataList = new ArrayList<DataBucket>();   
		DataBucket dataBucket = new DataBucket();
		int index=0;
		for(MonthlyAnswerStat answerStatforOneMonth: answerStats){
			dataBucket= new DataBucket();
			dataBucket.setDatapoint(answerStatforOneMonth.getDataPoint());

			dataBucket.setYear(answerStatforOneMonth.getYear());
			dataBucket.setMonth(answerStatforOneMonth.getMonth());
			dataBucket.setHospital(answerStatforOneMonth.getHospital());
			Integer[] computes = answerStatforOneMonth.getComputes();
			index =0;
			Map<String,Integer> clusterMap = new HashMap<String,Integer>();		  
			for(String cluster :clusters){


				clusterMap.put(cluster, computes[index++].intValue());


			}
			dataBucket.setClusterMap(clusterMap); 
			monthlyDataList.add(dataBucket);
		}

		return monthlyDataList;
	}






	@Override
	public List<DataBucket> getDataStat(int datapoint, int fmonth, int fyear,
			int toMonth, int toYear, Integer[] hospitalList, List<String> list) {
		GregorianCalendar gc = new GregorianCalendar(fyear, fmonth-1, 1);
		Date fromDate = new java.util.Date(gc.getTime().getTime());
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.YEAR, toYear);
		calendar.set(Calendar.MONTH, toMonth);  
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.add(Calendar.DATE, -1);  
		Date toDate = calendar.getTime();
		List<MonthlyAnswerStat> answerStats = getAnswerStatTbl(datapoint, fromDate, toDate,hospitalList);
		List<DataBucket> dataBuckets= getDataBuckets(answerStats,list);
		return dataBuckets;
	}



	@Override

	public List<DataPoint> getDataPoints(String questionnaireId) {

		List<DataPoint> dataPoints=new ArrayList<DataPoint>();
		QuestionnaireTbl questionnaireTbl =getByUID(QuestionnaireTbl.class,questionnaireId);
		List<DataPointTbl> dps=	getDataPointTbls(questionnaireTbl.getId());
		String xmlPath;


		for(DataPointTbl dpTbl:dps){
			DataPoint dataPoint = new DataPoint();
			dataPoint.setId(dpTbl.getId());
			dataPoint.setOrderId(dpTbl.getOrder());
			dataPoint.setName(dpTbl.getName());
			dataPoint.setUid(dpTbl.getUid());
			xmlPath =dpTbl.getClusterXml();
			List<Cluster> clusters= parser.parse(xmlPath);
			dataPoint.setClusters(clusters);
			dataPoints.add(dataPoint);
			//System.out.println(dpTbl.getName());
		}

		return dataPoints;
	}



	@Override

	public DataPoint getDataPoint(String datapoint) {

		DataPoint dataPoint=new DataPoint();
		DataPointTbl  dpTbl=	getByUID(DataPointTbl.class, datapoint);
		dataPoint.setId(dpTbl.getId());
		dataPoint.setName(dpTbl.getName());
		String xmlPath =dpTbl.getClusterXml();
		List<Cluster> clusters= parser.parse(xmlPath);
		dataPoint.setClusters(clusters);
		//System.out.println(dpTbl.getName());


		return dataPoint;
	}

	@Override
	public List<AnalyticAnswer> getUltimateAnswer(int globalId, Question question) {
		List<AnalyticAnswer> answerList=new ArrayList<AnalyticAnswer>();


		List<AnswerTbl> answerTblList =  getAnswer(globalId, question.getId());
		if(answerTblList!=null){
			for(AnswerTbl answerTbl:answerTblList){
				AnalyticAnswer analyticAnswer = new AnalyticAnswer();
				analyticAnswer.setAnswer(answerTbl.getAnswer());
				analyticAnswer.setDataNovelty(DataNovelty.PENULTIMATE);
				analyticAnswer.setQuestionRelevantDate(answerTbl.getAnswerSetTbl().getSDate());
				analyticAnswer.setQuestion(question);
				analyticAnswer.setIndex(answerTbl.getIndx());

				answerList.add(analyticAnswer);
			}

		}


		return answerList;
	}


	private List<AnswerTbl> getAnswer(int globalId, int questionId){

		javax.persistence.Query query = em
				.createQuery(Query.GET_ANSWER);
		query.setParameter("param1", globalId);
		query.setParameter("param2", questionId);

		return executeQuery(AnswerTbl.class, query);
	}





	@Transactional
	private List<DataPointTbl> getDataPointTbls(Integer questionId){


		javax.persistence.Query query = em
				.createQuery(Query.GET_DATAPOINTS_BY_QUESTIONNAIRE);
		query.setParameter("param1", questionId);

		return executeQuery(DataPointTbl.class, query);


	}


	/**
	 * @param parser the parser to set
	 */
	public void setParser(Parser parser) {
		this.parser = parser;
	}


	@Override
	@Transactional(readOnly=false)
	public void createBucket(DataBucket dataBucket,List<String>list) {
		AnswerStatTbl answerStatTbl = new AnswerStatTbl();

		DataPointTbl dataPointTbl = new DataPointTbl();
		dataPointTbl.setId(dataBucket.getDataPoint());
		answerStatTbl.setDataPointTbl(dataPointTbl);
		HealthCareOrganisationTbl healthCare = new HealthCareOrganisationTbl();
		healthCare.setId(dataBucket.getBranchId());
		answerStatTbl.setHealthCareOrganisationTbl(healthCare);

		answerStatTbl.setDate(dataBucket.getDate());
		Map<String, Integer> valueMap = dataBucket.getClusterMap();

		if(list.size()>0)     answerStatTbl.setCompute1(new BigDecimal(valueMap.get(list.get(0))));
		if(list.size()>1)     answerStatTbl.setCompute2(new BigDecimal(valueMap.get(list.get(1))));
		if(list.size()>2)     answerStatTbl.setCompute3(new BigDecimal(valueMap.get(list.get(2))));
		if(list.size()>3)     answerStatTbl.setCompute4(new BigDecimal(valueMap.get(list.get(3))));
		if(list.size()>4)     answerStatTbl.setCompute5(new BigDecimal(valueMap.get(list.get(4))));
		if(list.size()>5)     answerStatTbl.setCompute6(new BigDecimal(valueMap.get(list.get(5))));
		if(list.size()>6)     answerStatTbl.setCompute7(new BigDecimal(valueMap.get(list.get(6))));
		if(list.size()>7)     answerStatTbl.setCompute8(new BigDecimal(valueMap.get(list.get(7))));
		if(list.size()>8)     answerStatTbl.setCompute9(new BigDecimal(valueMap.get(list.get(8))));
		if(list.size()>9)     answerStatTbl.setCompute10(new BigDecimal(valueMap.get(list.get(9))));
		if(list.size()>10)     answerStatTbl.setCompute11(new BigDecimal(valueMap.get(list.get(10))));
		if(list.size()>11)     answerStatTbl.setCompute12(new BigDecimal(valueMap.get(list.get(11))));
		if(list.size()>12)     answerStatTbl.setCompute13(new BigDecimal(valueMap.get(list.get(12))));
		if(list.size()>13)     answerStatTbl.setCompute14(new BigDecimal(valueMap.get(list.get(13))));
		if(list.size()>14)     answerStatTbl.setCompute15(new BigDecimal(valueMap.get(list.get(14))));
		if(list.size()>15)     answerStatTbl.setCompute16(new BigDecimal(valueMap.get(list.get(15))));
		if(list.size()>16)     answerStatTbl.setCompute17(new BigDecimal(valueMap.get(list.get(16))));
		if(list.size()>17)     answerStatTbl.setCompute18(new BigDecimal(valueMap.get(list.get(17))));
		if(list.size()>18)     answerStatTbl.setCompute19(new BigDecimal(valueMap.get(list.get(18))));
		if(list.size()>19)     answerStatTbl.setCompute20(new BigDecimal(valueMap.get(list.get(19))));
		if(list.size()>20)     answerStatTbl.setCompute21(new BigDecimal(valueMap.get(list.get(20))));
		if(list.size()>21)     answerStatTbl.setCompute22(new BigDecimal(valueMap.get(list.get(21))));
		if(list.size()>22)     answerStatTbl.setCompute23(new BigDecimal(valueMap.get(list.get(22))));
		if(list.size()>23)     answerStatTbl.setCompute24(new BigDecimal(valueMap.get(list.get(23))));
		if(list.size()>24)     answerStatTbl.setCompute25(new BigDecimal(valueMap.get(list.get(24))));
		if(list.size()>25)     answerStatTbl.setCompute26(new BigDecimal(valueMap.get(list.get(25))));
		if(list.size()>26)     answerStatTbl.setCompute27(new BigDecimal(valueMap.get(list.get(26))));
		if(list.size()>27)     answerStatTbl.setCompute28(new BigDecimal(valueMap.get(list.get(27))));
		if(list.size()>28)     answerStatTbl.setCompute29(new BigDecimal(valueMap.get(list.get(28))));
		if(list.size()>29)     answerStatTbl.setCompute30(new BigDecimal(valueMap.get(list.get(29))));
		if(list.size()>30)     answerStatTbl.setCompute31(new BigDecimal(valueMap.get(list.get(30))));
		if(list.size()>31)     answerStatTbl.setCompute32(new BigDecimal(valueMap.get(list.get(31))));
		if(list.size()>32)     answerStatTbl.setCompute33(new BigDecimal(valueMap.get(list.get(32))));
		if(list.size()>33)     answerStatTbl.setCompute34(new BigDecimal(valueMap.get(list.get(33))));
		if(list.size()>34)     answerStatTbl.setCompute35(new BigDecimal(valueMap.get(list.get(34))));
		if(list.size()>35)     answerStatTbl.setCompute36(new BigDecimal(valueMap.get(list.get(35))));
		if(list.size()>36)     answerStatTbl.setCompute37(new BigDecimal(valueMap.get(list.get(36))));
		if(list.size()>37)     answerStatTbl.setCompute38(new BigDecimal(valueMap.get(list.get(37))));
		if(list.size()>38)     answerStatTbl.setCompute39(new BigDecimal(valueMap.get(list.get(38))));
		if(list.size()>39)     answerStatTbl.setCompute40(new BigDecimal(valueMap.get(list.get(39))));
		if(list.size()>40)     answerStatTbl.setCompute41(new BigDecimal(valueMap.get(list.get(40))));
		if(list.size()>41)     answerStatTbl.setCompute42(new BigDecimal(valueMap.get(list.get(41))));
		if(list.size()>42)     answerStatTbl.setCompute43(new BigDecimal(valueMap.get(list.get(42))));
		if(list.size()>43)     answerStatTbl.setCompute44(new BigDecimal(valueMap.get(list.get(43))));
		if(list.size()>44)     answerStatTbl.setCompute45(new BigDecimal(valueMap.get(list.get(44))));
		save(answerStatTbl);
	}

	@Override
	@Transactional(readOnly=false)
	public boolean addToBucket(int dataPoint, Date date, int branchId,
			Map<String, Integer> valueMap, List<String> list) {
		BigDecimal compute; 


		AnswerStatTbl answerStatTbl = getAnswerStat(dataPoint,date,branchId);

		if(list.size()>0)     {
			compute = answerStatTbl.getCompute1();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(0)))) ;
			answerStatTbl.setCompute1(compute);
		}

		if(list.size()>1){
			compute = answerStatTbl.getCompute2();
			if(compute!=null)
			compute = compute.add(new BigDecimal(valueMap.get(list.get(1)))) ;
			answerStatTbl.setCompute2(compute);

		} 

		if(list.size()>2){
			compute = answerStatTbl.getCompute3();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(2)))) ;
			answerStatTbl.setCompute3(compute);
		} 

		if(list.size()>3){
			compute = answerStatTbl.getCompute4();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(3)))) ;
			answerStatTbl.setCompute4(compute);
		} 

		if(list.size()>4){
			compute = answerStatTbl.getCompute5();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(4)))) ;
			answerStatTbl.setCompute5(compute);
		} 

		if(list.size()>5){
			compute = answerStatTbl.getCompute6();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(5)))) ;
			answerStatTbl.setCompute6(compute);
		} 

		if(list.size()>6){
			compute = answerStatTbl.getCompute7();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(6)))) ;
			answerStatTbl.setCompute7(compute);
		}


		if(list.size()>7){
			compute = answerStatTbl.getCompute8();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(7)))) ;
			answerStatTbl.setCompute8(compute);
		}


		if(list.size()>8){
			compute = answerStatTbl.getCompute9();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(8)))) ;
			answerStatTbl.setCompute9(compute);
		}


		if(list.size()>9){
			compute = answerStatTbl.getCompute10();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(9)))) ;
			answerStatTbl.setCompute10(compute);
		}



		if(list.size()>10){
			compute = answerStatTbl.getCompute11();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(10)))) ;
			answerStatTbl.setCompute11(compute);
		}


		if(list.size()>11){
			compute = answerStatTbl.getCompute12();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(11)))) ;
			answerStatTbl.setCompute12(compute);
		}


		if(list.size()>12){
			compute = answerStatTbl.getCompute13();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(12)))) ;
			answerStatTbl.setCompute13(compute);
		}

		if(list.size()>13){
			compute = answerStatTbl.getCompute14();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(13)))) ;
			answerStatTbl.setCompute14(compute);
		}


		if(list.size()>14){
			compute = answerStatTbl.getCompute15();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(14)))) ;
			answerStatTbl.setCompute15(compute);
		}


		if(list.size()>15){
			compute = answerStatTbl.getCompute16();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(15)))) ;
			answerStatTbl.setCompute16(compute);
		}


		if(list.size()>16){
			compute = answerStatTbl.getCompute17();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(16)))) ;
			answerStatTbl.setCompute17(compute);
		}


		if(list.size()>17){
			compute = answerStatTbl.getCompute18();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(17)))) ;
			answerStatTbl.setCompute18(compute);
		}	


		if(list.size()>18){
			compute = answerStatTbl.getCompute19();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(18)))) ;
			answerStatTbl.setCompute19(compute);
		}	


		if(list.size()>19){
			compute = answerStatTbl.getCompute20();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(19)))) ;
			answerStatTbl.setCompute20(compute);
		}	


		if(list.size()>20){
			compute = answerStatTbl.getCompute21();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(20)))) ;
			answerStatTbl.setCompute21(compute);
		}	


		if(list.size()>21){
			compute = answerStatTbl.getCompute22();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(21)))) ;
			answerStatTbl.setCompute22(compute);
		}	

		if(list.size()>22){
			compute = answerStatTbl.getCompute23();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(22)))) ;
			answerStatTbl.setCompute23(compute);
		}

		if(list.size()>23){
			compute = answerStatTbl.getCompute24();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(23)))) ;
			answerStatTbl.setCompute24(compute);
		}	

		if(list.size()>24){
			compute = answerStatTbl.getCompute25();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(24)))) ;
			answerStatTbl.setCompute25(compute);
		}	
		if(list.size()>25){
			compute = answerStatTbl.getCompute26();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(25)))) ;
			answerStatTbl.setCompute26(compute);
		}	
		if(list.size()>26){
			compute = answerStatTbl.getCompute27();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(26)))) ;
			answerStatTbl.setCompute27(compute);
		}
		if(list.size()>27){
			compute = answerStatTbl.getCompute28();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(27)))) ;
			answerStatTbl.setCompute28(compute);
		}
		if(list.size()>28){
			compute = answerStatTbl.getCompute29();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(28)))) ;
			answerStatTbl.setCompute29(compute);
		}	
		if(list.size()>29){
			compute = answerStatTbl.getCompute30();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(29)))) ;
			answerStatTbl.setCompute30(compute);
		}	
		if(list.size()>30){
			compute = answerStatTbl.getCompute31();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(30)))) ;
			answerStatTbl.setCompute31(compute);
		}	
		if(list.size()>31){
			compute = answerStatTbl.getCompute32();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(31)))) ;
			answerStatTbl.setCompute32(compute);
		}	
		if(list.size()>32){
			compute = answerStatTbl.getCompute33();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(32)))) ;
			answerStatTbl.setCompute33(compute);
		}	
		if(list.size()>33){
			compute = answerStatTbl.getCompute34();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(33)))) ;
			answerStatTbl.setCompute34(compute);
		}	
		if(list.size()>34){
			compute = answerStatTbl.getCompute35();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(34)))) ;
			answerStatTbl.setCompute35(compute);
		}	
		if(list.size()>35){
			compute = answerStatTbl.getCompute36();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(35)))) ;
			answerStatTbl.setCompute36(compute);
		}	
		if(list.size()>36){
			compute = answerStatTbl.getCompute37();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(36)))) ;
			answerStatTbl.setCompute37(compute);
		}	
		if(list.size()>37){
			compute = answerStatTbl.getCompute38();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(37)))) ;
			answerStatTbl.setCompute38(compute);
		}	
		if(list.size()>38){
			compute = answerStatTbl.getCompute39();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(38)))) ;
			answerStatTbl.setCompute39(compute);
		}	
		if(list.size()>39){
			compute = answerStatTbl.getCompute40();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(39)))) ;
			answerStatTbl.setCompute40(compute);
		}	

		if(list.size()>40){
			compute = answerStatTbl.getCompute41();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(40)))) ;
			answerStatTbl.setCompute41(compute);
		}	
		if(list.size()>41){
			compute = answerStatTbl.getCompute42();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(41)))) ;
			answerStatTbl.setCompute42(compute);
		}	
		if(list.size()>42){
			compute = answerStatTbl.getCompute43();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(42)))) ;
			answerStatTbl.setCompute43(compute);
		}	
		if(list.size()>43){
			compute = answerStatTbl.getCompute44();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(43)))) ;
			answerStatTbl.setCompute44(compute);
		}	
		if(list.size()>44){
			compute = answerStatTbl.getCompute45();
			if(compute!=null)
			compute=compute.add(new BigDecimal(valueMap.get(list.get(44)))) ;
			answerStatTbl.setCompute45(compute);
		}	
	
		
		update(answerStatTbl);         

		return true;
	}

	public AnswerStatTbl getAnswerStat(int dataPoint,Date date, int branchId){

		javax.persistence.Query query = em
				.createQuery(Query.GET_DATAPOINT);
		query.setParameter("param1", dataPoint);
		query.setParameter("param2", date,TemporalType.TIMESTAMP);
		query.setParameter("param3", branchId);
		return executeUniqueQuery(AnswerStatTbl.class, query);
	}

	@Override
	@Transactional
	public boolean removeFromBucket(int dataPoint, Date date, int branchId,
			Map<String, Integer> valueMap, List<String> list) {
		 AnswerStatTbl answerStatTbl = getAnswerStat(dataPoint,date,branchId);
		 BigDecimal compute; 
		 
		    if(list.size()>0)     {
                compute = answerStatTbl.getCompute1();
                if(compute!=null){
                compute=compute.subtract(new BigDecimal(valueMap.get(list.get(0)))) ;
                if(compute.intValue()<0) compute=new BigDecimal(0);
                }
                answerStatTbl.setCompute1(compute);
            }
		    
		    if(list.size()>1){
             compute = answerStatTbl.getCompute2();
             if(compute!=null){
             compute=compute.subtract(new BigDecimal(valueMap.get(list.get(1)))) ;
             if(compute.intValue()<0) compute=new BigDecimal(0);
             }
             answerStatTbl.setCompute2(compute);
             } 
		    
		    if(list.size()>2){
             compute = answerStatTbl.getCompute3();
             if(compute!=null){
             compute=compute.subtract(new BigDecimal(valueMap.get(list.get(2)))) ;
             if(compute.intValue()<0) compute=new BigDecimal(0);
             }
             answerStatTbl.setCompute3(compute);
        } 
		    
		   if(list.size()>3){
             compute = answerStatTbl.getCompute4();
             if(compute!=null){
             compute=compute.subtract(new BigDecimal(valueMap.get(list.get(3)))) ;
             if(compute.intValue()<0) compute=new BigDecimal(0);
             }
             answerStatTbl.setCompute4(compute);
        } 
		   
		   if(list.size()>4){
            compute = answerStatTbl.getCompute5();
            if(compute!=null){
            compute=compute.subtract(new BigDecimal(valueMap.get(list.get(4)))) ;
            if(compute.intValue()<0) compute=new BigDecimal(0);
            }
            answerStatTbl.setCompute5(compute);
       } 
		   
		  if(list.size()>5){
            compute = answerStatTbl.getCompute6();
            if(compute!=null){
            compute=compute.subtract(new BigDecimal(valueMap.get(list.get(5)))) ;
            if(compute.intValue()<0) compute=new BigDecimal(0);
            }
            answerStatTbl.setCompute6(compute);
       } 
		   
		  if(list.size()>6){
           compute = answerStatTbl.getCompute7();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(6)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute7(compute);
      }
		  
		  
		  if(list.size()>7){
           compute = answerStatTbl.getCompute8();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(7)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute8(compute);
      }
		  
		  
		  if(list.size()>8){
           compute = answerStatTbl.getCompute9();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(8)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute9(compute);
      }
		  
		
		  if(list.size()>9){
           compute = answerStatTbl.getCompute10();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(9)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute10(compute);
      }
		 
		 
		  
		  if(list.size()>10){
           compute = answerStatTbl.getCompute11();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(10)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute11(compute);
      }
		  
		  
		  if(list.size()>11){
           compute = answerStatTbl.getCompute12();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(11)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute12(compute);
      }
		  
		  
		 if(list.size()>12){
           compute = answerStatTbl.getCompute13();
           if(compute!=null){
           compute=compute.subtract(new BigDecimal(valueMap.get(list.get(12)))) ;
           if(compute.intValue()<0) compute=new BigDecimal(0);
           }
           answerStatTbl.setCompute13(compute);
      }
		 
		 if(list.size()>13){
          compute = answerStatTbl.getCompute14();
          if(compute!=null){
          compute=compute.subtract(new BigDecimal(valueMap.get(list.get(13)))) ;
          if(compute.intValue()<0) compute=new BigDecimal(0);
          }
          answerStatTbl.setCompute14(compute);
     }
		 
		 
		 if(list.size()>14){
          compute = answerStatTbl.getCompute15();
          if(compute!=null){
          compute=compute.subtract(new BigDecimal(valueMap.get(list.get(14)))) ;
          if(compute.intValue()<0) compute=new BigDecimal(0);
          }
          answerStatTbl.setCompute15(compute);
     }
		 
		 
		if(list.size()>15){
          compute = answerStatTbl.getCompute16();
          if(compute!=null){
          compute=compute.subtract(new BigDecimal(valueMap.get(list.get(15)))) ;
          if(compute.intValue()<0) compute=new BigDecimal(0);
          }
          answerStatTbl.setCompute16(compute);
     }
		
		
		if(list.size()>16){
         compute = answerStatTbl.getCompute17();
         if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(16)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
         }
         answerStatTbl.setCompute17(compute);
    }
		
		
		if(list.size()>17){
         compute = answerStatTbl.getCompute18();
         if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(17)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
         }
         answerStatTbl.setCompute18(compute);
    }	
	   	
	
		if(list.size()>18){
         compute = answerStatTbl.getCompute19();
         if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(18)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
         }
         answerStatTbl.setCompute19(compute);
    }	
	

		if(list.size()>19){
         compute = answerStatTbl.getCompute20();
     	if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(19)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
     	}
         answerStatTbl.setCompute20(compute);
    }	
	
		
		if(list.size()>20){
         compute = answerStatTbl.getCompute21();
     	if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(20)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
     	}
         answerStatTbl.setCompute21(compute);
    }	

		
		if(list.size()>21){
         compute = answerStatTbl.getCompute22();
     	if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(21)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
     	}
         answerStatTbl.setCompute22(compute);
    }	
		
		if(list.size()>22){
         compute = answerStatTbl.getCompute23();
     	if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(22)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
     	}
         answerStatTbl.setCompute23(compute);
    }
		
		if(list.size()>23){
         compute = answerStatTbl.getCompute24();
     	if(compute!=null){
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(23)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
     	}
         answerStatTbl.setCompute24(compute);
    }	
		
		if(list.size()>24){
         compute = answerStatTbl.getCompute25();
         {
         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(24)))) ;
         if(compute.intValue()<0) compute=new BigDecimal(0);
         }
         answerStatTbl.setCompute25(compute);
    }	
		if(list.size()>25){
	         compute = answerStatTbl.getCompute26();
	     	if(compute!=null){
	         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(25)))) ;
	         if(compute.intValue()<0) compute=new BigDecimal(0);
	     	}
	         answerStatTbl.setCompute26(compute);
	    }	
		if(list.size()>26){
	         compute = answerStatTbl.getCompute27();
	     	if(compute!=null){
	         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(26)))) ;
	         if(compute.intValue()<0) compute=new BigDecimal(0);
	     	}
	         answerStatTbl.setCompute27(compute);
	    }	
		if(list.size()>27){
	         compute = answerStatTbl.getCompute28();
	     	if(compute!=null){
	         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(27)))) ;
	         if(compute.intValue()<0) compute=new BigDecimal(0);
	     	}
	         answerStatTbl.setCompute28(compute);
	    }	
		if(list.size()>28){
	         compute = answerStatTbl.getCompute29();
	     	if(compute!=null){
	         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(28)))) ;
	         if(compute.intValue()<0) compute=new BigDecimal(0);
	     	}
	         answerStatTbl.setCompute29(compute);
	    }	
		if(list.size()>29){
	         compute = answerStatTbl.getCompute30();
	     	if(compute!=null){
	         compute=compute.subtract(new BigDecimal(valueMap.get(list.get(29)))) ;
	         if(compute.intValue()<0) compute=new BigDecimal(0);
	     	}
	         answerStatTbl.setCompute30(compute);
	    }	
		
		if(list.size()>30){
			compute = answerStatTbl.getCompute31();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(30)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute31(compute);
		}	
		if(list.size()>31){
			compute = answerStatTbl.getCompute32();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(31)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute32(compute);
		}	
		if(list.size()>32){
			compute = answerStatTbl.getCompute33();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(32)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute33(compute);
		}	
		if(list.size()>33){
			compute = answerStatTbl.getCompute34();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(33)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute34(compute);
		}	
		if(list.size()>34){
			compute = answerStatTbl.getCompute35();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(33)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute35(compute);
		}	
		if(list.size()>35){
			compute = answerStatTbl.getCompute36();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(35)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute36(compute);
		}	
		if(list.size()>36){
			compute = answerStatTbl.getCompute37();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(36)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute37(compute);
		}	
		if(list.size()>37){
			compute = answerStatTbl.getCompute38();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(37)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute38(compute);
		}	
		if(list.size()>38){
			compute = answerStatTbl.getCompute39();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(38)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute39(compute);
		}	
		if(list.size()>39){
			compute = answerStatTbl.getCompute40();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(39)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute40(compute);
		}
		
		if(list.size()>40){
			compute = answerStatTbl.getCompute41();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(40)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute41(compute);
		}
		
		if(list.size()>41){
			compute = answerStatTbl.getCompute42();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(41)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute42(compute);
		}
		
		if(list.size()>42){
			compute = answerStatTbl.getCompute43();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(42)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute43(compute);
		}
		
		if(list.size()>43){
			compute = answerStatTbl.getCompute44();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(43)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute44(compute);
		}
		
		if(list.size()>44){
			compute = answerStatTbl.getCompute45();
			if(compute!=null){
				compute=compute.subtract(new BigDecimal(valueMap.get(list.get(44)))) ;
				if(compute.intValue()<0) compute=new BigDecimal(0);
			}
			answerStatTbl.setCompute45(compute);
		}
		
	            
    update(answerStatTbl);		
	return true;

	}

		@Override
		public boolean exists(int dp, Date date, int branchId) {

			AnswerStatTbl answerStat = getAnswerStat(dp, date, branchId);
			if (answerStat==null) return false;
			return true;
		}

		@Override
		@Transactional(readOnly=false)
		public void updateBucket(int datapoint, Integer branchId, List<String> clusterList,
				Date penultimateDate, Map<String, Integer> penultimateValueMap,
				Date ultimateDate, Map<String, Integer> ultimatevalueMap) {
			this.removeFromBucket(datapoint, penultimateDate, branchId, penultimateValueMap, clusterList);
			this.addToBucket(datapoint, ultimateDate, branchId, ultimatevalueMap, clusterList);
		}

		@Override
		public List<DataBucket> getDataStatForGraph(int datapoint, int fmonth,
				int fyear, int toMonth, int toYear, List<String> list) {

			GregorianCalendar gc = new GregorianCalendar(fyear, fmonth-1, 1);
			Date fromDate = new java.util.Date(gc.getTime().getTime());
			Calendar calendar = Calendar.getInstance();  
			calendar.set(Calendar.YEAR, toYear);
			calendar.set(Calendar.MONTH, toMonth);  
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			calendar.add(Calendar.DATE, -1);  
			Date toDate = calendar.getTime();
			List<MonthlyAnswerStat> answerStats = getAnswerStatTblForGraph(datapoint, fromDate, toDate);
			List<DataBucket> dataBuckets= getDataBuckets(answerStats,list);
			return dataBuckets;
		}

		private List<MonthlyAnswerStat> getAnswerStatTblForGraph(int datapoint,Date fromDate, Date toDate) {
			javax.persistence.Query query = em.createQuery(Query.GET_ANSWERSTAT_TBL_GRAPH);
			query.setParameter("param1", datapoint);
			query.setParameter("param2", fromDate,TemporalType.TIMESTAMP);
			query.setParameter("param3", toDate,TemporalType.TIMESTAMP);
			return executeQuery(MonthlyAnswerStat.class, query);
		}

		@Override
		public List<DataBucket> getDataStatForGraph(int datapoint, int fmonth,
				int fyear, int toMonth, int toYear, Integer[] hospitalList,
				List<String> list) {
			GregorianCalendar gc = new GregorianCalendar(fyear, fmonth-1, 1);
			Date fromDate = new java.util.Date(gc.getTime().getTime());
			Calendar calendar = Calendar.getInstance();  
			calendar.set(Calendar.YEAR, toYear);
			calendar.set(Calendar.MONTH, toMonth);  
			calendar.set(Calendar.DAY_OF_MONTH, 1);  
			calendar.add(Calendar.DATE, -1);  
			Date toDate = calendar.getTime();
			List<MonthlyAnswerStat> answerStats = getAnswerStatTblForGraph(datapoint, fromDate, toDate,hospitalList);
			List<DataBucket> dataBuckets= getDataBuckets(answerStats,list);
			return dataBuckets;
		}

		private List<MonthlyAnswerStat> getAnswerStatTblForGraph(int datapoint,Date fromDate, Date toDate, Integer[] hospitalList) {
			javax.persistence.Query query = em.createQuery(Query.GET_ANSWERSTAT_TBL_BY_HOSPITAL_FOR_GRAPH);
			query.setParameter("param1", datapoint);
			query.setParameter("param2", fromDate,TemporalType.TIMESTAMP);
			query.setParameter("param3", toDate,TemporalType.TIMESTAMP);
			List<Integer> hospitals = Arrays.asList(hospitalList);
			query.setParameter("param4", hospitals);
			return executeQuery(MonthlyAnswerStat.class, query);
		}





	}
