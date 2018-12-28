package com.rf.privjoy.myStock.impl.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.StockExtService;
import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Stock;

@RestController
@RequestMapping("/stock")
public class StockExtServiceImpl implements StockExtService {

	private MyStockDataService dataService;
	
	@Override
	public StockDTO createStock(StockDTO stockDTO) {
		if (stockDTO.getStockId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new stock");
		}
		if (stockDTO.getSymbol() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Symbol should not be null when creating a new stock");
		}
		if (stockDTO.getCompany() == null || stockDTO.getCompany().getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company with specified id should not be null when creating a new stock");
		}
		Stock stock = dataService.convertToPersistedObject(stockDTO);
		Company company = dataService.getCompanyById(stockDTO.getCompany().getCompanyId());
		if (company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		stock.setCompany(company);
		dataService.saveStock(stock);
		return dataService.convertToDTO(stock);
	}

	@Override
	public StockDTO updateStock(StockDTO stockDTO) {
		if (stockDTO.getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating stock");
		}
		Stock updatedStock = dataService.convertToPersistedObject(stockDTO);
		Stock existingStock = dataService.getStockById(updatedStock.getId());
		if (existingStock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		dataService.updateStock(updatedStock);
		return dataService.convertToDTO(updatedStock);
	}

	@Override
	public StockDTO getStock(Long stockId) {
		Stock stock = dataService.getStockById(stockId);
		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		return dataService.convertToDTO(stock);
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
