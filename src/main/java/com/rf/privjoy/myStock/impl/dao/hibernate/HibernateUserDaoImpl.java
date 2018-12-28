package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.UserDao;
import com.rf.privjoy.myStock.impl.persistent.User;

@Component
public class HibernateUserDaoImpl extends HibernateGenericDao<User, Long> implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(User.class.getName()).append(" WHERE username = (:username)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("username", username);
		User user = (User) query.getSingleResult();
		endTransaction();
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(User.class.getName()).append(" WHERE email = (:email)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("email", email);
		User user = (User) query.getSingleResult();
		endTransaction();
		return user;
	}

}
