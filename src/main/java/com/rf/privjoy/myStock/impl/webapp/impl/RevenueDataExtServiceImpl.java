package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.RevenueDataExtService;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;

@RestController
@RequestMapping("/revenueData")
public class RevenueDataExtServiceImpl implements RevenueDataExtService {
	
	private MyStockDataService dataService;

	@Override
	public CompanyDTO createRevenueData(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the company should not be null when adding revenue data to company");
		}
		if (CollectionUtils.isEmpty(companyDTO.getRevenueDataCollection())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Revenue data is not defined");
		}
		Company company = dataService.convertToPersistedObject(companyDTO);
		Company existingCompany = dataService.getCompanyById(company.getId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		existingCompany = saveNewRevenueDataToExistingCompany(existingCompany, company.getRevenueDataCollection());
		dataService.updateCompany(existingCompany);
		company.setRevenueDataCollection(existingCompany.getRevenueDataCollection());
		return dataService.convertToDTO(company);
	}

	@Override
	public RevenueDataDTO updateRevenueData(RevenueDataDTO revenueDataDTO) {
		if (revenueDataDTO.getRevenueDataId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the revenue data should not be null when updating revenue data");
		}
		RevenueData updatedRevenueData = dataService.convertToPersistedObject(revenueDataDTO);
		RevenueData existingRevenueData = dataService.getRevenueDataById(updatedRevenueData.getId());
		if (existingRevenueData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revenue data with given ID is not found");
		}
		updatedRevenueData.setCompany(existingRevenueData.getCompany());
		dataService.updateRevenueData(updatedRevenueData);
		return dataService.convertToDTO(updatedRevenueData);
	}

	@Override
	public RevenueDataDTO getRevenueData(Long revenueDataId) {
		RevenueData revenueData = dataService.getRevenueDataById(revenueDataId);
		if (revenueData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revenue data with given ID is not found");
		}
		return dataService.convertToDTO(revenueData);
	}
	
	/**
	 * Save new revenueData to database then add to existing company
	 * @param existingCompany existing company to add revenueData to
	 * @param revenueDataCollection revenueData to save and add
	 * @return existing company with all new revenueData added
	 */
	private Company saveNewRevenueDataToExistingCompany(Company existingCompany, Set<RevenueData> revenueDataCollection) {
		revenueDataCollection.stream()
							.filter(revenueData -> revenueData.getId() == null)
							.forEach(revenueData -> {
								revenueData.setCompany(existingCompany);
								revenueData = dataService.saveRevenueData(revenueData);
								existingCompany.addRevenueData(revenueData);
							});
		return existingCompany;
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}

}
