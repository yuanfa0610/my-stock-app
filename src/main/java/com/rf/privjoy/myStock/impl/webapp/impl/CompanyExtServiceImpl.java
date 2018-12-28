package com.rf.privjoy.myStock.impl.webapp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.CompanyExtService;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Sector;

@RestController
@RequestMapping("/company")
public class CompanyExtServiceImpl implements CompanyExtService {
	
	private MyStockDataService dataService;

	@Override
	public CompanyDTO createCompany(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be defined when creating a new company");
		}
		if (companyDTO.getName() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name should not be null when creating a new company");
		}
		if (companyDTO.getSector() == null || companyDTO.getSector().getSectorId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sector with specified ID should not be null when creating a new company");
		}
		Sector sector = dataService.getSectorById(companyDTO.getSector().getSectorId());
		if (sector == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sector with given ID is not found");
		}
		Company company = dataService.convertToPersistedObject(companyDTO);
		company.setSector(sector);
		company = dataService.saveCompany(company);
		return dataService.convertToDTO(company);
	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating company");
		}
		Company updatedCompany = dataService.convertToPersistedObject(companyDTO);
		Company existingCompany = dataService.getCompanyById(updatedCompany.getId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		dataService.updateCompany(updatedCompany);
		return dataService.convertToDTO(updatedCompany);
	}

	@Override
	public CompanyDTO getCompany(Long companyId) {
		Company company = dataService.getCompanyById(companyId);
		if (company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		return dataService.convertToDTO(company);
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
