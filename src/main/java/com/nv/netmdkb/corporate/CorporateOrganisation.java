package com.nv.netmdkb.corporate;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;

public class CorporateOrganisation extends HealthCare {
	
	private List<HealthCare> attachedHelathCares=new ArrayList<HealthCare>();
	
	
	
	public List<HealthCare> attachedHelathCares(){
	     return this.attachedHelathCares;
	}
	
	
	public void setAttachedHelathCares(List<HealthCare> attachedHelathCares) {
		this.attachedHelathCares = attachedHelathCares;
	}


   
	public HealthCare getHelathCare(int branchId){
		
	for(HealthCare healthCare : attachedHelathCares){
	
		if (healthCare.getId()==branchId){
			return healthCare;
		}
		
	
	}
	throw new ServiceException(ErrorCodeEnum.INVALIDDATA) ;
	}

    public void addToAttachedHealthCares(HealthCare healthCare){
    	if (healthCare !=null)
    	attachedHelathCares.add(healthCare);
    	
    }
	
}
