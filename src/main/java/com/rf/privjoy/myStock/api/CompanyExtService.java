package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.CompanyDTO;

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
	
}
