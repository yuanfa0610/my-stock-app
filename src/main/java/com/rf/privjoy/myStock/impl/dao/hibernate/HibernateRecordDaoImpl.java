package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.RecordDao;
import com.rf.privjoy.myStock.impl.persistent.Record;

@Component
public class HibernateRecordDaoImpl extends HibernateGenericDao<Record, Long> implements RecordDao {

}
