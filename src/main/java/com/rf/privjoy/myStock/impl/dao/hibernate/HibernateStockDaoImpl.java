package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.StockDao;
import com.rf.privjoy.myStock.impl.persistent.Stock;

@Component
public class HibernateStockDaoImpl extends HibernateGenericDao<Stock, Long> implements StockDao {

	@Override
	public Stock getStockBySymbol(String symbol) {
		if (StringUtils.isBlank(symbol)) {
			return null;
		}
		startTransaction();
		StringBuilder queryString = new StringBuilder("FROM ").append(Stock.class.getName()).append(" WHERE symbol = (:symbol)");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("symbol", symbol);
		Stock stock = (Stock) query.getSingleResult();
		endTransaction();
		return stock;
	}

}
