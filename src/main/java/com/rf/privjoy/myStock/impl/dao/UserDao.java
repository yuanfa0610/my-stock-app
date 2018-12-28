package com.rf.privjoy.myStock.impl.dao;

import com.rf.privjoy.myStock.impl.persistent.User;

public interface UserDao extends GeneraicDao<User, Long> {

	/**
	 * Get user with given username
	 * @param username username of the user to search
	 * @return User
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Get user with given email
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
}
