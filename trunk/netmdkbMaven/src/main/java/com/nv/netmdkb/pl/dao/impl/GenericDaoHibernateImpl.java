/**
 * GenericDaoHibernateImpl.java
 */
package com.nv.netmdkb.pl.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmdkb.exception.ErrorCodeEnum;
import com.nv.netmdkb.exception.ServiceException;
import com.nv.netmdkb.pl.dao.GenericDao;

public class GenericDaoHibernateImpl implements GenericDao {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(GenericDaoHibernateImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public GenericDaoHibernateImpl() {

	}

	@Transactional(readOnly = false)
	public void update(final Object obj) {
		try {
			em.merge(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void delete(Object obj) {
		try {
			em.remove(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void save(Object obj) {
		try {
			em.persist(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getById(Class<T> a, int id) {
		try {
			T obj = em.find(a, id);
			return obj;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public <T> List<T> loadAll(Class<T> className) {
		List<T> response = null;
		try {
			Query query = em.createQuery("from " + className.getName());
			response = query.getResultList();
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		return response;
	}



	
	@Transactional(readOnly = false)
	public <T> T getByUID(Class<T> className, int uid) {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getByUID(Class<T> className, String uid) {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.weblims.pl.dao.GenericDao#getSequence(java.lang.String)
	 */
	@Override
	public BigInteger getNextSequence(String sequence) {
		try {
			Query query = em.createNativeQuery("select nextval('" + sequence
					+ "')");
			return (BigInteger) query.getSingleResult();
		} catch (RuntimeException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> List<T> executeQuery(Class<T> className, Query query) {
		List<T> response = null;
		try {
			response = query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Transactional(readOnly = false)
	public <T> T executeUniqueQuery(Class<T> className, Query query) {
		T response = null;
		try {
			response = (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DATABASEERROR);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	

}