package com.rf.privjoy.myStock.impl.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.RoleExtService;
import com.rf.privjoy.myStock.dto.RoleDTO;
import com.rf.privjoy.myStock.impl.persistent.Role;

@RestController
@RequestMapping("/role")
public class RoleExtServiceImpl implements RoleExtService {
	
	private MyStockDataService dataService;

	@Override
	public RoleDTO createRole(RoleDTO roleDTO) {
		if (roleDTO.getRoleId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new role");
		}
		if (roleDTO.getName() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name should not be null when creating a new role");
		}
		Role role = dataService.convertToPersistedObject(roleDTO);
		role = dataService.saveRole(role);
		return dataService.convertToDTO(role);
	}

	@Override
	public RoleDTO updateRole(RoleDTO roleDTO) {
		if (roleDTO.getRoleId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating role");
		}
		Role updatedRole = dataService.convertToPersistedObject(roleDTO);
		Role existingRole = dataService.getRoleById(updatedRole.getId());
		if (existingRole == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with given ID is not found");
		}
		dataService.updateRole(updatedRole);
		return dataService.convertToDTO(updatedRole);
	}

	@Override
	public RoleDTO getRole(Long roleId) {
		Role role = dataService.getRoleById(roleId);
		if (role == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with given ID is not found");
		}
		return dataService.convertToDTO(role);
	}

	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
}
