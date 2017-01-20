package com.nv.netmdkb.security.sync.bl;

import java.util.HashMap;
import java.util.Map;



import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.facade.SessionBindable;
import com.nv.netmdkb.facade.SecurityFacade;
import com.nv.netmdkb.pl.dao.LoginDao;
import com.nv.netmdkb.rs.dto.Credentials;


public class SyncAuthenticationHandler implements SecurityFacade {
	
	private static Map<String, Lock> map = new HashMap<String,Lock>();
     
	LoginDao loginDao;


	protected static void addToSession(String passPhrase,Lock lock){

		map.put(passPhrase, lock);	
	}


	protected static void updateSession(String passPhrase,Lock lock){
		map.put(passPhrase, lock);
	}


	protected static boolean removeFromSession(String passPhrase){

		map.remove(passPhrase);
		return true;
	}


	@Override
	public SessionBindable getLock(Credentials credentilas) {
		
		Boolean success =loginDao.authenticate(credentilas);
        
		if (!success){
			throw new ServiceException(ErrorCodeEnum.INVALIDDATA);
		}
		
		Lock lock = new Lock();
		lock.setBranchId(credentilas.getBranchId());
		lock.setPassPhrase(credentilas.getPassPhrase());
		lock.setMacId(credentilas.getMacId());
	    return lock;
	}


	

	@Override
	public boolean isAuthenticated(Credentials credentials) {
		Lock lock =map.get(credentials.getPassPhrase());
		if (lock==null){
			return false;
		}
		if (lock.getBranchId()==0 ||credentials.getBranchId()==0){
			return false;
		}
		if (lock.getBranchId()!=credentials.getBranchId()){
			System.out.println("Branch Id :"+lock.getBranchId() );
			System.out.println("Branch Id :"+credentials.getBranchId() );
			return false;
		}
		if (lock.getMacId()==null ||credentials.getMacId()==null){
			return false;
		}
		
		if (!lock.getMacId().equals(credentials.getMacId())){
			return false;
		}	
		return true;
	}


	/**
	 * @return the loginDao
	 */
	public LoginDao getLoginDao() {
		return loginDao;
	}


	/**
	 * @param loginDao the loginDao to set
	 */
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}



}
