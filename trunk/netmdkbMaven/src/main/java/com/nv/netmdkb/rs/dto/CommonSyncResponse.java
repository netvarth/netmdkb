 /**
* CommonSyncResponseDTO.java
* @author Nithesh Mohanan
*
* Version 1.0 Apr 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmdkb.rs.dto;

import java.util.List;


/**
 * @author Nithesh Mohanan
 *
 */
public class CommonSyncResponse {
	
	private List<SyncResponse> responses;

	
	
	public List<SyncResponse> getResponses() {
		return responses;
	}


	public void setResponses(List<SyncResponse> responses) {
		this.responses = responses;
	}



}
