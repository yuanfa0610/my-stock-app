package com.rf.privjoy.myStock.impl.dao.hibernate;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.dao.InventoryDao;
import com.rf.privjoy.myStock.impl.persistent.Inventory;

@Component
public class HibernateInventoryDaoImpl extends HibernateGenericDao<Inventory, Long> implements InventoryDao {

}
