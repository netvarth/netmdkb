package com.nv.netmdkb.bl.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.nv.netmdkb.bl.service.SecurityService;
import com.nv.netmdkb.facade.LoginFacade;
import com.nv.netmdkb.rs.dto.User;



public class SecurityServiceManager implements SecurityService {

	private LoginFacade loginHandler;
	
	
	
	
	public boolean isLoggedIn(String sessionId){
		if (loginHandler.isLoggedIn(sessionId)){
			return true;
		}
		
	return false;
	}
	
	
	public void login(User user,HttpServletRequest request){
		
		if (loginHandler.authenticate(user)){
			loginHandler.getNewSessionUser(user).bindSession(request);
		}	
		
	}
	
    public void logout(HttpServletRequest request){
		
			 request.getSession().removeAttribute("user");
			
	}

	public LoginFacade getLoginHandler() {
		return loginHandler;
	}

	public void setLoginHandler(LoginFacade loginHandler) {
		this.loginHandler = loginHandler;
	}


	@Override
	public User getUser(String sessionId) {
		User user=loginHandler.getUser(sessionId);
		return user;
	}
    
    
    
	
}
