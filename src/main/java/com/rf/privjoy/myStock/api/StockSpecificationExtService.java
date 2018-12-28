package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.dto.StockSpecificationDTO;

public interface StockSpecificationExtService {
	
	/**
	 * Create a new stockSpecification
	 * @param stockDTO dto of the stock that contains the stockSpecification to create
	 * @return dto of the stock that contains created stockSpecification
	 */
	@RequestMapping(method = RequestMethod.POST)
	public StockDTO createStock(@RequestBody StockDTO stockDTO);
	
	/**
	 * Update an existing stockSpecification
	 * @param stockSpecificationDTO dto of the stockSpecification
	 * @return dto of the updated stockSpecification
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public StockSpecificationDTO updateStockSpecification(@RequestBody StockSpecificationDTO stockSpecificationDTO);

	/**
	 * Get stockSpecification with given id
	 * @param stockSpecificationId id of the stockSpecification
	 * @return stockSpecification with given id
	 */
	@RequestMapping(path = "/{stockSpecificationId}", method = RequestMethod.GET)
	public StockSpecificationDTO getStock(@PathVariable("stockSpecificationId") Long stockSpecificationId);

}
