/**
 * ServiceException.java
 * 
 * @author Rakhi Vasudevan
 * 
 * Oct 29, 2012 
 * 
 */
package com.nv.netmdkb.exception;

import java.util.ArrayList;
import java.util.List;



public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3858074760526705563L;
	private ErrorCodeEnum error;
	private List<Parameter> paramList=new ArrayList<Parameter>();
	private boolean displayErrMsg;
	/**
	 * @param error
	 * @param paramMap
	 */
	public ServiceException(ErrorCodeEnum error) {
		super();
		this.error = error;

	}
	
	

	/**
	 * @return the error
	 */
	public ErrorCodeEnum getError() {
		return error;
	}


	public void addParam(Parameter e){
	   paramList.add(e);
	}



	public List<Parameter> getParamList() {
		return paramList;
	}



	/**
	 * @return the displayErrMsg
	 */
	public boolean isDisplayErrMsg() {
		return displayErrMsg;
	}



	/**
	 * @param displayErrMsg the displayErrMsg to set
	 */
	public void setDisplayErrMsg(boolean displayErrMsg) {
		this.displayErrMsg = displayErrMsg;
	}
	
	


	


	



}
