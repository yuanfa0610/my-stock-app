package com.rf.privjoy.myStock;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rf.privjoy.myStock.api.SectorExtService;
import com.rf.privjoy.myStock.impl.dao.AssetsDataDao;
import com.rf.privjoy.myStock.impl.dao.CompanyDao;
import com.rf.privjoy.myStock.impl.dao.InventoryDao;
import com.rf.privjoy.myStock.impl.dao.RecordDao;
import com.rf.privjoy.myStock.impl.dao.RevenueDataDao;
import com.rf.privjoy.myStock.impl.dao.RoleDao;
import com.rf.privjoy.myStock.impl.dao.SectorDao;
import com.rf.privjoy.myStock.impl.dao.StockDao;
import com.rf.privjoy.myStock.impl.dao.UserDao;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateAssetsDataDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateCompanyDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateInventoryDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateRecordDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateRevenueDataDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateRoleDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateSectorDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateStockDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateUserDaoImpl;
import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateUtil;
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
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;

public class MyStockTest {
	
	public static void main(String[] args) {
		
		MyStockTest myStockTest = new MyStockTest();
		
		// load the spring configuration file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyStockConfiguration.class);
		
		SectorExtService sectorSerive = context.getBean("sectorExtServiceImpl", SectorExtService.class);
		
		// retrieve bean from spring container
		MyStockDataService dataService = context.getBean("myStockDataServiceImpl", MyStockDataService.class);
		
		context.close();
		
		//myStockTest.directConect();
		
		myStockTest.connectViaConfigurationFile();

	}
	
	public void connectViaConfigurationFile() {
		// create session factory
		/**
		SessionFactory factory = new Configuration()
									.configure("com/rf/privjoy/myStock/hibernate.cfg.xml")
									.addAnnotatedClass(AssetsData.class)
									.addAnnotatedClass(Company.class)
									.addAnnotatedClass(Inventory.class)
									.addAnnotatedClass(Record.class)
									.addAnnotatedClass(RevenueData.class)
									.addAnnotatedClass(Role.class)
									.addAnnotatedClass(RoleName.class)
									.addAnnotatedClass(Sector.class)
									.addAnnotatedClass(Stock.class)
									.addAnnotatedClass(StockSpecification.class)
									.addAnnotatedClass(User.class)
									.buildSessionFactory();
									**/
		
		try {
			
			populateDataWithDao();
			//populateDataWithoutDao(factory);
			
		} finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
	
	public void populateDataWithDao() {
		
		// Create new sector 'Finance'
		Sector sector1 = new Sector("Finance");
		// Create new sector 'Retail/Wholesale'
		Sector sector2 = new Sector("Retail/Wholesale");
		
		// Save sector 1 and sector 2
		SectorDao sectorDao = new HibernateSectorDaoImpl();
		sectorDao.save(sector1);
		sectorDao.save(sector2);
		
		//Create new company 'JPMorgan Chase'
		Company company1 = new Company("JPMorgan Chase", "1799", "1983", "https://www.jpmorgan.com/country/US/en/jpmorgan");
		sector1.addCompany(company1);
		// Create new company Amazon'
		Company company2 = new Company("Amazon", "1994", "1997", "https://www.amazon.com");
		sector2.addCompany(company2);
		
		// Save company 1 and company 2
		CompanyDao companyDao = new HibernateCompanyDaoImpl();
		companyDao.save(company1);
		companyDao.save(company2);
		
		// Create revenue data for company 1
		RevenueData revenueData1_1 = new RevenueData("2017", BigDecimal.valueOf(113899000000L), BigDecimal.valueOf(14275000000L), BigDecimal.valueOf(99624000000L), BigDecimal.valueOf(22567000000L), BigDecimal.valueOf(6.35), BigDecimal.valueOf(2.08));
		RevenueData revenueData1_2 = new RevenueData("2016", BigDecimal.valueOf(105486000000L), BigDecimal.valueOf(9818000000L), BigDecimal.valueOf(95668000000L), BigDecimal.valueOf(22834000000L), BigDecimal.valueOf(6.24), BigDecimal.valueOf(1.60));
		RevenueData revenueData1_3 = new RevenueData("2015", BigDecimal.valueOf(101006000000L), BigDecimal.valueOf(7463000000L), BigDecimal.valueOf(93543000000L), BigDecimal.valueOf(22651000000L), BigDecimal.valueOf(6.05), BigDecimal.valueOf(1.43));
		company1.addRevenueData(revenueData1_1);
		company1.addRevenueData(revenueData1_2);
		company1.addRevenueData(revenueData1_3);
		
		// Save all revenue data
		RevenueDataDao revenueDataDao = new HibernateRevenueDataDaoImpl();
		revenueDataDao.saveOrUpdateAll(Arrays.asList(revenueData1_1, revenueData1_2, revenueData1_3));
		
		// Create assets data for company 1
		AssetsData assetsData1_1 = new AssetsData("2017", BigDecimal.valueOf(1010387000000L), BigDecimal.valueOf(1995209000000L), BigDecimal.valueOf(2533600000000L), BigDecimal.valueOf(1967746000000L), BigDecimal.valueOf(284080000000L), BigDecimal.valueOf(255693000000L));
		AssetsData assetsData1_2 = new AssetsData("2016", BigDecimal.valueOf(991732000000L), BigDecimal.valueOf(1925051000000L), BigDecimal.valueOf(2490972000000L), BigDecimal.valueOf(1902490000000L), BigDecimal.valueOf(295245000000L), BigDecimal.valueOf(254190000000L));
		AssetsData assetsData1_3 = new AssetsData("2015", BigDecimal.valueOf(916919000000L), BigDecimal.valueOf(1787268000000L), BigDecimal.valueOf(2351698000000L), BigDecimal.valueOf(1752490000000L), BigDecimal.valueOf(309756000000L), BigDecimal.valueOf(247573000000L));
		company1.addAssetsData(assetsData1_1);
		company1.addAssetsData(assetsData1_2);
		company1.addAssetsData(assetsData1_3);
		
		// Save all assets data
		AssetsDataDao assetsDataDao = new HibernateAssetsDataDaoImpl();
		assetsDataDao.saveOrUpdateAll(Arrays.asList(assetsData1_1, assetsData1_2, assetsData1_3));
		
		// Create new stock for company 1
		Stock stock1 = new Stock("JPM");
		company1.addStock(stock1);
		
		// Create new stock for company 2
		Stock stock2 = new Stock("AMZN");
		company2.addStock(stock2);
		
		// Save both stocks
		StockDao stockDao = new HibernateStockDaoImpl();
		stockDao.save(stock1);
		stockDao.save(stock2);
		
		// Create specification for stock 1
		StockSpecification stockSpecification1 = new StockSpecification(BigDecimal.valueOf(109.26), BigDecimal.valueOf(12.18), BigDecimal.valueOf(1.58));
		stock1.setStockSpecification(stockSpecification1);
		
		// Create specification for stock 2
		StockSpecification stockSpecification2 = new StockSpecification(BigDecimal.valueOf(1581.42), BigDecimal.valueOf(97.31), BigDecimal.valueOf(19.76));
		stock2.setStockSpecification(stockSpecification2);
		
		// Update both stocks with specifications
		stockDao.saveOrUpdate(stock1);
		stockDao.saveOrUpdate(stock2);
		
		// Create 3 new roles
		Role role1 = new Role("stock.admin");
		Role role2 = new Role("stock.agent");
		Role role3 = new Role("stock.client");
		
		// Save all roles
		RoleDao roleDao = new HibernateRoleDaoImpl();
		roleDao.saveOrUpdateAll(Arrays.asList(role1, role2, role3));
		
		// Create new user 1 and add the user to role 1
		User user1 = new User("auto.test1", "pass", "Test1", "Auto", "auto.test1@gmail.com", true);
		role1.addUser(user1);
		
		// Save user 1
		UserDao userDao = new HibernateUserDaoImpl();
		userDao.save(user1);
		
		// Update role 1 to create entry in user_role table
		roleDao.saveOrUpdate(role1);
		
		// Create new user 2 and add the user to role 1, role 2, and role 3
		User user2 = new User("auto.test2", "pass", "Test2", "Auto", "auto.test2@gmail.com", true);
		role1.addUser(user2);
		role2.addUser(user2);
		role3.addUser(user2);
		
		// Save user 2
		userDao.save(user2);
		
		// Update role 1, role 2, and role 3 to create 3 entries in user_role table
		roleDao.saveOrUpdateAll(Arrays.asList(role1, role2, role3));
		
		// Get current timestamp
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		
		// Create 1 new inventory
		Inventory inventory1 = new Inventory(stock1, user1, Constants.INVENTORY_STATUS_WATCHING, timestamp);
		user1.addInventory(inventory1);
		stock1.addInventory(inventory1);
		
		// Save the inventory
		InventoryDao inventoryDao = new HibernateInventoryDaoImpl();
		inventoryDao.save(inventory1);
		
		// Create 1 new record
		Record record1 = new Record(stock1, user1, stock1.getStockSpecification().getPrice(), Constants.RECORD_STATUS_WATCH, timestamp);
		
		// Save the record
		RecordDao recordDao = new HibernateRecordDaoImpl();
		recordDao.save(record1);
		
		System.out.println("Done with the operation");
		
	}
	
	public void populateDataWithoutDao(SessionFactory factory) {
		
		// Create new sector 'Finance'
		Sector sector1 = new Sector("Finance");
		// Create new sector 'Retail/Wholesale'
		Sector sector2 = new Sector("Retail/Wholesale");
		
		// Save sector 1 and sector 2
		save(factory, sector1);
		save(factory, sector2);
		
		//Create new company 'JPMorgan Chase'
		Company company1 = new Company("JPMorgan Chase", "1799", "1983", "https://www.jpmorgan.com/country/US/en/jpmorgan");
		sector1.addCompany(company1);
		// Create new company Amazon'
		Company company2 = new Company("Amazon", "1994", "1997", "https://www.amazon.com");
		sector2.addCompany(company2);
		
		// Save company 1
		save(factory, company1);
		// Save company 2
		save(factory, company2);
		
		// Create revenue data for company 1
		RevenueData revenueData1_1 = new RevenueData("2017", BigDecimal.valueOf(113899000000L), BigDecimal.valueOf(14275000000L), BigDecimal.valueOf(99624000000L), BigDecimal.valueOf(22567000000L), BigDecimal.valueOf(6.35), BigDecimal.valueOf(2.08));
		RevenueData revenueData1_2 = new RevenueData("2016", BigDecimal.valueOf(105486000000L), BigDecimal.valueOf(9818000000L), BigDecimal.valueOf(95668000000L), BigDecimal.valueOf(22834000000L), BigDecimal.valueOf(6.24), BigDecimal.valueOf(1.60));
		RevenueData revenueData1_3 = new RevenueData("2015", BigDecimal.valueOf(101006000000L), BigDecimal.valueOf(7463000000L), BigDecimal.valueOf(93543000000L), BigDecimal.valueOf(22651000000L), BigDecimal.valueOf(6.05), BigDecimal.valueOf(1.43));
		company1.addRevenueData(revenueData1_1);
		company1.addRevenueData(revenueData1_2);
		company1.addRevenueData(revenueData1_3);
		
		// Save all revenue data
		save(factory, revenueData1_1);
		save(factory, revenueData1_2);
		save(factory, revenueData1_3);
		
		// Create assets data for company 1
		AssetsData assetsData1_1 = new AssetsData("2017", BigDecimal.valueOf(1010387000000L), BigDecimal.valueOf(1995209000000L), BigDecimal.valueOf(2533600000000L), BigDecimal.valueOf(1967746000000L), BigDecimal.valueOf(284080000000L), BigDecimal.valueOf(255693000000L));
		AssetsData assetsData1_2 = new AssetsData("2016", BigDecimal.valueOf(991732000000L), BigDecimal.valueOf(1925051000000L), BigDecimal.valueOf(2490972000000L), BigDecimal.valueOf(1902490000000L), BigDecimal.valueOf(295245000000L), BigDecimal.valueOf(254190000000L));
		AssetsData assetsData1_3 = new AssetsData("2015", BigDecimal.valueOf(916919000000L), BigDecimal.valueOf(1787268000000L), BigDecimal.valueOf(2351698000000L), BigDecimal.valueOf(1752490000000L), BigDecimal.valueOf(309756000000L), BigDecimal.valueOf(247573000000L));
		company1.addAssetsData(assetsData1_1);
		company1.addAssetsData(assetsData1_2);
		company1.addAssetsData(assetsData1_3);
		
		// Save all assets data
		save(factory, assetsData1_1);
		save(factory, assetsData1_2);
		save(factory, assetsData1_3);
		
		// Create new stock for company 1
		Stock stock1 = new Stock("JPM");
		company1.addStock(stock1);
		// Create specification for stock 1
		StockSpecification stockSpecification1 = new StockSpecification(BigDecimal.valueOf(109.26), BigDecimal.valueOf(12.18), BigDecimal.valueOf(1.58));
		stock1.setStockSpecification(stockSpecification1);
		
		// Create new stock for company 2
		Stock stock2 = new Stock("AMZN");
		company2.addStock(stock2);
		// Create specification for stock 2
		StockSpecification stockSpecification2 = new StockSpecification(BigDecimal.valueOf(1581.42), BigDecimal.valueOf(97.31), BigDecimal.valueOf(19.76));
		stock2.setStockSpecification(stockSpecification2);
		
		// Save both stocks and their specifications
		save(factory, stock1);
		save(factory, stock2);
		
		// Create 3 new roles
		Role role1 = new Role("stock.admin");
		Role role2 = new Role("stock.agent");
		Role role3 = new Role("stock.client");
		
		// Create new user and add the user to a role
		User user = new User("auto.test", "pass", "Test", "Auto", "auto.test@gmail.com", true);
		role1.addUser(user);
		
		// Save the user
		save(factory, user);
		
		// Save all roles
		save(factory, role1);
		save(factory, role2);
		save(factory, role3);
		
		// Get current timestamp
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		
		// Create 1 new inventory and save it
		Inventory inventory1 = new Inventory(stock1, user, Constants.INVENTORY_STATUS_WATCHING, timestamp);
		save(factory, inventory1);
		
		// Create 1 new record and save it
		Record record1 = new Record(stock1, user, stock1.getStockSpecification().getPrice(), Constants.RECORD_STATUS_WATCH, timestamp);
		save(factory, record1);
		
		// Get current timestamp
		timestamp = Timestamp.valueOf(LocalDateTime.now());
		
		// Create 1 new inventory and save it
		Inventory inventory2 = new Inventory(stock2, user, Constants.INVENTORY_STATUS_HOLDING, timestamp);
		save(factory, inventory2);
		
		// Create 1 new record and save it
		Record record2 = new Record(stock2, user, stock2.getStockSpecification().getPrice(), Constants.RECORD_STATUS_BUY, timestamp);
		save(factory, record2);
		
		System.out.println("Done with the operation");
		
	}
	
	public void save(SessionFactory factory, Object entity) {
		// create session	
		Session session = factory.getCurrentSession();
		
		// start a transaction
		System.out.println("Starting a new transaction");
		session.beginTransaction();
		
		// Save Entity
		session.save(entity);
		
		// commit transaction
		System.out.println("Committing the transaction");
		session.getTransaction().commit();
		
		// close session
		session.close();
	}
	
	public void directConect() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/stock_management?useSSL=false&serverTimezone=EST";
		String user = "root";
		String pass = "Kimi19791017!";
		
		try {
			
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.print("Connection Successful!!!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
