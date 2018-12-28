package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.SectorDao;
import com.rf.privjoy.myStock.impl.persistent.Sector;

@Component
public class HibernateSectorDaoImpl extends HibernateGenericDao<Sector, Long> implements SectorDao {

	@Override
	public Sector getSectorByName(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(Sector.class.getName()).append(" WHERE name = (:name)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("name", name);
		Sector sector = (Sector) query.getSingleResult();
		endTransaction();
		return sector;
	}

}
