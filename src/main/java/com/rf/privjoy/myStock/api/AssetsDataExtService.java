package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;

public interface AssetsDataExtService {
	
	/**
	 * Create new assetsData
	 * @param companyDTO dto of the company that contains the assetsData to create
	 * @return dto of the company that contains created assetsData
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CompanyDTO createAssetsData(@RequestBody CompanyDTO companyDTO);
	
	/**
	 * Update existing assetsData
	 * @param assetsDataDTO dto of the assetsData to update
	 * @return dto of the updated assetsData
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public AssetsDataDTO updateAssetsData(@RequestBody AssetsDataDTO assetsDataDTO);
	
	/**
	 * Get assetsData with given id
	 * @param assetsDataId id of the assetsData
	 * @return assetsData with given id
	 */
	@RequestMapping(path = "/{assetsDataId}", method = RequestMethod.GET)
	public AssetsDataDTO getAssetsData(@PathVariable("assetsDataId") Long assetsDataId);

}
