package myStock.impl.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateServiceImpl;

public class MyStockUpdateServiceImplTest {
	
	private MyStockUpdateServiceImpl myStockUpdateService;
	
	private static final String EXISTING_COMPANY_NAME = "Company Name A";
	private static final Sector EXISTING_COMPANY_SECTOR = new Sector();
	private static final String EXISTING_COMPANY_YEAR_OF_FOUNDED = "1820";
	private static final String EXISTING_COMPANY_YEAR_OF_IPO = "1880";
	private static final String EXISTING_COMPANY_LINK = "http://aaa.com";
	private static final String UPDATED_COMPANY_NAME = "Company Name B";
	private static final Sector UPDATED_COMPANY_SECTOR = new Sector();
	private static final String UPDATED_COMPANY_YEAR_OF_FOUNDED = "1920";
	private static final String UPDATED_COMPANY_YEAR_OF_IPO = "1980";
	private static final String UPDATED_COMPANY_LINK = "http://bbb.com";
	
	private static final Company EXISTING_REVENUE_DATA_COMPANY =  new Company();
	private static final String EXISTING_REVENUE_DATA_YEAR = "1800";
	private static final BigDecimal EXISTING_REVENUE_DATA_REVENUE =  BigDecimal.valueOf(1000);
	private static final BigDecimal EXISTING_REVENUE_DATA_COST = BigDecimal.valueOf(900);
	private static final BigDecimal EXISTING_REVENUE_DATA_GROSS_PROFIT = BigDecimal.valueOf(800);
	private static final BigDecimal EXISTING_REVENUE_DATA_NET_INCOME = BigDecimal.valueOf(700);
	private static final BigDecimal EXISTING_REVENUE_DATA_BASIC_EPS = BigDecimal.valueOf(10.10);
	private static final BigDecimal EXISTING_REVENUE_DATA_DIVIDEND_PAYOUT = BigDecimal.valueOf(9.10);
	private static final Company UPDATED_REVENUE_DATA_COMPANY =  new Company();
	private static final String UPDATED_REVENUE_DATA_YEAR = "1900";
	private static final BigDecimal UPDATED_REVENUE_DATA_REVENUE =  BigDecimal.valueOf(2000);
	private static final BigDecimal UPDATED_REVENUE_DATA_COST = BigDecimal.valueOf(1800);
	private static final BigDecimal UPDATED_REVENUE_DATA_GROSS_PROFIT = BigDecimal.valueOf(1600);
	private static final BigDecimal UPDATED_REVENUE_DATA_NET_INCOME = BigDecimal.valueOf(1400);
	private static final BigDecimal UPDATED_REVENUE_DATA_BASIC_EPS = BigDecimal.valueOf(20.20);
	private static final BigDecimal UPDATED_REVENUE_DATA_DIVIDEND_PAYOUT = BigDecimal.valueOf(18.20);
	
	private static final Company EXISTING_ASSETS_DATA_COMPANY = new Company();
	private static final String EXISTING_ASSETS_DATA_YEAR = "1800";
	private static final BigDecimal EXISTING_ASSETS_DATA_CASH_ON_HAND = BigDecimal.valueOf(1000);
	private static final BigDecimal EXISTING_ASSETS_DATA_CURRENT_ASSETS = BigDecimal.valueOf(900);
	private static final BigDecimal EXISTING_ASSETS_DATA_TOTAL_ASSETS = BigDecimal.valueOf(800);
	private static final BigDecimal EXISTING_ASSETS_DATA_CURRENT_LIABILITY = BigDecimal.valueOf(700);
	private static final BigDecimal EXISTING_ASSETS_DATA_LONG_TERM_DEBT = BigDecimal.valueOf(600);
	private static final BigDecimal EXISTING_ASSETS_DATA_EQUITY = BigDecimal.valueOf(500);
	private static final Company UPDATED_ASSETS_DATA_COMPANY = new Company();
	private static final String UPDATED_ASSETS_DATA_YEAR = "1900";
	private static final BigDecimal UPDATED_ASSETS_DATA_CASH_ON_HAND = BigDecimal.valueOf(2000);
	private static final BigDecimal UPDATED_ASSETS_DATA_CURRENT_ASSETS = BigDecimal.valueOf(1800);
	private static final BigDecimal UPDATED_ASSETS_DATA_TOTAL_ASSETS = BigDecimal.valueOf(1600);
	private static final BigDecimal UPDATED_ASSETS_DATA_CURRENT_LIABILITY = BigDecimal.valueOf(1400);
	private static final BigDecimal UPDATED_ASSETS_DATA_LONG_TERM_DEBT = BigDecimal.valueOf(1200);
	private static final BigDecimal UPDATED_ASSETS_DATA_EQUITY = BigDecimal.valueOf(1000);
	
	
	public MyStockUpdateServiceImplTest() {
		myStockUpdateService = new MyStockUpdateServiceImpl();
	}
	
	
	@Test
	public void test_updateExistingCompany() {
		Company existingCompany = new Company();
		existingCompany.setName(EXISTING_COMPANY_NAME);
		existingCompany.setSector(EXISTING_COMPANY_SECTOR);
		existingCompany.setYearOfFounded(EXISTING_COMPANY_YEAR_OF_FOUNDED);
		existingCompany.setYearOfIpo(EXISTING_COMPANY_YEAR_OF_IPO);
		existingCompany.setLink(EXISTING_COMPANY_LINK);
		
		Company updatedCompany = new Company();
		updatedCompany.setName(UPDATED_COMPANY_NAME);
		updatedCompany.setSector(UPDATED_COMPANY_SECTOR);
		updatedCompany.setYearOfFounded(UPDATED_COMPANY_YEAR_OF_FOUNDED);
		updatedCompany.setYearOfIpo(UPDATED_COMPANY_YEAR_OF_IPO);
		updatedCompany.setLink(UPDATED_COMPANY_LINK);
		
		existingCompany = myStockUpdateService.updateExistingCompany(existingCompany, updatedCompany);
		
		assertEquals("Company name is not updated successfully", UPDATED_COMPANY_NAME, existingCompany.getName());
		assertEquals("Company sector is not updated successfully", UPDATED_COMPANY_SECTOR, existingCompany.getSector());
		assertEquals("Company yearOfFounded is not updated successfully", UPDATED_COMPANY_YEAR_OF_FOUNDED, existingCompany.getYearOfFounded());
		assertEquals("Company yearOfIpo is not updated successfully", UPDATED_COMPANY_YEAR_OF_IPO, existingCompany.getYearOfIpo());
		assertEquals("Company link is not updated successfully", UPDATED_COMPANY_LINK, existingCompany.getLink());
	}
	
	@Test
	public void test_updateExistingRevenueData() {
		RevenueData existingRevenueData = new RevenueData();
		existingRevenueData.setCompany(EXISTING_REVENUE_DATA_COMPANY);
		existingRevenueData.setYear(EXISTING_REVENUE_DATA_YEAR);
		existingRevenueData.setRevenue(EXISTING_REVENUE_DATA_REVENUE);
		existingRevenueData.setCost(EXISTING_REVENUE_DATA_COST);
		existingRevenueData.setGrossProfit(EXISTING_REVENUE_DATA_GROSS_PROFIT);
		existingRevenueData.setNetIncome(EXISTING_REVENUE_DATA_NET_INCOME);
		existingRevenueData.setBasicEps(EXISTING_REVENUE_DATA_BASIC_EPS);
		existingRevenueData.setDividendPayout(EXISTING_REVENUE_DATA_DIVIDEND_PAYOUT);
		
		RevenueData updatedRevenueData = new RevenueData();
		updatedRevenueData.setCompany(UPDATED_REVENUE_DATA_COMPANY);
		updatedRevenueData.setYear(UPDATED_REVENUE_DATA_YEAR);
		updatedRevenueData.setRevenue(UPDATED_REVENUE_DATA_REVENUE);
		updatedRevenueData.setCost(UPDATED_REVENUE_DATA_COST);
		updatedRevenueData.setGrossProfit(UPDATED_REVENUE_DATA_GROSS_PROFIT);
		updatedRevenueData.setNetIncome(UPDATED_REVENUE_DATA_NET_INCOME);
		updatedRevenueData.setBasicEps(UPDATED_REVENUE_DATA_BASIC_EPS);
		updatedRevenueData.setDividendPayout(UPDATED_REVENUE_DATA_DIVIDEND_PAYOUT);
		
		existingRevenueData = myStockUpdateService.updateExistingRevenueData(existingRevenueData, updatedRevenueData);
		
		assertEquals("Revenue Data company should not be updated", EXISTING_REVENUE_DATA_COMPANY, existingRevenueData.getCompany());
		assertEquals("Revenue Data year is not updated successfully", UPDATED_REVENUE_DATA_YEAR, existingRevenueData.getYear());
		assertEquals("Revenue Data revenue is not updated successfully", UPDATED_REVENUE_DATA_REVENUE, existingRevenueData.getRevenue());
		assertEquals("Revenue Data cost is not updated successfully", UPDATED_REVENUE_DATA_COST, existingRevenueData.getCost());
		assertEquals("Revenue Data gross profit is not updated successfully", UPDATED_REVENUE_DATA_GROSS_PROFIT, existingRevenueData.getGrossProfit());
		assertEquals("Revenue Data net income is not updated successfully", UPDATED_REVENUE_DATA_NET_INCOME, existingRevenueData.getNetIncome());
		assertEquals("Revenue Data basic eps is not updated successfully", UPDATED_REVENUE_DATA_BASIC_EPS, existingRevenueData.getBasicEps());
		assertEquals("Revenue Data dividend payout is not updated successfully", UPDATED_REVENUE_DATA_DIVIDEND_PAYOUT, existingRevenueData.getDividendPayout());
	}
	
	@Test
	public void test_updateExistingAssetsData() {
		AssetsData existingAssetsData = new AssetsData();
		existingAssetsData.setCompany(EXISTING_ASSETS_DATA_COMPANY);
		existingAssetsData.setYear(EXISTING_ASSETS_DATA_YEAR);
		existingAssetsData.setCashOnHand(EXISTING_ASSETS_DATA_CASH_ON_HAND);
		existingAssetsData.setCurrentAssets(EXISTING_ASSETS_DATA_CURRENT_ASSETS);
		existingAssetsData.setTotalAssets(EXISTING_ASSETS_DATA_TOTAL_ASSETS);
		existingAssetsData.setCurrentLiability(EXISTING_ASSETS_DATA_CURRENT_LIABILITY);
		existingAssetsData.setLongTermDebt(EXISTING_ASSETS_DATA_LONG_TERM_DEBT);
		existingAssetsData.setEquity(EXISTING_ASSETS_DATA_EQUITY);
		
		AssetsData updatedAssetsData = new AssetsData();
		updatedAssetsData.setCompany(UPDATED_ASSETS_DATA_COMPANY);
		updatedAssetsData.setYear(UPDATED_ASSETS_DATA_YEAR);
		updatedAssetsData.setCashOnHand(UPDATED_ASSETS_DATA_CASH_ON_HAND);
		updatedAssetsData.setCurrentAssets(UPDATED_ASSETS_DATA_CURRENT_ASSETS);
		updatedAssetsData.setTotalAssets(UPDATED_ASSETS_DATA_TOTAL_ASSETS);
		updatedAssetsData.setCurrentLiability(UPDATED_ASSETS_DATA_CURRENT_LIABILITY);
		updatedAssetsData.setLongTermDebt(UPDATED_ASSETS_DATA_LONG_TERM_DEBT);
		updatedAssetsData.setEquity(UPDATED_ASSETS_DATA_EQUITY);
		
		existingAssetsData = myStockUpdateService.updateExistingAssetsData(existingAssetsData, updatedAssetsData);
		
		assertEquals("Assets Data company should not be updated", EXISTING_ASSETS_DATA_COMPANY, existingAssetsData.getCompany());
		assertEquals("Assets Data year is not updated successfully", UPDATED_ASSETS_DATA_YEAR, existingAssetsData.getYear());
		assertEquals("Assets Data cash on hand is not updated successfully", UPDATED_ASSETS_DATA_CASH_ON_HAND, existingAssetsData.getCashOnHand());
		assertEquals("Assets Data current assets is not updated successfully", UPDATED_ASSETS_DATA_CURRENT_ASSETS, existingAssetsData.getCurrentAssets());
		assertEquals("Assets Data total assets is not updated successfully", UPDATED_ASSETS_DATA_TOTAL_ASSETS, existingAssetsData.getTotalAssets());
		assertEquals("Assets Data current liability is not updated successfully", UPDATED_ASSETS_DATA_CURRENT_LIABILITY, existingAssetsData.getCurrentLiability());
		assertEquals("Assets Data long term debt is not updated successfully", UPDATED_ASSETS_DATA_LONG_TERM_DEBT, existingAssetsData.getLongTermDebt());
		assertEquals("Assets Data equity is not updated successfully", UPDATED_ASSETS_DATA_EQUITY, existingAssetsData.getEquity());
	}

}
