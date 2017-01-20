package com.nv.netmdkb.analytic.obstetrics.report;

import java.util.List;

import com.nv.netmdkb.analytic.bl.report.ReportData;
import com.nv.netmdkb.rs.dto.Inference;


public class SummaryReport implements ReportData{
	
	private ObstetricsReportGenerator reportGenerator;
	
	private String jrxml;
	
	private ReportEnum reportEnum;


	@Override
	public List<Inference> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear,Integer[] hospitalList,String datapoints ) {
		
		return reportGenerator.getInferencesPerHospital(fMonth, fYear, toMonth, toYear,hospitalList,datapoints);
	}

	/**
	 * @return the reportGenerator
	 */
	public ObstetricsReportGenerator getReportGenerator() {
		return reportGenerator;
	}

	/**
	 * @param reportGenerator the reportGenerator to set
	 */
	public void setReportGenerator(ObstetricsReportGenerator reportGenerator) {
		this.reportGenerator = reportGenerator;
	}

	@Override
	public List<Inference> getDataBeans(String fMonth,
			String fYear, String toMonth, String toYear,String datapoints) {
	
		return reportGenerator.getInferences(fMonth, fYear, toMonth, toYear,datapoints);
	}

	
	@Override
	public String getJrxml() {
		
		return this.jrxml;
	}

	

	/**
	 * @param reportEnum the reportEnum to set
	 */
	public void setReportName(String reportName) {
		this.reportEnum = ReportEnum.valueOf(reportName);
	}


	public void setJrxml(String jrxml) {
		this.jrxml = jrxml;
	}

	@Override
	public ReportEnum getReportName() {
		
		return this.reportEnum;
	}





	

	

}
