package com.rf.privjoy.myStock.impl.webapp.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.InventoryExtService;
import com.rf.privjoy.myStock.dto.InventoryDTO;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.User;
import com.rf.privjoy.myStock.impl.utils.Constants;

@RestController
@RequestMapping("/inventory")
public class InventoryExtServiceImpl implements InventoryExtService {
	
	private MyStockDataService dataService;

	@Override
	public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
		if (inventoryDTO.getInventoryId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new inventory");
		}
		if (inventoryDTO.getStatus() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status should not be null when creating a new inventory");
		}
		if (inventoryDTO.getUser() == null || inventoryDTO.getUser().getUserId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with specified ID should not be null when creating a new inventory");
		}
		if (inventoryDTO.getStock() == null || inventoryDTO.getStock().getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock with specified ID should not be null when creating a new inventory");
		}
		String status = dataService.getInventoryStatus(inventoryDTO.getStatus());
		if (status == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid inventory status");
		}
		User user = dataService.getUserById(inventoryDTO.getUser().getUserId());
		Stock stock = dataService.getStockById(inventoryDTO.getStock().getStockId());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		Inventory inventory = dataService.convertToPersistedObject(inventoryDTO);
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		inventory.setTime(time);
		inventory.setStatus(status);
		inventory = dataService.saveInventory(inventory);
		dataService.createRecord(stock, user, stock.getStockSpecification().getPrice(), dataService.convertToRecordStatus(status), time);
		return dataService.convertToDTO(inventory);
	}

	@Override
	public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
		if (inventoryDTO == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating inventory");
		}
		Inventory updatedInventory = dataService.convertToPersistedObject(inventoryDTO);
		Inventory existingInventory = dataService.getInventoryById(updatedInventory.getId());
		if (existingInventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		if (updatedInventory.getStatus() == null || updatedInventory.getStatus().equalsIgnoreCase(existingInventory.getStatus())) {
			return dataService.convertToDTO(updatedInventory);
		}
		String status = dataService.getInventoryStatus(updatedInventory.getStatus());
		if (status == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid inventory status");
		}
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		existingInventory.setTime(time);
		existingInventory.setStatus(status);
		updatedInventory = dataService.updateInventory(existingInventory);
		dataService.createRecord(existingInventory.getStock(), existingInventory.getUser(), existingInventory.getStock().getStockSpecification().getPrice(), dataService.convertToRecordStatus(status), time);
		return dataService.convertToDTO(existingInventory);
	}

	@Override
	public InventoryDTO getInventory(Long inventoryId) {
		Inventory inventory = dataService.getInventoryById(inventoryId);
		if (inventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		return dataService.convertToDTO(inventory);
	}
	
	@Override
	public void removeInventory(Long inventoryId) {
		Inventory inventory = dataService.getInventoryById(inventoryId);
		if (inventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		dataService.deleteInventory(inventory);
		dataService.createRecord(inventory.getStock(), inventory.getUser(), inventory.getStock().getStockSpecification().getPrice(), Constants.RECORD_STATUS_REMOVE, time);
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
