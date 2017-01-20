package com.nv.netmdkb.bl.service;

import javax.servlet.http.HttpServletRequest;

import com.nv.netmdkb.rs.dto.User;


public interface SecurityService {
	
	public boolean isLoggedIn(String sessionId);
	
	public void login(User user,HttpServletRequest request);
	
	public void logout(HttpServletRequest request);

	public User getUser(String sessionId);
		
	

}
