package com.nv.netmdkb.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class Inference {
private int id;	
private String dataPoint ; 
 
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}



private  List<? extends Measure> evaluations ;





public List<? extends Measure> getEvaluations() {
	return evaluations;
}

public void setEvaluations(List<? extends Measure> evaluations) {
	this.evaluations = evaluations;
}

public String getDataPoint() {
	return dataPoint;
}

public void setDataPoint(String dataPoint) {
	this.dataPoint = dataPoint;
}






}
