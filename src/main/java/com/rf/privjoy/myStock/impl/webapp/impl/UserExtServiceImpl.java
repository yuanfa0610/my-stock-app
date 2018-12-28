package com.rf.privjoy.myStock.impl.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.UserExtService;
import com.rf.privjoy.myStock.dto.UserDTO;
import com.rf.privjoy.myStock.impl.persistent.User;

@RestController
@RequestMapping("/user")
public class UserExtServiceImpl implements UserExtService {
	
	private MyStockDataService dataService;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		if (userDTO.getUserId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new user");
		}
		if (userDTO.getUsername() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name should not be null when creating a new user");
		}
		if (userDTO.getPassword() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password should not be null when creating a new user");
		}
		if (userDTO.getLastname() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname should not be null when creating a new user");
		}
		if (userDTO.getFirstname() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname should not be null when creating a new user");
		}
		if (userDTO.getEmail() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email should not be null when creating a new user");
		}
		User user = dataService.convertToPersistedObject(userDTO);
		user.setActive(false);
		user = dataService.saveUser(user);
		return dataService.convertToDTO(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		if (userDTO.getUserId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating user");
		}
		User updatedUser = dataService.convertToPersistedObject(userDTO);
		User existingUser = dataService.getUserById(updatedUser.getId());
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		updatedUser.setActive(existingUser.getActive());
		dataService.updateUser(updatedUser);
		return dataService.convertToDTO(updatedUser);
	}

	@Override
	public UserDTO getUser(Long userId) {
		User user = dataService.getUserById(userId);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		return dataService.convertToDTO(user);
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
