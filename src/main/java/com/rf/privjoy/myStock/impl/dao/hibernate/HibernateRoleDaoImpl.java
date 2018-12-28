package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.RoleDao;
import com.rf.privjoy.myStock.impl.persistent.Role;

@Component
public class HibernateRoleDaoImpl extends HibernateGenericDao<Role, Long> implements RoleDao {

	@Override
	public Role getRoleByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(Role.class.getName()).append(" WHERE name = (:name)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("name", name);
		Role role = (Role) query.getSingleResult();
		endTransaction();
		return role;
	}

}
