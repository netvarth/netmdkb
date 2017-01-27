package com.nv.netmdkb.security.login.bl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nv.platform.base.util.StringEncoder;
import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.LoginFacade;
import com.nv.netmdkb.pl.dao.LoginDao;
import com.nv.netmdkb.rs.dto.User;



public class CorporateLoginHandler implements LoginFacade{
	
	
	private static Map<String,SessionUser> map = new HashMap<String,SessionUser>();
	private LoginDao loginDao;
	
	
	@Override
	public SessionUser getNewSessionUser(User user){
		
		SessionUser sessionUser = new SessionUser();
		sessionUser.setLoginTime(new Date());
		sessionUser.setUserName(user.getUserName());
		String loginName =user.getUserName();
		int userId = loginDao.getUserId(loginName);
		sessionUser.setUserId(userId);
		int organisationId = loginDao.getOrganisation(loginName);
		sessionUser.setOrganisationId(organisationId);
		String ownerName = loginDao.getOwnerNameById(userId);
		sessionUser.setOwnerName(ownerName);
	return sessionUser;
	}
	

	
	@Override
	public boolean authenticate(User user){
		validateUser(user);
		String userName = user.getUserName().trim();
		user.setUserName(userName);
		String encPassword = StringEncoder.encryptWithKey(user.getPassword().trim());
		user.setPassword(encPassword);
		if (loginDao.isValid(user)){
			return true;
		}
		
		return false;
		
		
		}
		
	
	private void validateUser(User user) {
		
		if (user.getUserName()== null || user.getUserName().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.INVALIDDATA);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(user.getPassword()==null||user.getPassword().equals("")){
			
			ServiceException se = new ServiceException(
					ErrorCodeEnum.INVALIDDATA);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}



	@Override
	public boolean isLoggedIn(String sessionId){
		
		if (map.get(sessionId) !=null){
		return true;
		}
		else{
		return false;	
		}
		
		}
	

	protected static void addToSession(String sessionId,SessionUser user){
		
	    map.put(sessionId, user);	
		
	    	
		
		}
	
       
       protected static void updateSession(String sessionId,SessionUser user){
    	   map.put(sessionId, user);
       
       }
	
	
      protected static boolean removeFromSession(String sessionId){
		
    	  map.remove(sessionId);
		
		
	   return true;
	 }
	
	
	
		
			
      @Override
	    public boolean retreivePassword(User user){
			
			
			return true;
			
			
		}




	



		/**
		 * @param loginDao the loginDao to set
		 */
		public void setLoginDao(LoginDao loginDao) {
			this.loginDao = loginDao;
		}



		@Override
		public User getUser(String sessionId) {
			SessionUser suser=map.get(sessionId);
			if(suser==null){
				ServiceException se=new ServiceException(ErrorCodeEnum.INVALIDDATA);
				se.setDisplayErrMsg(true);
			}
			User user=new User();
			user.setUserName(suser.getUserName());
			user.setOwnerName(suser.getOwnerName());
			return user;
			
		}

       
	    
	    
}
