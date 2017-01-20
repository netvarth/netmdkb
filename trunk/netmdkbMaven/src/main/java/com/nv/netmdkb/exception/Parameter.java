/**
* Parameter.java
* 
* @Author Sreeram
*
* Version 1.0 Aug 7, 2012
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmdkb.exception;

/**
 * @author Sreeram
 *
 */
public class Parameter {
	
	private String parameterName;
	private String value;
	
	/**
	 * @param parameterName
	 * @param value
	 */
	public Parameter(String parameterName, String value) {
		super();
		this.parameterName = parameterName;
		this.value = value;
	}
	
	public Parameter(){
		
	}

	/**
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
