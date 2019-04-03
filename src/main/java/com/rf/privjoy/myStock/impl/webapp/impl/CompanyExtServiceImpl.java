package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.CompanyExtService;
import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;
import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateService;

@RestController
@RequestMapping("/company")
public class CompanyExtServiceImpl implements CompanyExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;
	private MyStockUpdateService updateService;

	@Override
	public CompanyDTO createCompany(CompanyDTO companyDTO) {
		createCompanyValidate(companyDTO);
		Sector sector = dataService.getSectorById(companyDTO.getSectorId());
		if (sector == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sector with given ID is not found");
		}
		Company company = conversionService.convertToPersistedObject(companyDTO);
		company.setSector(sector);
		company = dataService.saveCompany(company);
		return conversionService.convertToDTO(company);
	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDTO) {
		updateCompanyValidate(companyDTO);
		Company updatedCompany = conversionService.convertToPersistedObject(companyDTO);
		Company existingCompany = dataService.getCompanyById(updatedCompany.getId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		existingCompany = updateService.updateExistingCompany(existingCompany, updatedCompany);
		existingCompany = dataService.updateCompany(existingCompany);
		return conversionService.convertToDTO(existingCompany);
	}

	@Override
	public CompanyDTO getCompany(Long companyId) {
		Company company = dataService.getCompanyById(companyId);
		if (company == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		return conversionService.convertToDTO(company);
	}
	
	@Override
	public List<CompanyDTO> getAllCompanies() {
		List<Company> companies = dataService.getAllCompanies();
		return conversionService.convertToCompanyDTOs(companies);
	}
	
	@Override
	public RevenueDataDTO createRevenueData(RevenueDataDTO revenueDataDTO) {
		createRevenueDataValidate(revenueDataDTO);
		Company existingCompany = dataService.getCompanyById(revenueDataDTO.getCompanyId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		RevenueData revenueData = conversionService.convertToPersistedObject(revenueDataDTO);
		revenueData = dataService.saveRevenueData(revenueData);
		return conversionService.convertToDTO(revenueData);
	}

	@Override
	public RevenueDataDTO updateRevenueData(RevenueDataDTO revenueDataDTO) {
		updateRevenueDataValidate(revenueDataDTO);
		RevenueData updatedRevenueData = conversionService.convertToPersistedObject(revenueDataDTO);
		RevenueData existingRevenueData = dataService.getRevenueDataById(updatedRevenueData.getId());
		if (existingRevenueData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revenue data with given ID is not found");
		}
		existingRevenueData = updateService.updateExistingRevenueData(existingRevenueData, updatedRevenueData);
		existingRevenueData= dataService.updateRevenueData(existingRevenueData);
		return conversionService.convertToDTO(existingRevenueData);
	}

	@Override
	public RevenueDataDTO getRevenueData(Long revenueDataId) {
		RevenueData revenueData = dataService.getRevenueDataById(revenueDataId);
		if (revenueData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Revenue data with given ID is not found");
		}
		return conversionService.convertToDTO(revenueData);
	}
	
	@Override
	public AssetsDataDTO createAssetsData(AssetsDataDTO assetsDataDTO) {
		createAssetsDataValidate(assetsDataDTO);
		Company existingCompany = dataService.getCompanyById(assetsDataDTO.getCompanyId());
		if (existingCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company with given ID is not found");
		}
		AssetsData assetsData = conversionService.convertToPersistedObject(assetsDataDTO);
		assetsData = dataService.saveAssetsData(assetsData);
		return conversionService.convertToDTO(assetsData);
	}

	@Override
	public AssetsDataDTO updateAssetsData(AssetsDataDTO assetsDataDTO) {
		updateAssetsDataValidate(assetsDataDTO);
		AssetsData updatedAssetsData = conversionService.convertToPersistedObject(assetsDataDTO);
		AssetsData existingAssetsData = dataService.getAssetsDataById(updatedAssetsData.getId());
		if (existingAssetsData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets data with given ID is not found");
		}
		existingAssetsData = updateService.updateExistingAssetsData(existingAssetsData, updatedAssetsData);
		existingAssetsData = dataService.updateAssetsData(existingAssetsData);
		return conversionService.convertToDTO(existingAssetsData);
	}

	@Override
	public AssetsDataDTO getAssetsData(Long assetsDataId) {
		AssetsData assetsData = dataService.getAssetsDataById(assetsDataId);
		if (assetsData == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Assets data with given ID is not found");
		}
		return conversionService.convertToDTO(assetsData);
	}
	
	/**
	 * Validate create company dto
	 * @param companyDTO dto to validate
	 */
	private void createCompanyValidate(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID should not be defined when creating a new company");
		}
		validateCompanyDTO(companyDTO);
		Company existingCompany = dataService.getCompanyByName(companyDTO.getName());
		if (existingCompany != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company with given name already exists");
		}
	}
	
	/**
	 * Validate update company dto
	 * @param companyDTO dto to validate
	 */
	private void updateCompanyValidate(CompanyDTO companyDTO) {
		if (companyDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID should not be null when updating a company");
		}
		validateCompanyDTO(companyDTO);
	}
	
	/**
	 * Validate company dto
	 * @param companyDTO dto to validate
	 */
	private void validateCompanyDTO(CompanyDTO companyDTO) {
		if (StringUtils.isEmpty(companyDTO.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company name should not be null or empty");
		}
		if (StringUtils.isEmpty(String.valueOf(companyDTO.getSectorId()))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sector ID should not be null or empty");
		}
	}
	
	/**
	 * Validate create revenueData dto
	 * @param revenueDataDTO dto to validate
	 */
	private void createRevenueDataValidate(RevenueDataDTO revenueDataDTO) {
		if (revenueDataDTO.getRevenueDataId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RevenueData ID should not be defined when creating a new revenueData");
		}
		validateRevenueDataDTO(revenueDataDTO);
	}
	
	/**
	 * Validate update revenueData dto
	 * @param revenueDataDTO dto to validate
	 */
	private void updateRevenueDataValidate(RevenueDataDTO revenueDataDTO) {
		if (revenueDataDTO.getRevenueDataId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RevenueData ID should not be null when updating revenueData");
		}
		validateRevenueDataDTO(revenueDataDTO);
	}
	
	/**
	 * Validate revenueData dto
	 * @param revenueDataDTO dto to validate
	 */
	private void validateRevenueDataDTO(RevenueDataDTO revenueDataDTO) {
		if (revenueDataDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID should not be null or empty");
		}
		if (StringUtils.isEmpty(revenueDataDTO.getYear())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Revenue data year should not be null or empty");
		}
	}
	
	/**
	 * Validate create assetsDataDTO dto
	 * @param assetsDataDTO dto to validate
	 */
	private void createAssetsDataValidate(AssetsDataDTO assetsDataDTO) {
		if (assetsDataDTO.getAssetsDataId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AssetsData ID should not be defined when creating a new assetsData");
		}
		validateAssetsDataDTO(assetsDataDTO);
	}
	
	/**
	 * Validate update assetsDataDTO dto
	 * @param assetsDataDTO dto to validate
	 */
	private void updateAssetsDataValidate(AssetsDataDTO assetsDataDTO) {
		if (assetsDataDTO.getAssetsDataId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AssetsData ID should not be null when updating assetsData");
		}
		validateAssetsDataDTO(assetsDataDTO);
	}
	
	/**
	 * Validate assetsData dto
	 * @param assetsData dto to validate
	 */
	private void validateAssetsDataDTO(AssetsDataDTO assetsDataDTO) {
		if (assetsDataDTO.getCompanyId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company ID should not be null or empty");
		}
		if (StringUtils.isEmpty(assetsDataDTO.getYear())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assets data year should not be null or empty");
		}
	}
	
	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
	/**
	 * @param conversionService the conversionService to set
	 */
	@Autowired
	public void setMyStockConversionService(MyStockConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
	/**
	 * @param updateService the updateService to set
	 */
	@Autowired
	public void setMyStockUpdateService(MyStockUpdateService updateService) {
		this.updateService = updateService;
	}

}
