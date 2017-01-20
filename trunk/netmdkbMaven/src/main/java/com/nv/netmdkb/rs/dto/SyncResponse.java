package com.nv.netmdkb.rs.dto;

import com.nv.netmdkb.analytic.bl.ActionEnum;

public class SyncResponse {
     
	private Integer localId;
	private Integer globalId;
	private String statusCode;
	private ActionEnum actionName;
	
	public Integer getLocalId() {
		return localId;
	}
	public void setLocalId(Integer localId) {
		this.localId = localId;
	}
	public Integer getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public ActionEnum getActionName() {
		return actionName;
	}
	public void setActionName(ActionEnum actionName) {
		this.actionName = actionName;
	}
}
