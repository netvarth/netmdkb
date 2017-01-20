package com.nv.netmdkb.facade;

import com.nv.netmdkb.rs.dto.Credentials;

public interface SecurityFacade {
	
	public SessionBindable getLock(Credentials credentilas);
	
	public boolean isAuthenticated(Credentials credentilas);


}
