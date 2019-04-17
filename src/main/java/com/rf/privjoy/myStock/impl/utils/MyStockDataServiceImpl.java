package com.rf.privjoy.myStock.impl.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public List<Sector> getAllSectors() {
		return sectorDao.getAll();
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
	public Company getCompanyByName(String companyName) {
		return companyDao.getCompanyByName(companyName);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		return companyDao.getAll();
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
	public List<Stock> getAllStocks() {
		return stockDao.getAll();
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
	public List<User> getAllUsers() {
		return userDao.getAll();
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
	public List<Role> getAllRoles() {
		return roleDao.getAll();
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
	public List<Inventory> getAllInventoies() {
		return inventoryDao.getAll();
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
	public List<Record> getAllRecords() {
		return recordDao.getAll();
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
