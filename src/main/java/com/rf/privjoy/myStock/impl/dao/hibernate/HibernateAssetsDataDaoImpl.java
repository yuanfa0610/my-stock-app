package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.AssetsDataDao;
import com.rf.privjoy.myStock.impl.persistent.AssetsData;

@Component
public class HibernateAssetsDataDaoImpl extends HibernateGenericDao<AssetsData, Long> implements AssetsDataDao {

}
