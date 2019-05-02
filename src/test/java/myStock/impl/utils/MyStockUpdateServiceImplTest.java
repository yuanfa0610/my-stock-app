package myStock.impl.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Role;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.persistent.User;
import com.rf.privjoy.myStock.impl.utils.MyStockUpdateServiceImpl;

import myStock.test.utils.CommonTestUtils;

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
	
	private static final Stock EXISTING_STOCK = new Stock();
	private static final BigDecimal EXISTING_PRICE = BigDecimal.valueOf(100);
	private static final BigDecimal EXISTING_PRICE_EARNING_RATIO = BigDecimal.valueOf(20.5);
	private static final BigDecimal EXISTING_PRICE_BOOK_RATIO = BigDecimal.valueOf(30.5);
	
	private static final Stock UPDATED_STOCK = new Stock();
	private static final BigDecimal UPDATED_PRICE = BigDecimal.valueOf(200);
	private static final BigDecimal UPDATED_PRICE_EARNING_RATIO = BigDecimal.valueOf(40.5);
	private static final BigDecimal UPDATED_PRICE_BOOK_RATIO = BigDecimal.valueOf(60.5);
	
	private static final String EXISTING_USERNAME = "username.existing";
	private static final String EXISTING_PASSWORD = "password.existing";
	private static final String EXISTING_LASTNAME = "LastNameExisting";
	private static final String EXISTING_FIRSTNAME = "FirstNameExisting";
	private static final String EXISTING_EMAIL = "existing@gmail.com";
	private static final boolean EXISTING_USER_ACTIVE = false;
	private static final Set<Role> EXISTING_USER_ROLES = new HashSet<>();
	
	private static final String UPDATED_USERNAME = "username.updated";
	private static final String UPDATED_PASSWORD = "password.updated";
	private static final String UPDATED_LASTNAME = "LastNameUpdated";
	private static final String UPDATED_FIRSTNAME = "FirstNameUpdated";
	private static final String UPDATED_EMAIL = "updated@gmail.com";
	private static final boolean UPDATED_USER_ACTIVE = true;
	private static final Set<Role> UPDATED_USER_ROLES = new HashSet<>();
	
	private static final User EXISTING_INVENTORY_USER = new User();
	private static final Stock EXISTING_INVENTORY_STOCK = new Stock();
	private static final String EXISTING_INVENTORY_STATUS = "existingStatus";
	private static final Timestamp EXISTING_INVENTORY_LASTUPDATED = Timestamp.valueOf(LocalDateTime.now());
	
	private static final User UPDATED_INVENTORY_USER = new User();
	private static final Stock UPDATED_INVENTORY_STOCK = new Stock();
	private static final String UPDATED_INVENTORY_STATUS = "updatedStatus";
	private static final Timestamp UPDATED_INVENTORY_LASTUPDATED = Timestamp.valueOf(LocalDateTime.now());
	
	public MyStockUpdateServiceImplTest() {
		myStockUpdateService = new MyStockUpdateServiceImpl();
	}
	
	
	@Test
	public void test_updateExistingCompany() {
		Company existingCompany = CommonTestUtils.createCompany(EXISTING_COMPANY_NAME, EXISTING_COMPANY_SECTOR, EXISTING_COMPANY_YEAR_OF_FOUNDED, 
																EXISTING_COMPANY_YEAR_OF_IPO, EXISTING_COMPANY_LINK);
		Company updatedCompany = CommonTestUtils.createCompany(UPDATED_COMPANY_NAME, UPDATED_COMPANY_SECTOR, UPDATED_COMPANY_YEAR_OF_FOUNDED, 
																UPDATED_COMPANY_YEAR_OF_IPO, UPDATED_COMPANY_LINK);
		
		existingCompany = myStockUpdateService.updateExistingCompany(existingCompany, updatedCompany);
		
		assertEquals("Company name is not updated successfully", UPDATED_COMPANY_NAME, existingCompany.getName());
		assertEquals("Company sector is not updated successfully", UPDATED_COMPANY_SECTOR, existingCompany.getSector());
		assertEquals("Company yearOfFounded is not updated successfully", UPDATED_COMPANY_YEAR_OF_FOUNDED, existingCompany.getYearOfFounded());
		assertEquals("Company yearOfIpo is not updated successfully", UPDATED_COMPANY_YEAR_OF_IPO, existingCompany.getYearOfIpo());
		assertEquals("Company link is not updated successfully", UPDATED_COMPANY_LINK, existingCompany.getLink());
	}
	
	@Test
	public void test_updateExistingRevenueData() {
		RevenueData existingRevenueData = CommonTestUtils.createRevenueData(EXISTING_REVENUE_DATA_COMPANY, EXISTING_REVENUE_DATA_YEAR, EXISTING_REVENUE_DATA_REVENUE, EXISTING_REVENUE_DATA_COST, 
																			EXISTING_REVENUE_DATA_GROSS_PROFIT, EXISTING_REVENUE_DATA_NET_INCOME, EXISTING_REVENUE_DATA_BASIC_EPS, EXISTING_REVENUE_DATA_DIVIDEND_PAYOUT);
		RevenueData updatedRevenueData = CommonTestUtils.createRevenueData(UPDATED_REVENUE_DATA_COMPANY, UPDATED_REVENUE_DATA_YEAR, UPDATED_REVENUE_DATA_REVENUE, UPDATED_REVENUE_DATA_COST, 
																			UPDATED_REVENUE_DATA_GROSS_PROFIT, UPDATED_REVENUE_DATA_NET_INCOME, UPDATED_REVENUE_DATA_BASIC_EPS, UPDATED_REVENUE_DATA_DIVIDEND_PAYOUT);
		
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
		AssetsData existingAssetsData = CommonTestUtils.createAssetsData(EXISTING_ASSETS_DATA_COMPANY, EXISTING_ASSETS_DATA_YEAR, EXISTING_ASSETS_DATA_CASH_ON_HAND, EXISTING_ASSETS_DATA_CURRENT_ASSETS, 
																		EXISTING_ASSETS_DATA_TOTAL_ASSETS, EXISTING_ASSETS_DATA_CURRENT_LIABILITY, EXISTING_ASSETS_DATA_LONG_TERM_DEBT, EXISTING_ASSETS_DATA_EQUITY);
		AssetsData updatedAssetsData = CommonTestUtils.createAssetsData(UPDATED_ASSETS_DATA_COMPANY, UPDATED_ASSETS_DATA_YEAR, UPDATED_ASSETS_DATA_CASH_ON_HAND, UPDATED_ASSETS_DATA_CURRENT_ASSETS, 
																		UPDATED_ASSETS_DATA_TOTAL_ASSETS, UPDATED_ASSETS_DATA_CURRENT_LIABILITY, UPDATED_ASSETS_DATA_LONG_TERM_DEBT, UPDATED_ASSETS_DATA_EQUITY);
		
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

	@Test
	public void test_updateExistingStockSpecification() {
		StockSpecification existingStockSpecification = CommonTestUtils.createStockSpecification(EXISTING_STOCK, EXISTING_PRICE, EXISTING_PRICE_EARNING_RATIO, EXISTING_PRICE_BOOK_RATIO);
		StockSpecification updatedStockSpecification = CommonTestUtils.createStockSpecification(UPDATED_STOCK, UPDATED_PRICE, UPDATED_PRICE_EARNING_RATIO, UPDATED_PRICE_BOOK_RATIO);
		
		existingStockSpecification = myStockUpdateService.updateExistingStockSpecification(existingStockSpecification, updatedStockSpecification);
		
		assertEquals("Stock Specification stock should not be updated", EXISTING_STOCK, existingStockSpecification.getStock());
		assertEquals("Stock Specification price is not updated successfully", UPDATED_PRICE, existingStockSpecification.getPrice());
		assertEquals("Stock Specification price earning ratio is not updated successfully", UPDATED_PRICE_EARNING_RATIO, existingStockSpecification.getPriceEarningRatio());
		assertEquals("Stock Specification price book ratio is not updated successfully", UPDATED_PRICE_BOOK_RATIO, existingStockSpecification.getPriceBookRatio());
	}
	
	@Test
	public void test_updateExistingUser() {
		User existingUser = CommonTestUtils.createUser(EXISTING_USERNAME, EXISTING_PASSWORD, EXISTING_LASTNAME, EXISTING_FIRSTNAME, EXISTING_EMAIL, EXISTING_USER_ACTIVE, EXISTING_USER_ROLES);
		User updatedUser = CommonTestUtils.createUser(UPDATED_USERNAME, UPDATED_PASSWORD, UPDATED_LASTNAME, UPDATED_FIRSTNAME, UPDATED_EMAIL, UPDATED_USER_ACTIVE, UPDATED_USER_ROLES);
		
		existingUser = myStockUpdateService.updateExistingUser(existingUser, updatedUser);
		
		assertEquals("User username should not be updated", EXISTING_USERNAME, existingUser.getUsername());
		assertEquals("User password is not updated successfully", UPDATED_PASSWORD, existingUser.getPassword());
		assertEquals("User lastname is not updated successfully", UPDATED_LASTNAME, existingUser.getLastname());
		assertEquals("User firstname is not updated successfully", UPDATED_FIRSTNAME, existingUser.getFirstname());
		assertEquals("User email is not updated successfully", UPDATED_EMAIL, existingUser.getEmail());
		assertEquals("User active should not be updated", EXISTING_USER_ACTIVE, existingUser.getActive());
		assertEquals("User roles is not updated successfully", UPDATED_USER_ROLES, existingUser.getRoles());
	}
	
	@Test
	public void test_updateExistingInventory() {
		Inventory existingInventory = CommonTestUtils.createInventory(EXISTING_INVENTORY_USER, EXISTING_INVENTORY_STOCK, EXISTING_INVENTORY_STATUS, EXISTING_INVENTORY_LASTUPDATED);
		Inventory updatedInventory = CommonTestUtils.createInventory(UPDATED_INVENTORY_USER, UPDATED_INVENTORY_STOCK, UPDATED_INVENTORY_STATUS, UPDATED_INVENTORY_LASTUPDATED);
		
		existingInventory = myStockUpdateService.updateExistingInventory(existingInventory, updatedInventory);
		
		assertEquals("Inventory user should not be updated", EXISTING_INVENTORY_USER, existingInventory.getUser());
		assertEquals("Inventory stock should not be updated", EXISTING_INVENTORY_STOCK, existingInventory.getStock());
		assertEquals("Inventory status is not updated successfully", UPDATED_INVENTORY_STATUS, existingInventory.getStatus());
		assertEquals("Inventory last updated time should not be updated", EXISTING_INVENTORY_LASTUPDATED, existingInventory.getLastUpdated());
	}
}
