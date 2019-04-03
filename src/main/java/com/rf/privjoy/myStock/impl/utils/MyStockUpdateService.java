package com.rf.privjoy.myStock.impl.utils;

import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.persistent.User;

public interface MyStockUpdateService {

	/**
	 * Update existing company with updated company
	 * @param existingCompany existing company
	 * @param updatedCompany updated company
	 * @return updated existing company
	 */
	public Company updateExistingCompany(Company existingCompany, Company updatedCompany);
	
	/**
	 * Update existing revenueData with updated revenueData
	 * @param existingRevenueData existing revenueData
	 * @param updatedRevenueData updated revenueData
	 * @return updated existing revenueData
	 */
	public RevenueData updateExistingRevenueData(RevenueData existingRevenueData, RevenueData updatedRevenueData);
	
	/**
	 * Update existing assetsData with updated assetsData
	 * @param existingAssetsData existing assetsData
	 * @param updatedAssetsData updated assetsData
	 * @return updated existing assetsData
	 */
	public AssetsData updateExistingAssetsData(AssetsData existingAssetsData, AssetsData updatedAssetsData);
	
	/**
	 * Update existing stockSpecification with updated stockSpecification
	 * @param existingStockSpecification existing stockSpecification
	 * @param updatedStockSpecification updated stockSpecification
	 * @return updated existing stockSpecification
	 */
	public StockSpecification updateExistingStockSpecification(StockSpecification existingStockSpecification, StockSpecification updatedStockSpecification);
	
	/**
	 * Update existing user with updated user
	 * @param existingUser existing user
	 * @param updatedUser updated user
	 * @return updated existing user
	 */
	public User updateExistingUser(User existingUser, User updatedUser);
	
	/**
	 * Update existing inventory with updated inventory
	 * @param existingInventory existing inventory
	 * @param updatedInventory updated inventory
	 * @return updated existing inventory
	 */
	public Inventory updateExistingInventory(Inventory existingInventory, Inventory updatedInventory);
}
