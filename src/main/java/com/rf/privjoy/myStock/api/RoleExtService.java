package com.rf.privjoy.myStock.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.RoleDTO;

public interface RoleExtService {
	
	/**
	 * Get role with given id
	 * @param roleId id of the role
	 * @return role with given id
	 */
	@RequestMapping(path = "/{roleId}", method = RequestMethod.GET)
	public RoleDTO getRole(@PathVariable("roleId") Long roleId);
	
	/**
	 * Get a list of dtos for all roles
	 * @return list of role dtos
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<RoleDTO> getAllRoles();

}
