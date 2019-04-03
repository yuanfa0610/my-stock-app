package com.rf.privjoy.myStock.impl.webapp.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.StockExtService;
import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.dto.StockSpecificationDTO;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateService;

@RestController
@RequestMapping("/stock")
public class StockExtServiceImpl implements StockExtService {

	private MyStockDataService dataService;
	private MyStockConversionService conversionService;
	private MyStockUpdateService updateService;
	
	@Override
	public StockDTO createStock(StockDTO stockDTO) {
		createStockValidate(stockDTO);
		Company company = dataService.getCompanyById(stockDTO.getCompanyId());
		if (company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		stockDTO = convertSymbolToUpperCase(stockDTO);
		Stock stock = conversionService.convertToPersistedObject(stockDTO);
		stock.setCompany(company);
		stock = dataService.saveStock(stock);
		return conversionService.convertToDTO(stock);
	}

	@Override
	public StockDTO getStock(Long stockId) {
		Stock stock = dataService.getStockById(stockId);
		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		return conversionService.convertToDTO(stock);
	}
	
	@Override
	public List<StockDTO> getAllStocks() {
		List<Stock> stocks = dataService.getAllStocks();
		return conversionService.convertToStockDTOs(stocks);
	}
	
	@Override
	public StockSpecificationDTO createStockSpecification(StockSpecificationDTO stockSpecificationDTO) {
		createStockSpecificationValidate(stockSpecificationDTO);
		Stock stock = dataService.getStockById(stockSpecificationDTO.getStockId());
		if (stock == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with given ID is not found");
		}
		StockSpecification stockSpecification = conversionService.convertToPersistedObject(stockSpecificationDTO);
		stockSpecification.setStock(stock);
		stockSpecification = dataService.saveStockSpecification(stockSpecification);
		stock.setStockSpecification(stockSpecification);
		dataService.updateStock(stock);
		return conversionService.convertToDTO(stockSpecification);
	}

	@Override
	public StockSpecificationDTO updateStockSpecification(StockSpecificationDTO stockSpecificationDTO) {
		updateStockSpecificationDTO(stockSpecificationDTO);
		StockSpecification updatedStockSpecification = conversionService.convertToPersistedObject(stockSpecificationDTO);
		StockSpecification existingStockSpecification = dataService.getStockSpecificationById(stockSpecificationDTO.getStockSpecificationId());
		if (existingStockSpecification == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock Specification with given ID is not found");
		}
		existingStockSpecification = updateService.updateExistingStockSpecification(existingStockSpecification, updatedStockSpecification);
		existingStockSpecification = dataService.updateStockSpecification(existingStockSpecification);
		return conversionService.convertToDTO(existingStockSpecification);
	}

	@Override
	public StockSpecificationDTO getStockSpecification(Long stockSpecificationId) {
		StockSpecification stockSpecification = dataService.getStockSpecificationById(stockSpecificationId);
		if (stockSpecification == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock Specification with given ID is not found");
		}
		return conversionService.convertToDTO(stockSpecification);
	}
	
	/**
	 * Validate create stock dto
	 * @param stockDTO dto to validate
	 */
	private void createStockValidate(StockDTO stockDTO) {
		if (stockDTO.getStockId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new stock");
		}
		validateStockDTO(stockDTO);
	}
	
	/**
	 * Validate stock dto
	 * @param stockDTO dto to validate
	 */
	private void validateStockDTO(StockDTO stockDTO) {
		if (StringUtils.isEmpty(stockDTO.getSymbol())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock symbol should not be null");
		}
		if (stockDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comapny ID should not be null in stock");
		}
	}
	
	/**
	 * Validate create stockSpecification dto
	 * @param stockSpecificationDTO dto to validate
	 */
	private void createStockSpecificationValidate(StockSpecificationDTO stockSpecificationDTO) {
		if (stockSpecificationDTO.getStockSpecificationId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new stockSpecification");
		}
		validateStockSpecificationDTO(stockSpecificationDTO);
	}
	
	/**
	 * Validate update stockSpecification dto
	 * @param stockSpecificationDTO dto to validate
	 */
	private void updateStockSpecificationDTO(StockSpecificationDTO stockSpecificationDTO) {
		if (stockSpecificationDTO.getStockSpecificationId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating stockSpecification");
		}
		validateStockSpecificationDTO(stockSpecificationDTO);
	}
	
	/**
	 * Validate stockSpecification dto
	 * @param stockSpecificationDTO dto to validate
	 */
	private void validateStockSpecificationDTO(StockSpecificationDTO stockSpecificationDTO) {
		if (stockSpecificationDTO.getStockId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock ID should not be null in stockSpecification");
		}
		if (stockSpecificationDTO.getPrice() == null || stockSpecificationDTO.getPrice().compareTo(BigDecimal.valueOf(0)) < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock price should not be null or negative");
		}
	}
	
	/**
	 * Convert all symbol characters in stock dto into upper cases
	 * @param stockDTO dto to convert
	 * @return stock dto
	 */
	private StockDTO convertSymbolToUpperCase(StockDTO stockDTO) {
		stockDTO.setSymbol(stockDTO.getSymbol().toUpperCase());
		return stockDTO;
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
