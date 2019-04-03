package com.rf.privjoy.myStock.impl.utils;

import org.springframework.stereotype.Component;

import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.persistent.User;

@Component
public class MyStockUpdateServiceImpl implements MyStockUpdateService {

	@Override
	public Company updateExistingCompany(Company existingCompany, Company updatedCompany) {
		existingCompany.setName(updatedCompany.getName());
		existingCompany.setSector(updatedCompany.getSector());
		existingCompany.setYearOfFounded(updatedCompany.getYearOfFounded());
		existingCompany.setYearOfIpo(updatedCompany.getYearOfIpo());
		existingCompany.setLink(updatedCompany.getLink());
		return existingCompany;
	}

	@Override
	public RevenueData updateExistingRevenueData(RevenueData existingRevenueData, RevenueData updatedRevenueData) {
		existingRevenueData.setYear(updatedRevenueData.getYear());
		existingRevenueData.setRevenue(updatedRevenueData.getRevenue());
		existingRevenueData.setCost(updatedRevenueData.getCost());
		existingRevenueData.setGrossProfit(updatedRevenueData.getGrossProfit());
		existingRevenueData.setNetIncome(updatedRevenueData.getNetIncome());
		existingRevenueData.setBasicEps(updatedRevenueData.getBasicEps());
		existingRevenueData.setDividendPayout(updatedRevenueData.getDividendPayout());
		return existingRevenueData;
	}

	@Override
	public AssetsData updateExistingAssetsData(AssetsData existingAssetsData, AssetsData updatedAssetsData) {
		existingAssetsData.setYear(updatedAssetsData.getYear());
		existingAssetsData.setCashOnHand(updatedAssetsData.getCashOnHand());
		existingAssetsData.setCurrentAssets(updatedAssetsData.getCurrentAssets());
		existingAssetsData.setTotalAssets(updatedAssetsData.getTotalAssets());
		existingAssetsData.setCurrentLiability(updatedAssetsData.getCurrentLiability());
		existingAssetsData.setLongTermDebt(updatedAssetsData.getLongTermDebt());
		existingAssetsData.setEquity(updatedAssetsData.getEquity());
		return existingAssetsData;
	}

	@Override
	public StockSpecification updateExistingStockSpecification(StockSpecification existingStockSpecification,
			StockSpecification updatedStockSpecification) {
		existingStockSpecification.setPrice(updatedStockSpecification.getPrice());
		existingStockSpecification.setPriceEarningRatio(updatedStockSpecification.getPriceEarningRatio());
		existingStockSpecification.setPriceBookRatio(updatedStockSpecification.getPriceBookRatio());
		return existingStockSpecification;
	}

	@Override
	public User updateExistingUser(User existingUser, User updatedUser) {
		existingUser.setPassword(updatedUser.getPassword());
		existingUser.setLastname(updatedUser.getLastname());
		existingUser.setFirstname(updatedUser.getFirstname());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setRoles(updatedUser.getRoles());
		return existingUser;
	}

	@Override
	public Inventory updateExistingInventory(Inventory existingInventory, Inventory updatedInventory) {
		existingInventory.setStatus(updatedInventory.getStatus());
		return existingInventory;
	}
}
