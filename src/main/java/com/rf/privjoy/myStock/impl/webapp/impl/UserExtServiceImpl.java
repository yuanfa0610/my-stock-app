package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.UserExtService;
import com.rf.privjoy.myStock.dto.UserDTO;
import com.rf.privjoy.myStock.impl.persistent.User;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateService;

@RestController
@RequestMapping("/user")
public class UserExtServiceImpl implements UserExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;
	private MyStockUpdateService updateService;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		createUserValidate(userDTO);
		User user = conversionService.convertToPersistedObject(userDTO);
		user.setActive(false);
		user = dataService.saveUser(user);
		return conversionService.convertToDTO(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		updateUserValidate(userDTO);
		User updatedUser = conversionService.convertToPersistedObject(userDTO);
		User existingUser = dataService.getUserById(updatedUser.getId());
		if (existingUser == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		existingUser = updateService.updateExistingUser(existingUser, updatedUser);
		existingUser = dataService.updateUser(existingUser);
		return conversionService.convertToDTO(existingUser);
	}

	@Override
	public UserDTO getUser(Long userId) {
		User user = dataService.getUserById(userId);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		return conversionService.convertToDTO(user);
	}
	
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = dataService.getAllUsers();
		return conversionService.convertToUserDTOs(users);
	}
	
	/**
	 * Validate create user dto
	 * @param userDTO dto to validate
	 */
	private void createUserValidate(UserDTO userDTO) {
		if (userDTO.getUserId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new user");
		}
		validateUserDTO(userDTO);
	}
	
	/**
	 * Validate update user dto
	 * @param userDTO dto to validate
	 */
	private void updateUserValidate(UserDTO userDTO) {
		if (userDTO.getUserId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating user");
		}
		validateUserDTO(userDTO);
	}
	
	/**
	 * Validate user dto
	 * @param userDTO dto to validate
	 */
	private void validateUserDTO(UserDTO userDTO) {
		if (StringUtils.isEmpty(userDTO.getUsername())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User name should not be null or empty");
		}
		if (StringUtils.isEmpty(userDTO.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User password should not be null or empty");
		}
		if (StringUtils.isEmpty(userDTO.getLastname())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User lastname should not be null or empty");
		}
		if (StringUtils.isEmpty(userDTO.getFirstname())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User firstname should not be null or empty");
		}
		if (StringUtils.isEmpty(userDTO.getEmail())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User email should not be null or empty");
		}
		if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User roles should not be null or empty");
		}
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
	/**
	 * @param conversionService the conversionService to set
	 */
	@Autowired
	public void setMyStockConversionService(MyStockConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
	/**
	 * @param updateService the updateService to set
	 */
	@Autowired
	public void setMyStockUpdateService(MyStockUpdateService updateService) {
		this.updateService = updateService;
	}

}
