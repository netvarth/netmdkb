package com.nv.netmdkb.facade;

import javax.servlet.http.HttpServletRequest;

public interface SessionBindable {
	
	public void bindSession(HttpServletRequest request);
	
	public String getId();

}
