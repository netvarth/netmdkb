package com.nv.netmdkb.analytic.obstetrics.listener;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class DataBucket {
    private int dataPoint;
	private int year;
	private int month;
    private Date date;
	private String hospital;
	private int branchId;
	
	private String questionnaire;
	private Map<String,Integer> clusterMap=new HashMap<String,Integer>();
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	
	public Map<String, Integer> getClusterMap() {
		return clusterMap;
	}

	public void setClusterMap(Map<String, Integer> clusterMap) {
		this.clusterMap = clusterMap;
	}
	public int getDataPoint() {
		return this.dataPoint;
	}
	public void setDatapoint(int dataPoint) {
		this.dataPoint = dataPoint;
	}

	public String getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
     
		
		
	
	
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @param dataPoint the dataPoint to set
	 */
	public void setDataPoint(int dataPoint) {
		this.dataPoint = dataPoint;
	}
	public DataBucket(){
		
	}
	
}
