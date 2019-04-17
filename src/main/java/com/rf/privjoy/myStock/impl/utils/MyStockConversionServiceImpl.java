package com.rf.privjoy.myStock.impl.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.InventoryDTO;
import com.rf.privjoy.myStock.dto.RecordDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;
import com.rf.privjoy.myStock.dto.RoleDTO;
import com.rf.privjoy.myStock.dto.SectorDTO;
import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.dto.StockSpecificationDTO;
import com.rf.privjoy.myStock.dto.UserDTO;
import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.Record;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Role;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.persistent.User;

@Component
@Transactional
public class MyStockConversionServiceImpl implements MyStockConversionService {
	
	private MyStockDataService dataService;

	@Override
	public SectorDTO convertToDTO(Sector sector) {
		if (sector == null) {
			return null;
		}
		SectorDTO sectorDTO = new SectorDTO();
		sectorDTO.setId(sector.getId());
		sectorDTO.setName(sector.getName());
		return sectorDTO;
	}
	
	@Override
	public List<SectorDTO> convertToSectorDTOs(List<Sector> sectors) {
		if (sectors == null) {
			return null;
		}
		List<SectorDTO> sectorDTOs = sectors.stream().map(sector -> convertToDTO(sector)).collect(Collectors.toList());
		return sectorDTOs;
	}

	@Override
	public Sector convertToPersistedObject(SectorDTO sectorDTO) {
		if (sectorDTO == null) {
			return null;
		}
		Sector sector = new Sector();
		sector.setId(sectorDTO.getSectorId());
		sector.setName(sectorDTO.getName());
		return sector;
	}

	@Override
	public CompanyDTO convertToDTO(Company company) {
		if (company == null) {
			return null;
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyId(company.getId());
		companyDTO.setName(company.getName());
		companyDTO.setSectorId(company.getSector().getId());
		companyDTO.setYearOfFounded(company.getYearOfFounded());
		companyDTO.setYearOfIpo(company.getYearOfIpo());
		companyDTO.setLink(company.getLink());
		if (company.getRevenueDataCollection() != null) {
			companyDTO.setRevenueDataCollection(company.getRevenueDataCollection()
					.stream()
					.filter(revenueData -> revenueData != null)
					.map(revenueData -> convertToDTO(revenueData))
					.collect(Collectors.toSet()));;
		}
		if (company.getAssetsDataCollection() != null) {
			companyDTO.setAssetsDataCollection(company.getAssetsDataCollection()
					.stream()
					.filter(assetsData -> assetsData != null)
					.map(assetsData -> convertToDTO(assetsData))
					.collect(Collectors.toSet()));
		}
		return companyDTO;
	}
	
	@Override
	public List<CompanyDTO> convertToCompanyDTOs(List<Company> companies) {
		if (companies == null) {
			return null;
		}
		List<CompanyDTO> companyDTOs = companies.stream().map(company -> convertToDTO(company)).collect(Collectors.toList());
		return companyDTOs;
	}

	@Override
	public Company convertToPersistedObject(CompanyDTO companyDTO) {
		if (companyDTO == null) {
			return null;
		}
		Sector sector = dataService.getSectorById(companyDTO.getSectorId());
		Company company = new Company();
		company.setId(companyDTO.getCompanyId());
		company.setName(companyDTO.getName());
		company.setSector(sector);
		company.setYearOfFounded(companyDTO.getYearOfFounded());
		company.setYearOfIpo(companyDTO.getYearOfIpo());
		company.setLink(companyDTO.getLink());
		if (companyDTO.getRevenueDataCollection() != null) {
			company.setRevenueDataCollection(companyDTO.getRevenueDataCollection()
					.stream()
					.filter(revenueDataDTO -> revenueDataDTO != null)
					.map(revenueDataDTO -> convertToPersistedObject(revenueDataDTO))
					.collect(Collectors.toSet()));
		}
		if (companyDTO.getAssetsDataCollection() != null) {
			company.setAssetsDataCollection(companyDTO.getAssetsDataCollection()
					.stream()
					.filter(assetsDataDTO -> assetsDataDTO != null)
					.map(assetsDataDTO -> convertToPersistedObject(assetsDataDTO))
					.collect(Collectors.toSet()));;	
		}
		return company;
	}

	@Override
	public RevenueDataDTO convertToDTO(RevenueData revenueData) {
		if (revenueData == null) {
			return null;
		}
		RevenueDataDTO revenueDataDTO = new RevenueDataDTO();
		revenueDataDTO.setRevenueDataId(revenueData.getId());
		revenueDataDTO.setCompanyId(revenueData.getCompany().getId());
		revenueDataDTO.setYear(revenueData.getYear());
		revenueDataDTO.setRevenue(revenueData.getRevenue());
		revenueDataDTO.setCost(revenueData.getCost());
		revenueDataDTO.setGrossProfit(revenueData.getGrossProfit());
		revenueDataDTO.setNetIncome(revenueData.getNetIncome());
		revenueDataDTO.setBasicEps(revenueData.getBasicEps());
		revenueDataDTO.setDividendPayout(revenueData.getDividendPayout());
		return revenueDataDTO;
	}

	@Override
	public RevenueData convertToPersistedObject(RevenueDataDTO revenueDataDTO) {
		if (revenueDataDTO == null) {
			return null;
		}
		Company company = dataService.getCompanyById(revenueDataDTO.getCompanyId());
		RevenueData revenueData = new RevenueData();
		revenueData.setId(revenueDataDTO.getRevenueDataId());
		revenueData.setCompany(company);
		revenueData.setYear(revenueDataDTO.getYear());
		revenueData.setRevenue(revenueDataDTO.getRevenue());
		revenueData.setCost(revenueDataDTO.getCost());
		revenueData.setGrossProfit(revenueDataDTO.getGrossProfit());
		revenueData.setNetIncome(revenueDataDTO.getNetIncome());
		revenueData.setBasicEps(revenueDataDTO.getBasicEps());
		revenueData.setDividendPayout(revenueDataDTO.getDividendPayout());
		return revenueData;
	}

	@Override
	public AssetsDataDTO convertToDTO(AssetsData assetsData) {
		if (assetsData == null) {
			return null;
		}
		AssetsDataDTO assetsDataDTO = new AssetsDataDTO();
		assetsDataDTO.setAssetsDataId(assetsData.getId());
		assetsDataDTO.setCompanyId(assetsData.getCompany().getId());
		assetsDataDTO.setYear(assetsData.getYear());
		assetsDataDTO.setCashOnHand(assetsData.getCashOnHand());
		assetsDataDTO.setCurrentAssets(assetsData.getCurrentAssets());
		assetsDataDTO.setTotalAssets(assetsData.getTotalAssets());
		assetsDataDTO.setCurrentLiability(assetsData.getCurrentLiability());
		assetsDataDTO.setLongTermDebt(assetsData.getLongTermDebt());
		assetsDataDTO.setEquity(assetsData.getEquity());
		return assetsDataDTO;
	}

	@Override
	public AssetsData convertToPersistedObject(AssetsDataDTO assetsDataDTO) {
		if (assetsDataDTO == null) {
			return null;
		}
		Company company = dataService.getCompanyById(assetsDataDTO.getCompanyId());
		AssetsData assetsData = new AssetsData();
		assetsData.setId(assetsDataDTO.getAssetsDataId());
		assetsData.setCompany(company);
		assetsData.setYear(assetsDataDTO.getYear());
		assetsData.setCashOnHand(assetsDataDTO.getCashOnHand());
		assetsData.setCurrentAssets(assetsDataDTO.getCurrentAssets());
		assetsData.setTotalAssets(assetsDataDTO.getTotalAssets());
		assetsData.setCurrentLiability(assetsDataDTO.getCurrentLiability());
		assetsData.setLongTermDebt(assetsDataDTO.getLongTermDebt());
		assetsData.setEquity(assetsDataDTO.getEquity());
		return assetsData;
	}

	@Override
	public StockDTO convertToDTO(Stock stock) {
		if (stock == null) {
			return null;
		}
		StockDTO stockDTO = new StockDTO();
		stockDTO.setStockId(stock.getId());
		stockDTO.setSymbol(stock.getSymbol());
		stockDTO.setCompanyId(stock.getCompany().getId());;
		stockDTO.setStockSpecification(convertToDTO(stock.getStockSpecification()));
		return stockDTO;
	}
	
	@Override
	public List<StockDTO> convertToStockDTOs(List<Stock> stocks) {
		if (stocks == null) {
			return null;
		}
		List<StockDTO> stockDTOs = stocks.stream().map(stock -> convertToDTO(stock)).collect(Collectors.toList());
		return stockDTOs;
	}

	@Override
	public Stock convertToPersistedObject(StockDTO stockDTO) {
		if (stockDTO == null) {
			return null;
		}
		Company company = dataService.getCompanyById(stockDTO.getCompanyId());
		Stock stock = new Stock();
		stock.setId(stockDTO.getStockId());
		stock.setSymbol(stockDTO.getSymbol());
		stock.setCompany(company);
		return stock;
	}

	@Override
	public StockSpecificationDTO convertToDTO(StockSpecification stockSpecification) {
		if (stockSpecification == null) {
			return null;
		}
		StockSpecificationDTO stockSpecificationDTO = new StockSpecificationDTO();
		stockSpecificationDTO.setStockSpecificationId(stockSpecification.getId());
		stockSpecificationDTO.setStockId(stockSpecification.getStock().getId());
		stockSpecificationDTO.setPrice(stockSpecification.getPrice());
		stockSpecificationDTO.setPriceEarningRatio(stockSpecification.getPriceEarningRatio());
		stockSpecificationDTO.setPriceBookRatio(stockSpecification.getPriceBookRatio());
		return stockSpecificationDTO;
	}

	@Override
	public StockSpecification convertToPersistedObject(StockSpecificationDTO stockSpecificationDTO) {
		if (stockSpecificationDTO == null) {
			return null;
		}
		Stock stock = dataService.getStockById(stockSpecificationDTO.getStockId());
		StockSpecification stockSpecification= new StockSpecification();
		stockSpecification.setId(stockSpecificationDTO.getStockSpecificationId());
		stockSpecification.setStock(stock);
		stockSpecification.setPrice(stockSpecificationDTO.getPrice());
		stockSpecification.setPriceEarningRatio(stockSpecificationDTO.getPriceEarningRatio());
		stockSpecification.setPriceBookRatio(stockSpecificationDTO.getPriceBookRatio());
		return stockSpecification;
	}

	@Override
	public UserDTO convertToDTO(User user) {
		if (user == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setLastname(user.getLastname());
		userDTO.setFirstname(user.getFirstname());
		userDTO.setEmail(user.getEmail());
		userDTO.setActive(user.getActive());
		if (user.getRoles() != null) {
			userDTO.setRoles(user.getRoles()
					.stream()
					.filter(role -> role != null)
					.map(role -> role.getId())
					.collect(Collectors.toSet()));
		}
		return userDTO;
	}
	
	@Override
	public List<UserDTO> convertToUserDTOs(List<User> users) {
		if (users == null) {
			return null;
		}
		List<UserDTO> userDTOs = users.stream().map(user -> convertToDTO(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public User convertToPersistedObject(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		}
		User user = new User();
		user.setId(userDTO.getUserId());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setLastname(userDTO.getLastname());
		user.setFirstname(userDTO.getFirstname());
		user.setActive(userDTO.getActive());
		user.setEmail(userDTO.getEmail());
		if (userDTO.getRoles() != null) {
			user.setRoles(userDTO.getRoles()
				.stream()
				.filter(roleId -> roleId != null)
				.map(roleId -> dataService.getRoleById(roleId))
				.collect(Collectors.toSet()));
		}
		return user;
	}

	@Override
	public RoleDTO convertToDTO(Role role) {
		if (role == null) {
			return null;
		}
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(role.getId());
		roleDTO.setName(role.getName());
		return roleDTO;
	}
	
	@Override
	public List<RoleDTO> convertToRoleDTOs(List<Role> roles) {
		if (roles == null) {
			return null;
		}
		List<RoleDTO> roleDTOs = roles.stream().map(role -> convertToDTO(role)).collect(Collectors.toList());
		return roleDTOs;
	}

	@Override
	public Role convertToPersistedObject(RoleDTO roleDTO) {
		if (roleDTO == null) {
			return null;
		}
		Role role = new Role();
		role.setId(roleDTO.getRoleId());
		role.setName(roleDTO.getName());
		return role;
	}

	@Override
	public InventoryDTO convertToDTO(Inventory inventory) {
		if (inventory == null) {
			return null;
		}
		InventoryDTO inventoryDTO = new InventoryDTO();
		inventoryDTO.setInventoryId(inventory.getId());
		inventoryDTO.setUserId(inventory.getUser().getId());
		inventoryDTO.setStockId(inventory.getStock().getId());
		inventoryDTO.setStatus(inventory.getStatus());
		inventoryDTO.setLastUpdated(inventory.getLastUpdated());
		return inventoryDTO;
	}
	
	@Override
	public List<InventoryDTO> convertToInventoryDTOs(List<Inventory> inventories) {
		if (inventories == null) {
			return null;
		}
		List<InventoryDTO> inventoryDTOs = inventories.stream().map(inventory -> convertToDTO(inventory)).collect(Collectors.toList());
		return inventoryDTOs;
	}

	@Override
	public Inventory convertToPersistedObject(InventoryDTO inventoryDTO) {
		if (inventoryDTO == null) {
			return null;
		}
		User user = dataService.getUserById(inventoryDTO.getUserId());
		Stock stock = dataService.getStockById(inventoryDTO.getStockId());
		Inventory inventory = new Inventory();
		inventory.setId(inventoryDTO.getInventoryId());
		inventory.setUser(user);
		inventory.setStock(stock);
		inventory.setStatus(inventoryDTO.getStatus());
		inventory.setLastUpdated(inventoryDTO.getLastUpdated());
		return inventory;
	}

	@Override
	public RecordDTO convertToDTO(Record record) {
		if (record == null) {
			return null;
		}
		RecordDTO recordDTO = new RecordDTO();
		recordDTO.setRecordId(record.getId());
		recordDTO.setStock(convertToDTO(record.getStock()));
		recordDTO.setUser(convertToDTO(record.getUser()));
		recordDTO.setStockPrice(record.getStockPrice());
		recordDTO.setStatus(record.getStatus());
		recordDTO.setTime(record.getTime());
		return recordDTO;
	}
	
	@Override
	public List<RecordDTO> convertToRecordDTOs(List<Record> records) {
		if (records == null) {
			return null;
		}
		List<RecordDTO> recordDTOs = records.stream().map(record -> convertToDTO(record)).collect(Collectors.toList());
		return recordDTOs;
	}

	@Override
	public Record convertToPersistedObject(RecordDTO recordDTO) {
		if (recordDTO == null) {
			return null;
		}
		Record record = new Record();
		record.setId(recordDTO.getRecordId());
		record.setStock(convertToPersistedObject(recordDTO.getStock()));
		record.setUser(convertToPersistedObject(recordDTO.getUser()));
		record.setStockPrice(recordDTO.getStockPrice());
		record.setStatus(recordDTO.getStatus());
		record.setTime(recordDTO.getTime());
		return record;
	}

	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
}
