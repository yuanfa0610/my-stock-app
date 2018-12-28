package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.StockDTO;

public interface StockExtService {
	
	/**
	 * Create a new stock
	 * @param stockDTO dto of the stock to create
	 * @return dto of the created stock
	 */
	@RequestMapping(method = RequestMethod.POST)
	public StockDTO createStock(@RequestBody StockDTO stockDTO);
	
	/**
	 * Update an existing stock
	 * @param stockDTO dto of the stock
	 * @return dto of the updated stock
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public StockDTO updateStock(@RequestBody StockDTO stockDTO);
	
	/**
	 * Get stock with given id
	 * @param stockId id of the stock
	 * @return dto of the stock with given id
	 */
	@RequestMapping(path = "/{stockId}", method = RequestMethod.GET)
	public StockDTO getStock(@PathVariable("stockId") Long stockId);

}
