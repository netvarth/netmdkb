package com.nv.netmdkb.analytic.bl.dataCluster;

import com.nv.netmdkb.exception.ServiceException;



public interface Parser {

	public boolean evaluate(Predicate predicate,String... operants) throws ServiceException;
		
		
	
	
}
