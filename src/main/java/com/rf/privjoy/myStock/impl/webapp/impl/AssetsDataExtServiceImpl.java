package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.AssetsDataExtService;
import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;

@RestController
@RequestMapping("/assetsData")
public class AssetsDataExtServiceImpl implements AssetsDataExtService {
	
	private MyStockDataService dataService;

	@Override
	public CompanyDTO createAssetsData(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the company should not be null when adding assets data to company");
		}
		if (CollectionUtils.isEmpty(companyDTO.getAssetsDataCollection())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assets data is not defined");
		}
		Company company = dataService.convertToPersistedObject(companyDTO);
		Company existingCompany = dataService.getCompanyById(company.getId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		existingCompany = saveNewAssetsDataToExistingCompany(existingCompany, company.getAssetsDataCollection());
		dataService.updateCompany(existingCompany);
		company.setAssetsDataCollection(existingCompany.getAssetsDataCollection());
		return dataService.convertToDTO(company);
	}

	@Override
	public AssetsDataDTO updateAssetsData(AssetsDataDTO assetsDataDTO) {
		if (assetsDataDTO.getAssetsDataId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of the assets data should not be null when updating assets data");
		}
		AssetsData updatedAssetsData = dataService.convertToPersistedObject(assetsDataDTO);
		AssetsData existingAssetsData = dataService.getAssetsDataById(updatedAssetsData.getId());
		if (existingAssetsData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets data with given ID is not found");
		}
		updatedAssetsData.setCompany(existingAssetsData.getCompany());
		dataService.updateAssetsData(updatedAssetsData);
		return dataService.convertToDTO(updatedAssetsData);
	}

	@Override
	public AssetsDataDTO getAssetsData(Long assetsDataId) {
		AssetsData assetsData = dataService.getAssetsDataById(assetsDataId);
		if (assetsData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets data with given ID is not found");
		}
		return dataService.convertToDTO(assetsData);
	}
	
	/**
	 * Save new assetsData to database then add to existing company
	 * @param existingCompany existing company to add assetsData to
	 * @param assetsDataCollection assetsData to save and add
	 * @return existing company with all new assetsData added
	 */
	private Company saveNewAssetsDataToExistingCompany(Company existingCompany, Set<AssetsData> assetsDataCollection) {
		assetsDataCollection.stream()
							.filter(assetsData -> assetsData.getId() == null)
							.forEach(assetsData -> {
								assetsData.setCompany(existingCompany);
								assetsData = dataService.saveAssetsData(assetsData);
								existingCompany.addAssetsData(assetsData);
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
