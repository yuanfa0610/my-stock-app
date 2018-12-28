package com.rf.privjoy.myStock.impl.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.StockSpecificationExtService;
import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.dto.StockSpecificationDTO;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;

@RestController
@RequestMapping("/stockSpecification")
public class StockSpecificationExtServiceImpl implements StockSpecificationExtService {
	
	private MyStockDataService dataService;
	
	@Override
	public StockDTO createStock(StockDTO stockDTO) {
		if (stockDTO.getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the stock should not be null when creating new stock specification for stock");
		}
		if (stockDTO.getStockSpecification() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock specification is not defined");
		}
		if (stockDTO.getStockSpecification().getStockSpecificationId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the stock specification should not be defined when creating new stock specification for stock");
		}
		Stock stock = dataService.convertToPersistedObject(stockDTO);
		Stock existingStock = dataService.getStockById(stock.getId());
		if (existingStock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		if (existingStock.getStockSpecification() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock with given ID already has stock specification associated");
		}
		StockSpecification stockSpecification = stock.getStockSpecification();
		stockSpecification.setStock(existingStock);
		stockSpecification = dataService.saveStockSpecification(stockSpecification);
		existingStock.setStockSpecification(stockSpecification);
		dataService.updateStock(existingStock);
		stock.setStockSpecification(stockSpecification);
		return dataService.convertToDTO(stock);
	}


	@Override
	public StockSpecificationDTO updateStockSpecification(StockSpecificationDTO stockSpecificationDTO) {
		if (stockSpecificationDTO.getStockSpecificationId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the stock specification should not be null when updating stock specification");
		}
		StockSpecification updatedStockSpecification = dataService.convertToPersistedObject(stockSpecificationDTO);
		StockSpecification existingStockSpecification = dataService.getStockSpecificationById(updatedStockSpecification.getId());
		if (existingStockSpecification == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock specification with given ID is not found");
		}
		updatedStockSpecification.setStock(existingStockSpecification.getStock());
		dataService.updateStockSpecification(updatedStockSpecification);
		return dataService.convertToDTO(updatedStockSpecification);
	}


	@Override
	public StockSpecificationDTO getStock(Long stockSpecificationId) {
		StockSpecification stockSpecification = dataService.getStockSpecificationById(stockSpecificationId);
		if (stockSpecification == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock specification with given ID is not found");
		}
		return dataService.convertToDTO(stockSpecification);
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
