package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;

public interface RevenueDataExtService {
	
	/**
	 * Create new revenueData
	 * @param companyDTO dto of the company that contains the revenueData to create
	 * @return dto of the company that contains created revenueData
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CompanyDTO createRevenueData(@RequestBody CompanyDTO companyDTO);
	
	/**
	 * Update existing revenueData
	 * @param revenueDataDTO dto of the revenueData to update
	 * @return dto of the updated revenueData
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public RevenueDataDTO updateRevenueData(@RequestBody RevenueDataDTO revenueDataDTO);
	
	/**
	 * Get revenueData with given id
	 * @param revenueDataId id of the revenueData
	 * @return revenueData with given id
	 */
	@RequestMapping(path = "/{revenueDataId}", method = RequestMethod.GET)
	public RevenueDataDTO getRevenueData(@PathVariable("revenueDataId") Long revenueDataId);

}
