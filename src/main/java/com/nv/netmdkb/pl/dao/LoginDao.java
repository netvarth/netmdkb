package com.nv.netmdkb.pl.dao;

import com.nv.netmdkb.rs.dto.Credentials;
import com.nv.netmdkb.rs.dto.User;

public interface LoginDao extends GenericDao {
	
	public int getUserId(String loginId);
	public int getOrganisation(String loginName);
	public boolean isValid(User user);
	public boolean authenticate (Credentials credentials);
	public String getOwnerNameById(int userId);	
	

}
