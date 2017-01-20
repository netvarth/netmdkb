package com.nv.netmdkb.exception;
/**
 * 
 */

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author Mani E.V
 *
 */
public class ServiceExceptionHandler {

	/**
	 * @param se
	 * @return errorMessage
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public String handleException(ServiceException se){
		//se.printStackTrace();
		String errorMsg = null;
		String oldMsg=se.getError().getErrMsg();
		if(se.getParamList().isEmpty())
			errorMsg = oldMsg;
		for(Parameter param : se.getParamList()){
			String toReplace = "{"+param.getParameterName()+"}";
			String valToReplace = param.getValue();
			errorMsg = oldMsg.replace(toReplace, valToReplace);
		}
		return errorMsg;
	}
	
	/**
	 * @param se
	 * @return errorMessage
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String handleRunTimeException(RuntimeException se){
		se.printStackTrace();
		return se.getMessage();
	}
}
