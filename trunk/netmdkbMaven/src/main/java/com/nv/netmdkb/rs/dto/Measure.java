package com.nv.netmdkb.rs.dto;

public class Measure {
	
	private String  row;
	private String  column="";
	private int rowId;
	private int columId;
	private Integer measure;
	private Integer  year;
	private int  month;
	private String  hospital;
    
	public Measure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRow() {
		return row;
	}



	public void setRow(String row) {
		this.row = row;
	}



	public String getColumn() {
		return column;
	}



	public void setColumn(String column) {
		this.column = column;
	}



	public int getRowId() {
		return rowId;
	}



	public void setRowId(int rowid) {
		this.rowId = rowid;
	}



	public int getColumId() {
		return columId;
	}



	public void setColumId(int columId) {
		this.columId = columId;
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



	public void setYear(Integer year) {
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


}
