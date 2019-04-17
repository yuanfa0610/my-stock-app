package com.rf.privjoy.myStock.impl.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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

public interface MyStockDataService {
	
	/**
	 * Save sector to database
	 * @param sector sector to save
	 * @return saved sector
	 */
	public Sector saveSector(Sector sector);
	
	/**
	 * Update existing sector in database
	 * @param sector sector to update
	 * @return updated sector
	 */
	public Sector updateSector(Sector sector);
	
	/**
	 * Get sector with given id
	 * @param sectorId id of the sector
	 * @return sector with given id
	 */
	public Sector getSectorById(Long sectorId);
	
	/**
	 * Get all sectors
	 * @return all sectors
	 */
	public List<Sector> getAllSectors();
	
	/**
	 * Save company to database
	 * @param company company to save
	 * @return saved company
	 */
	public Company saveCompany(Company company);
	
	/**
	 * Update existing company in database
	 * @param company company to update
	 * @return updated company
	 */
	public Company updateCompany(Company company);
	
	/**
	 * Get company with given id
	 * @param companyId id of the company
	 * @return company with given id
	 */
	public Company getCompanyById(Long companyId);
	
	/**
	 * Get company with given name
	 * @param companyName name of the company
	 * @return company with given name
	 */
	public Company getCompanyByName(String companyName);
	
	/**
	 * Get all companies
	 * @return all companies
	 */
	public List<Company> getAllCompanies();
	
	/**
	 * Save revenueData to database
	 * @param revenueData revenueData to save
	 * @return saved revenueData
	 */
	public RevenueData saveRevenueData(RevenueData revenueData);
	
	/**
	 * Update existing revenueData in database
	 * @param revenueData revenueData to update
	 * @return updated revenueData
	 */
	public RevenueData updateRevenueData(RevenueData revenueData);
	
	/**
	 * Get revenueData with given id
	 * @param revenueDataId id of the revenueData
	 * @return revenueData with given id
	 */
	public RevenueData getRevenueDataById(Long revenueDataId);
	
	/**
	 * Save assetsData to database
	 * @param assetsData assetsData to save
	 * @return saved assetsData
	 */
	public AssetsData saveAssetsData(AssetsData assetsData);
	
	/**
	 * Update existing assetsData in database
	 * @param assetsData assetsData to update
	 * @return updated assetsData
	 */
	public AssetsData updateAssetsData(AssetsData assetsData);
	
	/**
	 * Get assetsData with given id
	 * @param assetsDataId id of the assetsData
	 * @return assetsData with given id
	 */
	public AssetsData getAssetsDataById(Long assetsDataId);
	
	/**
	 * Save stock to database
	 * @param stock stock to save
	 * @return saved stock
	 */
	public Stock saveStock(Stock stock);
	
	/**
	 * Update existing stock in database
	 * @param stock stock to update
	 * @return updated stock
	 */
	public Stock updateStock(Stock stock);
	
	/**
	 * Get stock with given id
	 * @param stockId id of the stock
	 * @return stock with given id
	 */
	public Stock getStockById(Long stockId);
	
	/**
	 * Get all stocks
	 * @return all stocks
	 */
	public List<Stock> getAllStocks();
	
	/**
	 * Save stockSpecification to database
	 * @param stockSpecification stockSpecification to save
	 * @return saved stockSpecification
	 */
	public StockSpecification saveStockSpecification(StockSpecification stockSpecification);
	
	/**
	 * Update existing stockSpecification
	 * @param stockSpecification stockSpecification to update
	 * @return updated stockSpecification
	 */
	public StockSpecification updateStockSpecification(StockSpecification stockSpecification);
	
	/**
	 * Get stockSpecification with given id
	 * @param stockSpecificationId
	 * @return stockSpecification with given id
	 */
	public StockSpecification getStockSpecificationById(Long stockSpecificationId);
	
	/**
	 * Save user to database
	 * @param user user to save
	 * @return saved user
	 */
	public User saveUser(User user);
	
	/**
	 * Update existing user in database
	 * @param user user to update
	 * @return updated user
	 */
	public User updateUser(User user);
	
	/**
	 * Get user with given id
	 * @param userId id of the user
	 * @return user with given id
	 */
	public User getUserById(Long userId);
	
	/**
	 * Get all users
	 * @return all users
	 */
	public List<User> getAllUsers();
	
	/**
	 * Save role to database
	 * @param role role to save
	 * @return saved role
	 */
	public Role saveRole(Role role);
	
	/**
	 * Update existing role in database
	 * @param role role to update
	 * @return updated role
	 */
	public Role updateRole(Role role);
	
	/**
	 * Get role with given id
	 * @param roleId id of the role
	 * @return role with given id
	 */
	public Role getRoleById(Long roleId);
	
	/**
	 * Get all roles
	 * @return all roles
	 */
	public List<Role> getAllRoles();
	
	/**
	 * Save inventory to database
	 * @param inventory inventory to save
	 * @return saved inventory
	 */
	public Inventory saveInventory(Inventory inventory);
	
	/**
	 * Update existing inventory in database
	 * @param inventory inventory to update
	 * @return updated inventory
	 */
	public Inventory updateInventory(Inventory inventory);
	
	/**
	 * Get inventory with given id
	 * @param inventoryId id of the inventory
	 * @return inventory with given id
	 */
	public Inventory getInventoryById(Long inventoryId);
	
	/**
	 * Get all inventories
	 * @return all inventories
	 */
	public List<Inventory> getAllInventoies();
	
	/**
	 * Delete inventory from database
	 * @param inventory inventory to delete
	 */
	public void deleteInventory(Inventory inventory);
	
	/**
	 * Save record to database
	 * @param record record to save
	 * @return saved record
	 */
	public Record saveRecord(Record record);
	
	/**
	 * Get record with given id
	 * @param recordId id of the record
	 * @return record with given id
	 */
	public Record getRecordById(Long recordId);
	
	/**
	 * Get all records
	 * @return all records
	 */
	public List<Record> getAllRecords();
	
	/**
	 * Create a new record then save to database
	 * @param stock stock in record
	 * @param user user in record
	 * @param stockPrice stock price in record
	 * @param status status in record
	 * @param time time in record
	 */
	public void createRecord(Stock stock, User user, BigDecimal stockPrice, String status, Timestamp time);
	
	/**
	 * Convert input inventory status to standard inventory status
	 * @param status input inventory status to convert
	 * @return standard inventory status or null if input is invalid
	 */
	public String getInventoryStatus(String status);
	
	/**
	 * Convert inventory status to corresponding record status
	 * @param inventoryStatus inventory status to convert
	 * @return record status or null if input is invalid
	 */
	public String convertToRecordStatus(String inventoryStatus);
	
}
