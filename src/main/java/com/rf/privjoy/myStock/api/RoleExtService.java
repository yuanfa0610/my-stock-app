package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.RoleDTO;

public interface RoleExtService {
	
	/**
	 * Create a new role
	 * @param roleDTO dto of the role to create
	 * @return dto of the created role
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO);
	
	/**
	 * Update an existing role
	 * @param roleDTO dto of the role
	 * @return dto of the updated role
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RoleDTO updateRole(@RequestBody RoleDTO roleDTO);
	
	/**
	 * Get role with given id
	 * @param roleId id of the role
	 * @return role with given id
	 */
	@RequestMapping(path = "/{roleId}", method = RequestMethod.GET)
	public RoleDTO getRole(@PathVariable("roleId") Long roleId);

}
