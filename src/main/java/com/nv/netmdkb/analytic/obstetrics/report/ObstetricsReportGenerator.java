package com.nv.netmdkb.analytic.obstetrics.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.nv.netmdkb.analytic.bl.report.InformationExplorer;
import com.nv.netmdkb.rs.dto.AggregateMeasure;
import com.nv.netmdkb.rs.dto.Inference;
import com.nv.netmdkb.rs.dto.Measure;


public  class ObstetricsReportGenerator {
	
private List<? extends InformationExplorer> explorers ;



public List<Inference> getInferences(String fmonth, String fyear, String toMonth, String toYear, String datapoints){
	 List<Inference>inferences = new ArrayList<Inference>();
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 Inference inference ;
	 for (InformationExplorer explorer:datapointlist){
	     inference = explorer.getInference(fmonth, fyear, toMonth, toYear);
	     //System.out.println(inference.getDataPoint());
	     inferences.add(inference);
	 }
	 
 return inferences;
 }



public List<Inference> getInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer[] hospitalList, String datapoints){
	
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 List<Inference>inferences = new ArrayList<Inference>();
	 
	 Inference inference ;
	 for (InformationExplorer explorer:datapointlist){
	     inference = explorer.getInference(fmonth, fyear, toMonth, toYear,hospitalList);
	     inferences.add(inference);
	 }
	 
 return inferences;
 }



private List<? extends InformationExplorer> getSubList(String datapoints) {
	
	String[] datapointArray = datapoints.split("\\s*,\\s*");
	List<String> datapointList= Arrays.asList(datapointArray);

	List<InformationExplorer> datapointsublist=new ArrayList<InformationExplorer>();

	 for (InformationExplorer explorer:explorers){
		if(datapointList.contains(Integer.toString(explorer.getId()))){
			datapointsublist.add(explorer);
		}
			
	 }
	return datapointsublist;
}



public  List<Inference> getAggregatedInferences(String fmonth, String fyear, String toMonth, String toYear,String datapoints){
	
	Inference inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<? extends Measure> measures=null;
	 
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 for (InformationExplorer explorer:datapointlist){
		 inference = explorer.getInference(fmonth, fyear, toMonth, toYear);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getDataPoint(),inference.getId(),measure));
		 }
	 }
	 Inference aginference= new Inference();
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference> inferenceList = new ArrayList<Inference>();
	 inferenceList.add(aginference);
return inferenceList;
}

 
 
 
public List<Inference> getAggregatedInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer[] hospitalList,String datapoints){
	
	Inference inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<? extends Measure> measures=null;
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 for (InformationExplorer explorer:datapointlist){
		 inference = explorer.getInference(fmonth, fyear, toMonth, toYear,hospitalList);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getDataPoint(),inference.getId(), measure));
		 }
	 }
	 Inference aginference= new Inference();
	 aginference.setDataPoint("");
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference> inferenceList = new ArrayList<Inference>();
	 inferenceList.add(aginference);
return inferenceList;
}



 public  ObstetricsReportGenerator(List<? extends InformationExplorer> explores){
     this.explorers=explores;
 }



public List<Inference> getInferencesPerHospitalForGraph(String fMonth,
		String fYear, String toMonth, String toYear, Integer[] hospitalList,
		String datapoints) {
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 List<Inference>inferences = new ArrayList<Inference>();
	 
	 Inference inference ;
	 for (InformationExplorer explorer:datapointlist){
	     inference = explorer.getInferenceForGraphCoordinates(fMonth, fYear, toMonth, toYear,hospitalList);
	     inferences.add(inference);
	 }
	 
 return inferences;
}



public List<Inference> getInferencesForGraph(String fMonth, String fYear,
		String toMonth, String toYear, String datapoints) {
	
	 List<Inference>inferences = new ArrayList<Inference>();
	 List<? extends InformationExplorer> datapointlist;
	 if(datapoints.equals("selectAlldatapoints")){
	     datapointlist=explorers;}
	 else {
		 datapointlist=getSubList(datapoints);}
	 Inference inference ;
	 for (InformationExplorer explorer:datapointlist){
	     inference = explorer.getInferenceForGraphCoordinates(fMonth, fYear, toMonth, toYear);
	     //System.out.println(inference);
	     inferences.add(inference);
	 }
	 
 return inferences;
}






 
 
 
 
 
}
