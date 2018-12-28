package com.rf.privjoy.myStock.impl.dao;

import com.rf.privjoy.myStock.impl.persistent.Company;

public interface CompanyDao extends GeneraicDao<Company, Long>{
	
	/**
	 * Get company with given name
	 * @param name name of the company to search
	 * @return Company
	 */
	public Company getCompanyByName(String name);

}
