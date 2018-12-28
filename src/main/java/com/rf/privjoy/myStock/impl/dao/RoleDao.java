package com.rf.privjoy.myStock.impl.dao;

import com.rf.privjoy.myStock.impl.persistent.Role;

public interface RoleDao extends GeneraicDao<Role, Long> {
	
	/**
	 * Get role with given name
	 * @param name name of the role to search
	 * @return Role
	 */
	public Role getRoleByName(String name);

}
