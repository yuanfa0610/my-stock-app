package com.rf.privjoy.myStock.impl.dao.hibernate;

import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
	
	private final static Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
 
	private static final SessionFactory sessionFactory;
 
	static {
		try {
			sessionFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
			LOGGER.info("Session factory created");
		} catch (Throwable ex) {
			LOGGER.info("Session factory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	/**
	 * Get SessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
