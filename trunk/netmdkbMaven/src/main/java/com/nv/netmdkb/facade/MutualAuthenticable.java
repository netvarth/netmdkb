package com.nv.netmdkb.facade;

public interface MutualAuthenticable {
	
	
	 public boolean authenticate();
	 public boolean lock();
	 public boolean isAuthenticated();
	
}
