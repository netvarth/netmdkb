/**
 * 
 */
package com.nv.netmdkb.pl.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.pl.Query;
import com.nv.netmdkb.pl.dao.LoginDao;
import com.nv.netmdkb.pl.entity.OrganisationLoginTbl;
import com.nv.netmdkb.pl.entity.OrganisationTbl;
import com.nv.netmdkb.pl.entity.OrganisationUserTbl;
import com.nv.netmdkb.rs.dto.Credentials;
import com.nv.netmdkb.rs.dto.User;

/**
 * @author netvarth
 *
 */
public class LoginDaoImpl extends GenericDaoHibernateImpl implements LoginDao{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3318542661543141561L;
	@PersistenceContext()
	private EntityManager em;
	
	@Override
	public int getUserId(String userName) {
		OrganisationLoginTbl loginDetails=getUserByName(userName);
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.INVALIDDATA);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return loginDetails.getId();
	}


	@Override
	public int getOrganisation(String userName) {
		OrganisationTbl organisation=getOrganisationByUserName(userName);
		if(organisation== null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.INVALIDDATA);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return organisation.getId();
	}

	private OrganisationTbl getOrganisationByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_BY_USER);
		query.setParameter("param1", userName);
		return executeUniqueQuery(OrganisationTbl.class, query);
	}


	@Override
	public boolean isValid(User user) {

		OrganisationLoginTbl loginDetails = getOrgUserByUserNameAndPassword(
				user.getPassword(), user.getUserName());
		if(loginDetails==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.INVALIDDATA);
			se.setDisplayErrMsg(true);
			throw se;
		}

		return true;
	}

	private OrganisationLoginTbl getOrgUserByUserNameAndPassword(
			String password, String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_USER_BY_PASSWORD);
		query.setParameter("param1", password);
		query.setParameter("param2", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}


	private OrganisationLoginTbl getUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATIONDETAILS_USER);
		query.setParameter("param1", userName);
		return executeUniqueQuery(OrganisationLoginTbl.class, query);
	}
	
	
	@Override
	public boolean authenticate(Credentials credentials) {
		
		return true;
	}


	@Override
	public String getOwnerNameById(int loginId) {
		
		OrganisationTbl userDetails = getUserDetail(loginId);
		return userDetails.getOwnerFirstName()+" " +userDetails.getOwnerLastName() ;
	}
	
	private OrganisationTbl getUserDetail(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_USERDETAILS);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(OrganisationTbl.class, query);
	}
	
	
}
