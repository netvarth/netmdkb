/**
 * ReportHandler.java
 */
package com.nv.netmdkb.analytic.obstetrics.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import com.nv.netmdkb.analytic.bl.report.ReportData;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.rs.dto.Graph;
import com.nv.netmdkb.rs.dto.Inference;
import com.nv.netmdkb.rs.dto.Measure;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * @author Joshi
 *
 */
public class ReportHandler {
	
	
	private Map<ReportEnum,ReportData> reportMap;

	
	public InputStream getJRXml(Map<String,Object> map,ServletContext context) {
		String reportName = (String) map.get("reportName");
		ReportEnum reportEnum = ReportEnum.getEnum(reportName);
		String realPath = context.getRealPath("/jrxml"+File.separator+reportEnum.getJrxml()+".jrxml");
		File file = new File(realPath);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.FILENOTFOUND);
		}
	
		
	}
	
	public JasperPrint createReport(InputStream is,Map<String,Object> map){
		String reportName = (String) map.get("reportName");
		String fMonth = (String) map.get("startMonth");
		String fYear = (String) map.get("startYear");
		String toMonth = (String) map.get("endMonth");
		String toYear = (String) map.get("endYear");
		String hospitalString = (String) map.get("hospital");
		String datapoints= (String) map.get("datapoints");
	
		String[] hospitalArray = hospitalString.split(",");
		Integer[] hospitalList=new Integer[hospitalArray.length]  ;
		Integer hospital=0;
		if (hospitalString !=null && !hospitalString.equals("selectAllhospitals")){
	
			for(int i=0; i<hospitalArray.length; i++)
			{
			    try{
			    	hospitalList[i] = Integer.parseInt(hospitalArray[i]);
			    }
			    catch(NumberFormatException nfe){
			        //Not an integer, do some
			    }
			}
			
		}
		else
			hospital=null;
		ReportEnum reportEnum = ReportEnum.getEnum(reportName);
		ReportData dataBean = reportMap.get(reportEnum);
		List<Inference> dataBeanList;
		if (hospital !=null){
			dataBeanList = dataBean.getDataBeans(fMonth, fYear, toMonth, toYear,hospitalList,datapoints);
		}else{
			dataBeanList = dataBean.getDataBeans(fMonth, fYear, toMonth, toYear,datapoints);
		}
		
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		try{
			 Map<String, Object> reportParms = new HashMap<String, Object>();
			 reportParms.put("reportName",reportName+" Report" );
			reportParms.put("startMonth", getMonth(fMonth));
			reportParms.put("startYear",fYear);
			reportParms.put("endMonth",getMonth(toMonth));
			reportParms.put("endYear",toYear);
			reportParms.put("to", "to");
			reportParms.put("from","from");
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
			jasperPrint = JasperFillManager.fillReport(jasperReport,reportParms,beanColDataSource);
			
			
		}
		catch(JRException e){
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.APIERROR);
		}

	return jasperPrint;
	}

	private String getMonth(String month) {
		String monthString;
		int monthValue=Integer.parseInt(month);
		switch (monthValue) {
        case 1:  monthString = "January";       break;
        case 2:  monthString = "February";      break;
        case 3:  monthString = "March";         break;
        case 4:  monthString = "April";         break;
        case 5:  monthString = "May";           break;
        case 6:  monthString = "June";          break;
        case 7:  monthString = "July";          break;
        case 8:  monthString = "August";        break;
        case 9:  monthString = "September";     break;
        case 10: monthString = "October";       break;
        case 11: monthString = "November";      break;
        case 12: monthString = "December";      break;
        default: monthString = "Invalid month"; break;
    }
		return monthString;
	}


	public Map<ReportEnum, ReportData> getReportMap() {
		return reportMap;
	}


	public void setReportMap(Map<ReportEnum, ReportData> reportMap) {
		this.reportMap = reportMap;
	}

	public Map<String, Object> getGraphCoordinates(Graph graph) {
		ReportEnum reportEnum = ReportEnum.getEnum("graph");
		ReportData dataBean = reportMap.get(reportEnum);
		List<Inference> dataBeanList = null;
		Integer[] hospital = new Integer[1]  ;
		hospital=null;
		if (hospital !=null)
			dataBeanList = dataBean.getDataBeans(graph.getStartMonth(), graph.getStartYear(), graph.getEndMonth(), graph.getEndYear(),hospital,graph.getDatapoints());
		else
			dataBeanList = dataBean.getDataBeans(graph.getStartMonth(), graph.getStartYear(), graph.getEndMonth(), graph.getEndYear(),graph.getDatapoints());
		
		   Map<String,Object > valueMap=new LinkedHashMap();
 
		    for(Inference databean:dataBeanList){
		    	String datapoint = databean.getDataPoint();
		      List<? extends Measure> measures = databean.getEvaluations();
		      Map<String,Integer>map= new LinkedHashMap<String, Integer>();
		     if( measures.size()>0){
		    
		           Integer Y_cordinate=0;
		           String X_cordinate;
		           String current_Row=null;          
		           for(Measure measure:measures){
		        	   String deliveryType= measure.getRow();
		        	   	if(deliveryType.equals("CS")) {
		        	   		X_cordinate=getMonth(Integer.toString(measure.getMonth()));
		        	   		if(current_Row==null)
				        		   current_Row=X_cordinate;
		        	   		if(current_Row==X_cordinate)
		        	   			Y_cordinate = measure.getMeasure()+Y_cordinate;
		        	   		else{
		        	   			map.put(current_Row,Y_cordinate);
		        	   			Y_cordinate=0;
		        	   			current_Row=X_cordinate;
		        	   			Y_cordinate = measure.getMeasure()+Y_cordinate;
		        	   			
		        	   		}
		        	   		
		        	   		
		        	   	}
		        	   		
       	             
		           }
		           map.put(current_Row, Y_cordinate);
		           valueMap.put(datapoint,map);
		    
		}
	 }
		    System.out.println(valueMap);
		return valueMap;
	}



}
