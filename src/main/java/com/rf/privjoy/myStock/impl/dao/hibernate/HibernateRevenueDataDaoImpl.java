package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.RevenueDataDao;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;

@Component
public class HibernateRevenueDataDaoImpl extends HibernateGenericDao<RevenueData, Long> implements RevenueDataDao {

}
