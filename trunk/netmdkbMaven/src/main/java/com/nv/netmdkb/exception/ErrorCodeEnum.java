/**
 * ErrorCodeEnum.java
 * 
 * @Author Asha Chandran
 *
 * Dec 21, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmdkb.exception;

public enum ErrorCodeEnum {
	
/*
 * YouNeverWait errors
 */
	OVERLOADERROR("501","Resource is not available"),
	APIERROR("503", "error from API"),
	INVALIDDATA("412", "preconditionFailed"),
	DATABASEERROR("500","Database operation failed"),
	SUCCESS("200","Success"),
    FILENOTFOUND("404","File not found"),
	AUTHORIATIONERROR("203","Credential Validation Failed"),
	LOCALANSWERSETEXISTS("204","Local Answer Set Exists");
	
	private String errCode;
	private String errMsg;


	private ErrorCodeEnum(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
