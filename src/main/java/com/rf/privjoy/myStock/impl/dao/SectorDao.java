package com.rf.privjoy.myStock.impl.dao;

import com.rf.privjoy.myStock.impl.persistent.Sector;

public interface SectorDao extends GeneraicDao<Sector, Long> {
	
	/**
	 * Get sector with given name
	 * @param name name of the sector to search
	 * @return Sector
	 */
	public Sector getSectorByName(String name);

}
