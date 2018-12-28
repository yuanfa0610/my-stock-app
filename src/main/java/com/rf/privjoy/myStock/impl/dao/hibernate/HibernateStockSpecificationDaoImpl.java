package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.StockSpecificationDao;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;

@Component
public class HibernateStockSpecificationDaoImpl extends HibernateGenericDao<StockSpecification, Long> implements StockSpecificationDao {

}
