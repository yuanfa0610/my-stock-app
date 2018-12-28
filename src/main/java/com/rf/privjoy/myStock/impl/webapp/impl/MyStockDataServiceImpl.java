package com.rf.privjoy.myStock.impl.webapp.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.rf.privjoy.myStock.impl.dao.AssetsDataDao;
import com.rf.privjoy.myStock.impl.dao.CompanyDao;
import com.rf.privjoy.myStock.impl.dao.InventoryDao;
import com.rf.privjoy.myStock.impl.dao.RecordDao;
import com.rf.privjoy.myStock.impl.dao.RevenueDataDao;
import com.rf.privjoy.myStock.impl.dao.RoleDao;
import com.rf.privjoy.myStock.impl.dao.SectorDao;
import com.rf.privjoy.myStock.impl.dao.StockDao;
import com.rf.privjoy.myStock.impl.dao.StockSpecificationDao;
import com.rf.privjoy.myStock.impl.dao.UserDao;
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
import com.rf.privjoy.myStock.impl.utils.Constants;

@Component
@Transactional
public class MyStockDataServiceImpl implements MyStockDataService {
	
	private SectorDao sectorDao;
	private CompanyDao companyDao;
	private RevenueDataDao revenueDataDao;
	private AssetsDataDao assetsDataDao;
	private StockDao stockDao;
	private StockSpecificationDao stockSpecificationDao;
	private UserDao userDao;
	private RoleDao roleDao;
	private InventoryDao inventoryDao;
	private RecordDao recordDao;
	

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
		companyDTO.setSector(convertToDTO(company.getSector()));
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
	public Company convertToPersistedObject(CompanyDTO companyDTO) {
		if (companyDTO == null) {
			return null;
		}
		Company company = new Company();
		company.setId(companyDTO.getCompanyId());
		company.setName(companyDTO.getName());
		company.setSector(convertToPersistedObject(companyDTO.getSector()));
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
		RevenueData revenueData = new RevenueData();
		revenueData.setId(revenueDataDTO.getRevenueDataId());
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
		AssetsData assetsData = new AssetsData();
		assetsData.setId(assetsDataDTO.getAssetsDataId());
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
		stockDTO.setCompany(convertToDTO(stock.getCompany()));
		stockDTO.setStockSpecification(convertToDTO(stock.getStockSpecification()));
		return stockDTO;
	}

	@Override
	public Stock convertToPersistedObject(StockDTO stockDTO) {
		if (stockDTO == null) {
			return null;
		}
		Stock stock = new Stock();
		stock.setId(stockDTO.getStockId());
		stock.setSymbol(stockDTO.getSymbol());
		stock.setCompany(convertToPersistedObject(stockDTO.getCompany()));
		stock.setStockSpecification(convertToPersistedObject(stockDTO.getStockSpecification()));
		return stock;
	}

	@Override
	public StockSpecificationDTO convertToDTO(StockSpecification stockSpecification) {
		if (stockSpecification == null) {
			return null;
		}
		StockSpecificationDTO stockSpecificationDTO = new StockSpecificationDTO();
		stockSpecificationDTO.setStockSpecificationId(stockSpecification.getId());
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
		StockSpecification stockSpecification= new StockSpecification();
		stockSpecification.setId(stockSpecificationDTO.getStockSpecificationId());
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
		if (user.getRoles() != null) {
			userDTO.setRoles(user.getRoles()
					.stream()
					.filter(role -> role != null)
					.map(role -> convertToDTO(role))
					.collect(Collectors.toSet()));
		}
		return userDTO;
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
		user.setEmail(userDTO.getEmail());
		if (userDTO.getRoles() != null) {
			user.setRoles(userDTO.getRoles()
				.stream()
				.filter(roleDTO -> roleDTO != null)
				.map(roleDTO -> convertToPersistedObject(roleDTO))
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
		inventoryDTO.setStock(convertToDTO(inventory.getStock()));
		inventoryDTO.setUser(convertToDTO(inventory.getUser()));
		inventoryDTO.setStatus(inventory.getStatus());
		inventoryDTO.setTime(inventory.getTime());
		return inventoryDTO;
	}

	@Override
	public Inventory convertToPersistedObject(InventoryDTO inventoryDTO) {
		if (inventoryDTO == null) {
			return null;
		}
		Inventory inventory = new Inventory();
		inventory.setId(inventoryDTO.getInventoryId());
		inventory.setStock(convertToPersistedObject(inventoryDTO.getStock()));
		inventory.setUser(convertToPersistedObject(inventoryDTO.getUser()));
		inventory.setStatus(inventoryDTO.getStatus());
		inventory.setTime(inventoryDTO.getTime());
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

	@Override
	public Sector saveSector(Sector sector) {
		Long sectorId = sectorDao.save(sector);
		sector.setId(sectorId);
		return sector;
	}

	@Override
	public Sector updateSector(Sector sector) {
		sectorDao.update(sector);
		return sector;
	}

	@Override
	public Sector getSectorById(Long sectorId) {
		return sectorDao.getById(sectorId);
	}

	@Override
	public Company saveCompany(Company company) {
		Long companyId = companyDao.save(company);
		company.setId(companyId);
		return company;
	}

	@Override
	public Company updateCompany(Company company) {
		companyDao.update(company);
		return company;
	}

	@Override
	public Company getCompanyById(Long companyId) {
		return companyDao.getById(companyId);
	}

	@Override
	public RevenueData saveRevenueData(RevenueData revenueData) {
		Long revenueDataId = revenueDataDao.save(revenueData);
		revenueData.setId(revenueDataId);
		return revenueData;
	}

	@Override
	public RevenueData updateRevenueData(RevenueData revenueData) {
		revenueDataDao.update(revenueData);
		return revenueData;
	}

	@Override
	public RevenueData getRevenueDataById(Long revenueDataId) {
		return revenueDataDao.getById(revenueDataId);
	}

	@Override
	public AssetsData saveAssetsData(AssetsData assetsData) {
		Long assetsDataId = assetsDataDao.save(assetsData);
		assetsData.setId(assetsDataId);
		return assetsData;
	}

	@Override
	public AssetsData updateAssetsData(AssetsData assetsData) {
		assetsDataDao.update(assetsData);
		return assetsData;
	}

	@Override
	public AssetsData getAssetsDataById(Long assetsDataId) {
		return assetsDataDao.getById(assetsDataId);
	}

	@Override
	public Stock saveStock(Stock stock) {
		Long stockId = stockDao.save(stock);
		stock.setId(stockId);
		return stock;
	}

	@Override
	public Stock updateStock(Stock stock) {
		stockDao.update(stock);
		return stock;
	}

	@Override
	public Stock getStockById(Long stockId) {
		return stockDao.getById(stockId);
	}

	@Override
	public StockSpecification saveStockSpecification(StockSpecification stockSpecification) {
		Long stockSpecificationId = stockSpecificationDao.save(stockSpecification);
		stockSpecification.setId(stockSpecificationId);
		return stockSpecification;
	}

	@Override
	public StockSpecification updateStockSpecification(StockSpecification stockSpecification) {
		stockSpecificationDao.update(stockSpecification);
		return stockSpecification;
	}

	@Override
	public StockSpecification getStockSpecificationById(Long stockSpecificationId) {
		return stockSpecificationDao.getById(stockSpecificationId);
	}

	@Override
	public User saveUser(User user) {
		Long userId = userDao.save(user);
		user.setId(userId);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userDao.update(user);
		return user;
	}

	@Override
	public User getUserById(Long userId) {
		return userDao.getById(userId);
	}

	@Override
	public Role saveRole(Role role) {
		Long roleId = roleDao.save(role);
		role.setId(roleId);
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		roleDao.update(role);
		return role;
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleDao.getById(roleId);
	}

	@Override
	public Inventory saveInventory(Inventory inventory) {
		Long inventoryId = inventoryDao.save(inventory);
		inventory.setId(inventoryId);
		return inventory;
	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		inventoryDao.update(inventory);
		return inventory;
	}

	@Override
	public Inventory getInventoryById(Long inventoryId) {
		return inventoryDao.getById(inventoryId);
	}
	
	@Override
	public void deleteInventory(Inventory inventory) {
		inventoryDao.delete(inventory);
	}

	@Override
	public Record saveRecord(Record record) {
		Long recordId = recordDao.save(record);
		record.setId(recordId);
		return record;
	}

	@Override
	public Record getRecordById(Long recordId) {
		return recordDao.getById(recordId);
	}
	
	@Override
	public void createRecord(Stock stock, User user, BigDecimal stockPrice, String status, Timestamp time) {
		Record record = new Record(stock, user, stockPrice, status, time);
		saveRecord(record);
	}
	
	@Override
	public String getInventoryStatus(String status) {
		if (status.equalsIgnoreCase(Constants.INVENTORY_STATUS_WATCHING)) {
			return Constants.INVENTORY_STATUS_WATCHING;
		} else if (status.equalsIgnoreCase(Constants.INVENTORY_STATUS_HOLDING)) {
			return Constants.INVENTORY_STATUS_HOLDING;
		}
		return null;
	}
	
	@Override
	public String convertToRecordStatus(String inventoryStatus) {
		if (inventoryStatus.equalsIgnoreCase(Constants.INVENTORY_STATUS_WATCHING)) {
			return Constants.RECORD_STATUS_WATCH;
		} else if (inventoryStatus.equalsIgnoreCase(Constants.INVENTORY_STATUS_HOLDING)) {
			return Constants.RECORD_STATUS_BUY;
		}
		return null;
	}

	/**
	 * @param sectorDao the sectorDao to set
	 */
	@Autowired
	public void setSectorDao(SectorDao sectorDao) {
		this.sectorDao = sectorDao;
	}

	/**
	 * @param compnayDao the compnayDao to set
	 */
	@Autowired
	public void setCompnayDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * @param revenueDataDao the revenueDataDao to set
	 */
	@Autowired
	public void setRevenueDataDao(RevenueDataDao revenueDataDao) {
		this.revenueDataDao = revenueDataDao;
	}

	/**
	 * @param assetsDataDao the assetsDataDao to set
	 */
	@Autowired
	public void setAssetsDataDao(AssetsDataDao assetsDataDao) {
		this.assetsDataDao = assetsDataDao;
	}

	/**
	 * @param stockDao the stockDao to set
	 */
	@Autowired
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	/**
	 * @param stockSpecificationDao the stockSpecificationDao to set
	 */
	@Autowired
	public void setStockSpecificationDao(StockSpecificationDao stockSpecificationDao) {
		this.stockSpecificationDao = stockSpecificationDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param roleDao the roleDao to set
	 */
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * @param inventoryDao the inventoryDao to set
	 */
	@Autowired
	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	/**
	 * @param recordDao the recordDao to set
	 */
	@Autowired
	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

}
