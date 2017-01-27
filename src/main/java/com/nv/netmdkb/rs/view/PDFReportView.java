/**
 * PDFReportView.java
 * January 13,2013
 */
package com.nv.netmdkb.rs.view;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.servlet.View;


/**
 * @author Luciya
 * 
 */
public class PDFReportView  implements View  {

	
	
	private final String CONTENT_TYPE = "application/pdf";
	

	@Override
	public String getContentType() {
		return CONTENT_TYPE;
	}

	/**
	* for loading report in pdf format
	*/
	@Override
	public void render(Map model, HttpServletRequest request,
	HttpServletResponse response) throws JRException, SQLException,
	IOException {
	response.setContentType("application/pdf");	
	response.addHeader("Content-Disposition", "inline;filename=\"report.pdf\"");
	JasperPrint jPrint = (JasperPrint) model.get("jPrint");
	OutputStream out = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jPrint,out);
	out.flush();
	out.close();
	}

	
	


}
