package com.nv.netmdkb.security.sync.bl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


import com.nv.netmdkb.facade.SessionBindable;



public class Lock implements SessionBindable, HttpSessionBindingListener {

	private int branchId;
	private String passPhrase;
	private String macId;

	
	@Override
	public void bindSession(HttpServletRequest request) {
	
		request.getSession(true).setAttribute("user", this);
	
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		SyncAuthenticationHandler.addToSession(passPhrase, this);
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		SyncAuthenticationHandler.removeFromSession(passPhrase);

		
	}

	@Override
	public String getId() {
		
		return this.passPhrase;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}
	
	

	
}
