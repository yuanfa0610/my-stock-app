package com.rf.privjoy.myStock.impl.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.util.CollectionUtils;

import com.rf.privjoy.myStock.impl.dao.GeneraicDao;

public class HibernateGenericDao<T, ID extends Serializable> implements GeneraicDao<T, ID> {
	
	private final static Logger LOGGER = Logger.getLogger(HibernateGenericDao.class.getName());
	
	private SessionFactory sessionFactory;
	private Session session;
	private Class<T> modelClass;
	
	public HibernateGenericDao() {
		setSessionFactory(HibernateUtil.getSessionFactory());
		this.modelClass = findModelClass(getClass());
	}

	/**
	 * Set session factory
	 * @param sessionFactory the sessionFactory to set
	 */
    private void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }
    
    /**
     * Get session factory
     * @return SessionFactory
     */
	private SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	/**
	 * Set session
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
	/**
	 * Get session
	 * @return Session
	 */
	public Session getSession() {
		return this.session;
	}
	
    /**
     * @param <V> class of model
     * @param daoClass dao class
     * @return class of model
     */
    private static <V> Class<V> findModelClass(Class<?> daoClass) {
      if (daoClass.equals(HibernateGenericDao.class)) {
        return null;
      } else {
        Type type = daoClass.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
          @SuppressWarnings("unchecked") Class<V> genericType = (Class<V>) ((ParameterizedType) type).getActualTypeArguments()[0];
          return genericType;
        } else {
          return findModelClass((Class<?>) type);
        }
      }
    }
	
	/**
	 * Create a new session then begin transaction
	 */
	public void startTransaction() {
		setSession(getSessionFactory().getCurrentSession());
		LOGGER.info("New session created");
		getSession().beginTransaction();
		LOGGER.info("Transaction begins");
	}
	
	/**
	 * End the transaction then close the session
	 */
	public void endTransaction() {
		getSession().getTransaction().commit();
		LOGGER.info("Transaction committed");
		getSession().close();
		LOGGER.info("Session closed");
	}

	@Override
	public ID save(T entity) {
		startTransaction();
		@SuppressWarnings("unchecked")
		ID generatedId = (ID) getSession().save(entity);
		endTransaction();
		return generatedId;
	}

	@Override
	public void update(T entity) {
		startTransaction();
		getSession().update(entity);
		endTransaction();
	}

	@Override
	public void saveOrUpdate(T entity) {
		startTransaction();
		getSession().saveOrUpdate(entity);
		endTransaction();
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entities) {
		startTransaction();
		entities.stream().filter(entity -> entity != null).forEach(entity -> getSession().saveOrUpdate(entity));
		endTransaction();
	}

	@Override
	public T getById(ID id) {
		startTransaction();
		T result =  getSession().find(modelClass, id);
		endTransaction();
		return result;
	}

	@Override
	public List<T> getByIds(Collection<ID> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(modelClass.getName()).append(" WHERE id in (:ids)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("ids", ids);
		List<T> resultList = query.list();
		endTransaction();
		return resultList;
	}

	@Override
	public List<ID> getAllIds() {
		startTransaction();
		StringBuilder queryString = new StringBuilder("SELECT id FROM ").append(modelClass.getName());
		Query query = getSession().createQuery(queryString.toString());
		List<ID> resultList = query.list();
		endTransaction();
		return resultList;
	}

	@Override
	public List<T> getAll() {
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(modelClass.getName());
		Query query = getSession().createQuery(queryString.toString());
		List<T> resultList = query.list();
		endTransaction();
		return resultList;
	}

	@Override
	public void delete(T entity) {
		startTransaction();
		getSession().delete(entity);
		endTransaction();
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		startTransaction();
		entities.stream().filter(entity -> entity != null).forEach(entity -> getSession().delete(entity));
		endTransaction();
	}

}
