package com.nv.netmdkb.facade;

import com.nv.netmdkb.rs.dto.User;


public interface LoginFacade {
	
	public SessionBindable getNewSessionUser(User user);
	public boolean authenticate(User user);
	public boolean isLoggedIn(String sessionId);
	public boolean retreivePassword(User user);
	public User getUser(String sessionId);

}
