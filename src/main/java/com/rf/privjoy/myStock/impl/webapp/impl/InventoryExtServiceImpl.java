package com.rf.privjoy.myStock.impl.webapp.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateService;

@RestController
@RequestMapping("/inventory")
public class InventoryExtServiceImpl implements InventoryExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;
	private MyStockUpdateService updateService;

	@Override
	public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
		createInventoryValidate(inventoryDTO);
		User user = dataService.getUserById(inventoryDTO.getUserId());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given ID is not found");
		}
		Stock stock = dataService.getStockById(inventoryDTO.getStockId());
		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		inventoryDTO = convertStatusToUpperCase(inventoryDTO);
		Inventory inventory = conversionService.convertToPersistedObject(inventoryDTO);
		Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
		inventory.setLastUpdated(lastUpdated);
		inventory = dataService.saveInventory(inventory);
		Timestamp time = lastUpdated;
		dataService.createRecord(stock, user, stock.getStockSpecification().getPrice(), dataService.convertToRecordStatus(inventoryDTO.getStatus()), time);
		return conversionService.convertToDTO(inventory);
	}

	@Override
	public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
		updateInventoryValidate(inventoryDTO);
		inventoryDTO = convertStatusToUpperCase(inventoryDTO);
		Inventory updatedInventory = conversionService.convertToPersistedObject(inventoryDTO);
		Inventory existingInventory = dataService.getInventoryById(updatedInventory.getId());
		if (existingInventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		existingInventory = updateService.updateExistingInventory(existingInventory, updatedInventory);
		Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
		existingInventory.setLastUpdated(lastUpdated);
		existingInventory = dataService.updateInventory(existingInventory);
		Timestamp time = lastUpdated;
		dataService.createRecord(existingInventory.getStock(), existingInventory.getUser(), existingInventory.getStock().getStockSpecification().getPrice(), dataService.convertToRecordStatus(inventoryDTO.getStatus()), time);
		return conversionService.convertToDTO(existingInventory);
	}

	@Override
	public InventoryDTO getInventory(Long inventoryId) {
		Inventory inventory = dataService.getInventoryById(inventoryId);
		if (inventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		return conversionService.convertToDTO(inventory);
	}
	
	@Override
	public List<InventoryDTO> getAllInventories() {
		List<Inventory> inventories = dataService.getAllInventoies();
		return conversionService.convertToInventoryDTOs(inventories);
	}
	
	@Override
	public void removeInventory(Long inventoryId) {
		Inventory inventory = dataService.getInventoryById(inventoryId);
		if (inventory == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory with given ID is not found");
		}
		dataService.deleteInventory(inventory);
		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
		dataService.createRecord(inventory.getStock(), inventory.getUser(), inventory.getStock().getStockSpecification().getPrice(), Constants.RECORD_STATUS_REMOVE, time);
	}
	
	/**
	 * Validate inventory dto
	 * @param inventoryDTO dto to validate
	 */
	private void validateInventory(InventoryDTO inventoryDTO) {
		if (inventoryDTO.getUserId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory user ID should not be null");
		}
		if (inventoryDTO.getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory stock ID should not be null");
		}
		if (StringUtils.isEmpty(inventoryDTO.getStatus())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory status should not be null");
		}
		String status = dataService.getInventoryStatus(inventoryDTO.getStatus());
		if (status == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid inventory status");
		}
	}
	
	/**
	 * Validate create inventory dto
	 * @param inventoryDTO dto to validate
	 */
	private void createInventoryValidate(InventoryDTO inventoryDTO) {
		if (inventoryDTO.getInventoryId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new inventory");
		}
		validateInventory(inventoryDTO);
	}
	
	/**
	 * Validate update inventory dto
	 * @param inventoryDTO dto to validate
	 */
	private void updateInventoryValidate(InventoryDTO inventoryDTO) {
		if (inventoryDTO.getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating inventory");
		}
		validateInventory(inventoryDTO);
	}
	
	/**
	 * Convert all status characters in inventory dto into upper cases
	 * @param inventoryDTO dto to convert
	 * @return inventory dto
	 */
	private InventoryDTO convertStatusToUpperCase(InventoryDTO inventoryDTO) {
		inventoryDTO.setStatus(inventoryDTO.getStatus().toUpperCase());
		return inventoryDTO;
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
