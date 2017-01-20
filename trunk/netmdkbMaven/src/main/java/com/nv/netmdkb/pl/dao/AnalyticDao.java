package com.nv.netmdkb.pl.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.nv.netmdkb.analytic.bl.AnalyticAnswer;
import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.analytic.obstetrics.listener.DataBucket;
import com.nv.netmdkb.questionnaire.Question;



public interface AnalyticDao extends GenericDao {
	
	

	public List<DataBucket> getDataStat(int datapoint,int fmonth,int fyear,int toMonth,int toYear,List<String>list);
	
	public List<DataBucket> getDataStat(int datapoint,int fmonth,int fyear,int toMonth, int toYear,Integer[] hospitalList,List<String>list);
	
	public boolean addToBucket(int dataPoint,Date date, int branchId, Map<String, Integer> valueMap, List<String>list);
	
	public boolean removeFromBucket(int dataPoint,Date date,int branchId, Map<String, Integer> valueMap,List<String>list);

	public void createBucket(DataBucket dataBucket,List<String>list);
	
	public List<DataPoint> getDataPoints(String questionnaireId);

	public List<AnalyticAnswer> getUltimateAnswer(int answerSetId, Question question);

	public boolean exists(int dp, Date date,int branchId);
	
	public DataPoint getDataPoint(String datapoint);

	//public void updateBucket(int datapoint, Integer branch, Date penultimateDate,Map<Integer, String> penultimateAnswers,Map<String, Integer> penultimateValueMap, Date ultimateDate,Map<Integer, String> ultimateAnswers,Map<String, Integer> ultimatevalueMap);

	public void updateBucket(int id, Integer id2, List<String> clusterList,
			Date penultimateDate, Map<String, Integer> penultimateValueMap,
			Date ultimateDate, Map<String, Integer> ultimatevalueMap);

	public List<DataBucket> getDataStatForGraph(int datapoint,int fmonth,int fyear,int toMonth,int toYear,List<String>list);

	public List<DataBucket> getDataStatForGraph(int datapoint,int fmonth,int fyear,int toMonth, int toYear,Integer[] hospitalList,List<String>list);
	
	

	
	
}
