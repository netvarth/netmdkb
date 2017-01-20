/**
 * GenericDao.java
 *
 * Sep 7, 2012
 *
 * @author Asha Chandran 
 *
 **/
package com.nv.netmdkb.pl.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;


public interface GenericDao extends Serializable{
	public  void save(final Object obj);
	public  void update(final Object obj);
	public  void  delete(Object obj);
	public <T>  T getById(Class<T> className ,int id);
	public <T>  T getByUID(Class<T> className ,int uid);
	public <T>  T getByUID(Class<T> className ,String uid);
	public BigInteger getNextSequence(String sequence);
	public <T> List<T> loadAll(Class<T> className);


}
