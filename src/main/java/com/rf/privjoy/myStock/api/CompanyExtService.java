package com.rf.privjoy.myStock.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;

public interface CompanyExtService {
	
	/**
	 * Create a new company
	 * @param companyDTO dto of the company to create
	 * @return dto of the created company
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO);
	
	/**
	 * Update an existing company
	 * @param companyDTO dto of company
	 * @return dto of the updated company
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO);
	
	/**
	 * Get company with given id
	 * @param companyId id of the company
	 * @return company with given id
	 */
	@RequestMapping(path = "/{companyId}", method = RequestMethod.GET)
	public CompanyDTO getCompany(@PathVariable("companyId") Long companyId);
	
	/**
	 * Get a list of dtos for all companies
	 * @return list of company dtos
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<CompanyDTO> getAllCompanies();
	
	/**
	 * Create new revenueData
	 * @param revenueDataDTO dto of the revenueData to create
	 * @return dto of the created revenueData
	 */
	@RequestMapping(path = "/revenueData", method = RequestMethod.POST)
	public RevenueDataDTO createRevenueData(@RequestBody RevenueDataDTO revenueDataDTO);
	
	/**
	 * Update existing revenueData
	 * @param revenueDataDTO dto of the revenueData to update
	 * @return dto of the updated revenueData
	 */
	@RequestMapping(path = "/revenueData", method = RequestMethod.PUT)
	public RevenueDataDTO updateRevenueData(@RequestBody RevenueDataDTO revenueDataDTO);
	
	/**
	 * Get revenueData with given id
	 * @param revenueDataId id of the revenueData
	 * @return revenueData with given id
	 */
	@RequestMapping(path = "/revenueData/{revenueDataId}", method = RequestMethod.GET)
	public RevenueDataDTO getRevenueData(@PathVariable("revenueDataId") Long revenueDataId);
	
	/**
	 * Create new assetsData
	 * @param assetsDataDTO dto of the assetsData to create
	 * @return dto of the created assetsData
	 */
	@RequestMapping(path = "/assetsData", method = RequestMethod.POST)
	public AssetsDataDTO createAssetsData(@RequestBody AssetsDataDTO assetsDataDTO);
	
	/**
	 * Update existing assetsData
	 * @param assetsDataDTO dto of the assetsData to update
	 * @return dto of the updated assetsData
	 */
	@RequestMapping(path = "/assetsData", method = RequestMethod.PUT)
	public AssetsDataDTO updateAssetsData(@RequestBody AssetsDataDTO assetsDataDTO);
	
	/**
	 * Get assetsData with given id
	 * @param assetsDataId id of the assetsData
	 * @return assetsData with given id
	 */
	@RequestMapping(path = "/assetsData/{assetsDataId}", method = RequestMethod.GET)
	public AssetsDataDTO getAssetsData(@PathVariable("assetsDataId") Long assetsDataId);
	
}
