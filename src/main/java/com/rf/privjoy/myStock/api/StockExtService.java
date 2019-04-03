package com.rf.privjoy.myStock.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.dto.StockSpecificationDTO;

public interface StockExtService {
	
	/**
	 * Create a new stock
	 * @param stockDTO dto of the stock to create
	 * @return dto of the created stock
	 */
	@RequestMapping(method = RequestMethod.POST)
	public StockDTO createStock(@RequestBody StockDTO stockDTO);
	
	/**
	 * Get stock with given id
	 * @param stockId id of the stock
	 * @return dto of the stock with given id
	 */
	@RequestMapping(path = "/{stockId}", method = RequestMethod.GET)
	public StockDTO getStock(@PathVariable("stockId") Long stockId);
	
	/**
	 * Get a list of dtos for all stocks
	 * @return list of stock dtos
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<StockDTO> getAllStocks();
	
	/**
	 * Create a new stockSpecification
	 * @param stockSpecificationDTO dto of the stockSpecification to create
	 * @return dto of the stockSpecification
	 */
	@RequestMapping(path = "/stockSpecification", method = RequestMethod.POST)
	public StockSpecificationDTO createStockSpecification(@RequestBody StockSpecificationDTO stockSpecificationDTO);
	
	/**
	 * Update an existing stockSpecification
	 * @param stockSpecificationDTO dto of the stockSpecification
	 * @return dto of the updated stockSpecification
	 */
	@RequestMapping(path = "/stockSpecification", method = RequestMethod.PUT)
	public StockSpecificationDTO updateStockSpecification(@RequestBody StockSpecificationDTO stockSpecificationDTO);

	/**
	 * Get stockSpecification with given id
	 * @param stockSpecificationId id of the stockSpecification
	 * @return stockSpecification with given id
	 */
	@RequestMapping(path = "/stockSpecification/{stockSpecificationId}", method = RequestMethod.GET)
	public StockSpecificationDTO getStockSpecification(@PathVariable("stockSpecificationId") Long stockSpecificationId);

}
