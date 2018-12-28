package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.UserDTO;

public interface UserExtService {
	
	/**
	 * Create a new user
	 * @param userDTO dto of the user to create
	 * @return dto of the created user
	 */
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO userDTO);
	
	/**
	 * Update an existing user
	 * @param userDTO dto of the user
	 * @return dto of the updated user
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public UserDTO updateUser(@RequestBody UserDTO userDTO);
	
	/**
	 * Get user with given id
	 * @param userId id of the user
	 * @return user with given id
	 */
	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	public UserDTO getUser(@PathVariable("userId") Long userId);

}