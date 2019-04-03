package com.rf.privjoy.myStock.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.InventoryDTO;

public interface InventoryExtService {
	
	/**
	 * Create a new inventory
	 * @param inventoryDTO dto of the inventory to create
	 * @return dto of the created inventory
	 */
	@RequestMapping(method = RequestMethod.POST)
	public InventoryDTO createInventory(@RequestBody InventoryDTO inventoryDTO);
	
	/**
	 * Update an existing inventory
	 * @param inventoryDTO dto of the inventory
	 * @return dto of the updated inventory
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO);
	
	/**
	 * Get inventory with given id
	 * @param inventoryId id of the inventory
	 * @return inventory with given id
	 */
	@RequestMapping(path = "/{inventoryId}", method = RequestMethod.GET)
	public InventoryDTO getInventory(@PathVariable("inventoryId") Long inventoryId);
	
	/**
	 * Get a list of dtos for all inventories
	 * @return list of inventory dtos
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<InventoryDTO> getAllInventories();
	
	/**
	 * Remove inventory with given id
	 * @param inventoryId id of the inventory to remove
	 */
	@RequestMapping(path = "/{inventoryId}", method = RequestMethod.DELETE)
	public void removeInventory(@PathVariable("inventoryId") Long inventoryId);
	
}
