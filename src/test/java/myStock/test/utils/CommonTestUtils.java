package myStock.test.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.Inventory;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Role;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.persistent.User;

public class CommonTestUtils {

	/**
	 * Create a new company
	 * @param name company name
	 * @param sector company sector
	 * @param yearOfFounded company year of founded
	 * @param yearOfIpo company year of IPO
	 * @param link company link
	 * @return new company
	 */
	public static Company createCompany(String name, Sector sector, String yearOfFounded, String yearOfIpo, String link) {
		Company company = new Company();
		company.setName(name);
		company.setSector(sector);
		company.setYearOfFounded(yearOfFounded);
		company.setYearOfIpo(yearOfIpo);
		company.setLink(link);
		return company;
	}
	
	/**
	 * Create new revenueData
	 * @param company revenueData company
	 * @param year revenueData year
	 * @param revenue revenue of the year
	 * @param cost cost of the year
	 * @param grossProfit gross profit of the year
	 * @param netIncome net income of the year
	 * @param basicEps basic EPS of the year
	 * @param dividendPayout dividend payout of the year
	 * @return new revenueData
	 */
	public static RevenueData createRevenueData(Company company, String year, BigDecimal revenue, BigDecimal cost, BigDecimal grossProfit, BigDecimal netIncome, BigDecimal basicEps, BigDecimal dividendPayout) {
		RevenueData revenueData = new RevenueData();
		revenueData.setCompany(company);
		revenueData.setYear(year);
		revenueData.setRevenue(revenue);
		revenueData.setCost(cost);
		revenueData.setGrossProfit(grossProfit);
		revenueData.setNetIncome(netIncome);
		revenueData.setBasicEps(basicEps);
		revenueData.setDividendPayout(dividendPayout);
		return revenueData;
	}
	
	/**
	 * Create a new assetsData
	 * @param company assetsData company
	 * @param year assetsData year
	 * @param cashOnHand cash on hand of the year
	 * @param currentAssets current assets of the year
	 * @param totalAssets total assets of the year 
	 * @param currentLiability current liability of the year
	 * @param longTermDebt long term debt of the year
	 * @param equity equity of the year
	 * @return new assetsData
	 */
	public static AssetsData createAssetsData(Company company, String year, BigDecimal cashOnHand, BigDecimal currentAssets, BigDecimal totalAssets, BigDecimal currentLiability, BigDecimal longTermDebt, BigDecimal equity) {
		AssetsData assetsData = new AssetsData();
		assetsData.setCompany(company);
		assetsData.setYear(year);
		assetsData.setCashOnHand(cashOnHand);
		assetsData.setCurrentAssets(currentAssets);
		assetsData.setTotalAssets(totalAssets);
		assetsData.setCurrentLiability(currentLiability);
		assetsData.setLongTermDebt(longTermDebt);
		assetsData.setEquity(equity);
		return assetsData;
	}
	
	/**
	 * Create a new stockSpecification
	 * @param stock stockSpecification stock
	 * @param price price of the stock
	 * @param priceEarningRatio P/E ratio of the stock
	 * @param priceBookRatio P/B ratio of the stock
	 * @return new stockSpecification
	 */
	public static StockSpecification createStockSpecification(Stock stock, BigDecimal price, BigDecimal priceEarningRatio, BigDecimal priceBookRatio) {
		StockSpecification stockSpecification = new StockSpecification();
		stockSpecification.setStock(stock);
		stockSpecification.setPrice(price);
		stockSpecification.setPriceEarningRatio(priceEarningRatio);
		stockSpecification.setPriceBookRatio(priceBookRatio);
		return stockSpecification;
	}
	
	/**
	 * Create new user
	 * @param username username of the user
	 * @param password password of the user
	 * @param lastname last name of the user
	 * @param firstname first name of the user
	 * @param email email of the user
	 * @param active whether the user is active of not
	 * @return new user
	 */
	public static User createUser(String username, String password, String lastname, String firstname, String email, boolean active, Set<Role> roles) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setLastname(lastname);
		user.setFirstname(firstname);
		user.setEmail(email);
		user.setActive(active);
		user.setRoles(roles);
		return user;
	}
	
	/**
	 * Create new inventory
	 * @param user user of the inventory
	 * @param stock stock of the inventory
	 * @param status status of the inventory
	 * @param lastUpdated last updated time of the inventory
	 * @return
	 */
	public static Inventory createInventory(User user, Stock stock, String status, Timestamp lastUpdated) {
		Inventory inventory = new Inventory();
		inventory.setUser(user);
		inventory.setStock(stock);
		inventory.setStatus(status);
		inventory.setLastUpdated(lastUpdated);
		return inventory;
	}
	
}
