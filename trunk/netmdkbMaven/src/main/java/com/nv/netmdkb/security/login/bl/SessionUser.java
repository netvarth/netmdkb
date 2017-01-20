package com.nv.netmdkb.security.login.bl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.nv.netmdkb.facade.SessionBindable;



public class SessionUser implements HttpSessionBindingListener ,SessionBindable{
	 private int userId;
     private String userName;
     private int organisationId;
     private Date loginTime;
     private String ownerName;
   
	
	
     
     
    
     
    public Integer getUserUId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void bindSession(HttpServletRequest request){
		request.getSession(true).setAttribute("user",this);
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		CorporateLoginHandler.addToSession(event.getSession().getId(), this);
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		CorporateLoginHandler.removeFromSession(event.getSession().getId());
		
	}
	@Override
	public String getId() {
		return Integer.toString(userId);
	}
     
     
     

}
