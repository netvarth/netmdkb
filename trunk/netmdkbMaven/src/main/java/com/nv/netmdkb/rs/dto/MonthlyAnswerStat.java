package com.nv.netmdkb.rs.dto;
import java.math.BigDecimal;

public class MonthlyAnswerStat {

	private int dataPoint;
	private int year;
	private int month;
	private String hospital;
	private Integer[] computes= new Integer[45];
	/**
	 * @return the dataPoint
	 */
	public int getDataPoint() {
		return dataPoint;
	}
	/**
	 * @param dataPoint the dataPoint to set
	 */
	public void setDataPoint(int dataPoint) {
		this.dataPoint = dataPoint;
	}


	/*
	 * @return the hospital
	 */
	public String getHospital() {
		return hospital;
	}
	/**
	 * @param hospital the hospital to set
	 */
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	/**
	 * @return the computes
	 */




	public MonthlyAnswerStat(int dataPoint, int year, int month,String hospital, BigDecimal compute1,BigDecimal compute2,BigDecimal compute3,BigDecimal compute4,BigDecimal compute5,BigDecimal compute6,
			BigDecimal compute7,BigDecimal compute8,BigDecimal compute9,BigDecimal compute10,BigDecimal compute11,BigDecimal compute12,BigDecimal compute13,BigDecimal compute14,BigDecimal compute15,BigDecimal compute16,
			BigDecimal compute17,BigDecimal compute18,BigDecimal compute19,BigDecimal compute20,BigDecimal compute21,BigDecimal compute22,BigDecimal compute23,BigDecimal compute24,BigDecimal compute25,BigDecimal compute26,BigDecimal compute27,BigDecimal compute28,BigDecimal compute29,BigDecimal compute30,
			BigDecimal compute31,BigDecimal compute32,BigDecimal compute33,BigDecimal compute34,BigDecimal compute35,BigDecimal compute36,BigDecimal compute37,BigDecimal compute38,BigDecimal compute39,BigDecimal compute40,BigDecimal compute41,BigDecimal compute42,BigDecimal compute43,BigDecimal compute44,BigDecimal compute45){ 
		super();
		
		this.dataPoint = dataPoint;
		this.year =  year;
		this.month = month;
		this.hospital = hospital;
	     
		if (compute1==null) this.computes[0]=0; else this.computes[0]=compute1.intValueExact();
		if (compute2==null) this.computes[1]=0; else this.computes[1]=compute2.intValueExact();
		if (compute3==null) this.computes[2]=0; else this.computes[2]=compute3.intValueExact();
		if (compute4==null) this.computes[3]=0; else this.computes[3]=compute4.intValueExact();
		if (compute5==null) this.computes[4]=0; else this.computes[4]=compute5.intValueExact();
		if (compute6==null) this.computes[5]=0; else this.computes[5]=compute6.intValueExact();
		if (compute7==null) this.computes[6]=0; else this.computes[6]=compute7.intValueExact();
		if (compute8==null) this.computes[7]=0; else this.computes[7]=compute8.intValueExact();
		if (compute9==null) this.computes[8]=0; else this.computes[8]=compute9.intValueExact();
		if (compute10==null) this.computes[9]=0; else this.computes[9]=compute10.intValueExact();
		if (compute11==null) this.computes[10]=0; else this.computes[10]=compute11.intValueExact();
		if (compute12==null) this.computes[11]=0; else this.computes[11]=compute12.intValueExact();
		if (compute13==null) this.computes[12]=0; else this.computes[12]=compute13.intValueExact();
		if (compute14==null) this.computes[13]=0; else this.computes[13]=compute14.intValueExact();
		if (compute15==null) this.computes[14]=0; else this.computes[14]=compute15.intValueExact();
		if (compute16==null) this.computes[15]=0; else this.computes[15]=compute16.intValueExact();
		if (compute17==null) this.computes[16]=0; else this.computes[16]=compute17.intValueExact();
		if (compute18==null) this.computes[17]=0; else this.computes[17]=compute18.intValueExact();
		if (compute19==null) this.computes[18]=0; else this.computes[18]=compute19.intValueExact();
		if (compute20==null) this.computes[19]=0; else this.computes[19]=compute20.intValueExact();
		if (compute21==null) this.computes[20]=0; else this.computes[20]=compute21.intValueExact();
		if (compute22==null) this.computes[21]=0; else this.computes[21]=compute22.intValueExact();
		if (compute23==null) this.computes[22]=0; else this.computes[22]=compute23.intValueExact();
		if (compute24==null) this.computes[23]=0; else this.computes[23]=compute24.intValueExact();
		if (compute25==null) this.computes[24]=0; else this.computes[24]=compute25.intValueExact();
		if (compute26==null) this.computes[25]=0; else this.computes[25]=compute26.intValueExact();
		if (compute27==null) this.computes[26]=0; else this.computes[26]=compute27.intValueExact();
		if (compute28==null) this.computes[27]=0; else this.computes[27]=compute28.intValueExact();
		if (compute29==null) this.computes[28]=0; else this.computes[28]=compute29.intValueExact();
		if (compute30==null) this.computes[29]=0; else this.computes[29]=compute30.intValueExact();
		if (compute31==null) this.computes[30]=0; else this.computes[30]=compute31.intValueExact();
		if (compute32==null) this.computes[31]=0; else this.computes[31]=compute32.intValueExact();
		if (compute33==null) this.computes[32]=0; else this.computes[32]=compute33.intValueExact();
		if (compute34==null) this.computes[33]=0; else this.computes[33]=compute34.intValueExact();
		if (compute35==null) this.computes[34]=0; else this.computes[34]=compute35.intValueExact();
		if (compute36==null) this.computes[35]=0; else this.computes[35]=compute36.intValueExact();
		if (compute37==null) this.computes[36]=0; else this.computes[36]=compute37.intValueExact();
		if (compute38==null) this.computes[37]=0; else this.computes[37]=compute38.intValueExact();
		if (compute39==null) this.computes[38]=0; else this.computes[38]=compute39.intValueExact();
		if (compute40==null) this.computes[39]=0; else this.computes[39]=compute40.intValueExact();
		if (compute41==null) this.computes[40]=0; else this.computes[40]=compute41.intValueExact();
		if (compute42==null) this.computes[41]=0; else this.computes[41]=compute42.intValueExact();
		if (compute43==null) this.computes[42]=0; else this.computes[42]=compute43.intValueExact();
		if (compute44==null) this.computes[43]=0; else this.computes[43]=compute44.intValueExact();
		if (compute45==null) this.computes[44]=0; else this.computes[44]=compute45.intValueExact();
	
		
	
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the computes
	 */
	/**
	 * @return the computes
	 */
	public Integer[] getComputes() {
		return computes;
	}
	/**
	 * @param computes the computes to set
	 */
	public void setComputes(Integer[] computes) {
		this.computes = computes;
	}




}
