package com.nv.netmdkb.rs.dto;


public class AggregateMeasure extends Measure {
	
	private String  dataPoint;
	private int dataPointId;
	private String  row;
	private String  column="";
	private int rowId;
	private int columId;
	private Integer measure;
	private Integer  year;
	private int  month;
	private String  hospital;

    

    
    public String getDataPoint() {
		return dataPoint;
	}
	public void setDataPoint(String dataPoint) {
		this.dataPoint = dataPoint;
	}
	public int getDataPointId() {
		return dataPointId;
	}
	public void setDataPointId(int dataPointId) {
		this.dataPointId = dataPointId;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public Integer getMeasure() {
		return measure;
	}
	public void setMeasure(Integer measure) {
		this.measure = measure;
	}
	public Integer getYear() {
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
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
/*	public AggregateMeasure(String dataPoint,Measure measure) {
		super();
		this.dataPoint = dataPoint;
		this.row=measure.getRow();
		this.rowId=measure.getRowId();
		this.column=measure.getColumn();
		this.columId=measure.getColumId();
		this.measure=measure.getMeasure();
		this.year=measure.getYear();
		this.month=measure.getMonth();
		this.hospital=measure.getHospital();
	    this.column=measure.getColumn();
	}
 */  
    
    
	public AggregateMeasure(String dataPoint, int id, Measure measure) {
		
		this.dataPoint = dataPoint;
		this.dataPointId = id;
		this.row=measure.getRow();
		this.rowId=measure.getRowId();
		this.column=measure.getColumn();
		this.columId=measure.getColumId();
		this.measure=measure.getMeasure();
		this.year=measure.getYear();
		this.month=measure.getMonth();
		this.hospital=measure.getHospital();
	    this.column=measure.getColumn();
	}
	/**
	 * @return the rowId
	 */
	public int getRowId() {
		return rowId;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	/**
	 * @return the columId
	 */
	public int getColumId() {
		return columId;
	}
	/**
	 * @param columId the columId to set
	 */
	public void setColumId(int columId) {
		this.columId = columId;
	}

    

}
