package com.nv.netmdkb.analytic.bl.report;

import java.util.List;

import com.nv.netmdkb.analytic.bl.dataPoint.DataPoint;
import com.nv.netmdkb.analytic.obstetrics.report.ReportEnum;
import com.nv.netmdkb.rs.dto.Inference;






public interface ReportData {
	
	public ReportEnum getReportName();
	
	public List<Inference> getDataBeans(String fMonth, String fYear, String toMonth, String toYear, Integer[] hospitalList, String datapoints);

	public List<Inference> getDataBeans(String fMonth,String fYear, String toMonth,String toYear, String datapoints);

	public String getJrxml();
}
