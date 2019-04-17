package com.rf.privjoy.myStock.impl.utils;

import java.util.List;

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

public interface MyStockConversionService {

	/**
	 * Convert Sector to SectorDTO
	 * @param sector POJO to convert
	 * @return Sector DTO
	 */
	public SectorDTO convertToDTO(Sector sector);
	
	/**
	 * Convert Sectors to SectorDTOs
	 * @param sectors sector POJOs to convert
	 * @return Sector DTOs
	 */
	public List<SectorDTO> convertToSectorDTOs(List<Sector> sectors);
	
	/**
	 * Convert SectorDTO to Sector
	 * @param sectorDTO sector DTO to convert
	 * @return Sector POJO
	 */
	public Sector convertToPersistedObject(SectorDTO sectorDTO);
	
	/**
	 * Convert Company to CompanyDTO
	 * @param company POJO to convert
	 * @return Company DTO
	 */
	public CompanyDTO convertToDTO(Company company);
	
	/**
	 * Convert Companies to CompanyDTOs
	 * @param companies company POJOs to convert
	 * @return Company DTOs
	 */
	public List<CompanyDTO> convertToCompanyDTOs(List<Company> companies);
	
	/**
	 * Convert CompanyDTO to Company
	 * @param companyDTO company DTO to convert
	 * @return Company POJO
	 */
	public Company convertToPersistedObject(CompanyDTO companyDTO);
	
	/**
	 * Convert RevenueData to RevenueDataDTO
	 * @param revenueData POJO to convert
	 * @return RevenueData DTO
	 */
	public RevenueDataDTO convertToDTO(RevenueData revenueData);
	
	/**
	 * Convert RevenueDataDTO to RevenueData
	 * @param revenueDataDTO revenueData DTO to convert
	 * @return RevenueData POJO
	 */
	public RevenueData convertToPersistedObject(RevenueDataDTO revenueDataDTO);
	
	/**
	 * Convert AssetsData to AssetsDataDTO
	 * @param assetsData POJO to convert
	 * @return AssetsData DTO
	 */
	public AssetsDataDTO convertToDTO(AssetsData assetsData);
	
	/**
	 * Convert AssetsDataDTO to AssetsData
	 * @param assetsDataDTO assetsData DTO to convert
	 * @return AssetsData POJO
	 */
	public AssetsData convertToPersistedObject(AssetsDataDTO assetsDataDTO);
	
	/**
	 * Convert Stock to StockDTO
	 * @param stock POJO to convert
	 * @return Stock DTO
	 */
	public StockDTO convertToDTO(Stock stock);
	
	/**
	 * Convert Stocks to StockDTOs
	 * @param stocks stock POJOs to convert
	 * @return Stock DTOs
	 */
	public List<StockDTO> convertToStockDTOs(List<Stock> stocks);
	
	/**
	 * Convert StockDTO to Stock
	 * @param stockDTO stock DTO to convert
	 * @return Stock POJO
	 */
	public Stock convertToPersistedObject(StockDTO stockDTO);
	
	/**
	 * Convert StockSpecification to StockSpecificationDTO
	 * @param stockSpecification POJO to convert
	 * @return StockSpecification DTO
	 */
	public StockSpecificationDTO convertToDTO (StockSpecification stockSpecification);
	
	/**
	 * Convert StockSpecificationDTO to StockSpecification
	 * @param stockSpecificationDTO stockSpecification DTO to convert
	 * @return StockSpecification POJO
	 */
	public StockSpecification convertToPersistedObject(StockSpecificationDTO stockSpecificationDTO);
	
	/**
	 * Convert User to UserDTO
	 * @param user POJO to convert
	 * @return User DTO
	 */
	public UserDTO convertToDTO(User user);
	
	/**
	 * Convert Users to UserDTOs
	 * @param users POJOs to convert
	 * @return User DTOs
	 */
	public List<UserDTO> convertToUserDTOs(List<User> users);
	
	/**
	 * Convert UserDTO to User
	 * @param userDTO user DTO to convert
	 * @return User POJO
	 */
	public User convertToPersistedObject(UserDTO userDTO);
	
	/**
	 * Convert Role to RoleDTO
	 * @param role POJO to convert
	 * @return Role DTO
	 */
	public RoleDTO convertToDTO(Role role);
	
	/**
	 * Convert Roles to RoleDTOs
	 * @param roles role POJOs to convert
	 * @return Role DTOs
	 */
	public List<RoleDTO> convertToRoleDTOs(List<Role> roles);
	
	/**
	 * Convert RoleDTO to Role
	 * @param roleDTO role DTO to convert
	 * @return Role POJO
	 */
	public Role convertToPersistedObject(RoleDTO roleDTO);
	
	
	/**
	 * Convert Inventory to InventoryDTO
	 * @param inventory POJO to convert
	 * @return Inventory DTO
	 */
	public InventoryDTO convertToDTO(Inventory inventory);
	
	/**
	 * Convert Inventories to InventoryDTOs
	 * @param inventories inventory POJOs to convert
	 * @return Inventory DTOs
	 */
	public List<InventoryDTO> convertToInventoryDTOs(List<Inventory> inventories);

	/**
	 * Convert InventoryDTO to Inventory
	 * @param inventoryDTO inventory DTO to convert
	 * @return Inventory POJO
	 */
	public Inventory convertToPersistedObject(InventoryDTO inventoryDTO);
	
	/**
	 * Convert Record to RecordDTO
	 * @param record POJO to convert
	 * @return Record DTO
	 */
	public RecordDTO convertToDTO(Record record);
	
	/**
	 * Convert Records to Record DTOs
	 * @param records record POJOs to convert
	 * @return Record DTOs
	 */
	public List<RecordDTO> convertToRecordDTOs(List<Record> records);
	
	/**
	 * Convert RecordDTO to Record
	 * @param recordDTO record DTO to convert
	 * @return Record POJO
	 */
	public Record convertToPersistedObject(RecordDTO recordDTO);
	
}
