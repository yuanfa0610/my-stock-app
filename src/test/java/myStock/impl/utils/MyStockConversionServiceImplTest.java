package myStock.impl.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.rf.privjoy.myStock.dto.AssetsDataDTO;
import com.rf.privjoy.myStock.dto.CompanyDTO;
import com.rf.privjoy.myStock.dto.RevenueDataDTO;
import com.rf.privjoy.myStock.dto.SectorDTO;
import com.rf.privjoy.myStock.dto.StockDTO;
import com.rf.privjoy.myStock.impl.persistent.AssetsData;
import com.rf.privjoy.myStock.impl.persistent.Company;
import com.rf.privjoy.myStock.impl.persistent.RevenueData;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.persistent.Stock;
import com.rf.privjoy.myStock.impl.persistent.StockSpecification;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionServiceImpl;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;

import myStock.test.utils.CommonTestUtils;

public class MyStockConversionServiceImplTest {
	
	private MyStockConversionServiceImpl spiedMyStockConversionServiceImpl;
	private MyStockDataService mockMyStockDataService;
	
	private static final Long SECTOR_ID = 111122223333L;
	private static final Long SECTOR_ID_SECOND = 555566667777l;
	private static final String SECTOR_NAME = "Sector Test Name";
	private static final String SECTOR_NAME_SECOND = "Sector Test Name 2";
	
	private static final Long COMPANY_ID = 1212121212L;
	private static final Long COMPANY_ID_SECOND = 21212121L;
	private static final String COMPANY_NAME = "Company Test Name";
	private static final String COMPANY_NAME_SECOND = "Company Test Name 2";
	private static final String COMPANY_YEAR_OF_FOUNDED = "1990";
	private static final String COMPANY_YEAR_OF_IPO = "2000";
	private static final String COMPANY_LINK = "company_test@gmail.com";
	
	private static final Long REVENUE_DATA_ID = 223322L;
	private static final Long REVENUE_DATA_ID_SECOND = 766444L;
	private static final String REVENUE_DATA_YEAR = "1800";
	private static final BigDecimal REVENUE_DATA_REVENUE =  BigDecimal.valueOf(1000);
	private static final BigDecimal REVENUE_DATA_COST = BigDecimal.valueOf(900);
	private static final BigDecimal REVENUE_DATA_GROSS_PROFIT = BigDecimal.valueOf(800);
	private static final BigDecimal REVENUE_DATA_NET_INCOME = BigDecimal.valueOf(700);
	private static final BigDecimal REVENUE_DATA_BASIC_EPS = BigDecimal.valueOf(10.10);
	private static final BigDecimal REVENUE_DATA_DIVIDEND_PAYOUT = BigDecimal.valueOf(9.10);

	private static final Long ASSETS_DATA_ID = 433221L;
	private static final Long ASSETS_DATA_ID_SECOND = 983222L;
	private static final String ASSETS_DATA_YEAR = "1900";
	private static final BigDecimal ASSETS_DATA_CASH_ON_HAND = BigDecimal.valueOf(2000);
	private static final BigDecimal ASSETS_DATA_CURRENT_ASSETS = BigDecimal.valueOf(1800);
	private static final BigDecimal ASSETS_DATA_TOTAL_ASSETS = BigDecimal.valueOf(1600);
	private static final BigDecimal ASSETS_DATA_CURRENT_LIABILITY = BigDecimal.valueOf(1400);
	private static final BigDecimal ASSETS_DATA_LONG_TERM_DEBT = BigDecimal.valueOf(1200);
	private static final BigDecimal ASSETS_DATA_EQUITY = BigDecimal.valueOf(1000);
	
	private static final Long STOCK_ID = 123321L;
	private static final String STOCK_SYMBOL = "Stock Test Symbol";
	private static final BigDecimal STOCK_PRICE = BigDecimal.valueOf(210.10);
	private static final BigDecimal STOCK_PRICE_EARNING_RATIO = BigDecimal.valueOf(25.00);
	private static final BigDecimal STOCK_PRICE_BOOK_RATIO = BigDecimal.valueOf(13.55);
	
	@Before
	public void dataSetup() {
		this.spiedMyStockConversionServiceImpl = spy(MyStockConversionServiceImpl.class);
		this.mockMyStockDataService = getMockMyStockDataService();
		this.spiedMyStockConversionServiceImpl.setMyStockDataService(mockMyStockDataService);
	}
	
	@Test
	public void test_sectorConversion() {
		Sector sector = CommonTestUtils.createSector(SECTOR_NAME);
		sector.setId(SECTOR_ID);
		
		SectorDTO sectorDTO = spiedMyStockConversionServiceImpl.convertToDTO(sector);
		
		assertEquals("Sector ID is not converted successfully", SECTOR_ID, sectorDTO.getSectorId());
		assertEquals("Sector name is not converted successfully", SECTOR_NAME, sectorDTO.getName());
		
		sector = spiedMyStockConversionServiceImpl.convertToPersistedObject(sectorDTO);
		
		assertEquals("SectorDTO ID is not converted successfully", SECTOR_ID, sector.getId());
		assertEquals("SectorDTO name is not converted successfully", SECTOR_NAME, sector.getName());
	}
	
	@Test
	public void test_sectorsConversion() {
		Sector sector1 = CommonTestUtils.createSector(SECTOR_NAME);
		sector1.setId(SECTOR_ID);
		Sector sector2 = CommonTestUtils.createSector(SECTOR_NAME_SECOND);
		sector2.setId(SECTOR_ID_SECOND);
		List<Sector> sectors = Arrays.asList(sector1, sector2);
		
		List<SectorDTO> sectorDTOs = spiedMyStockConversionServiceImpl.convertToSectorDTOs(sectors);
		
		assertTrue("Number of items in sectorDTOs is incorrect", sectorDTOs.size() == 2);
		assertEquals("Sector ID for the first entry is not converted successfully", SECTOR_ID, sectorDTOs.get(0).getSectorId());
		assertEquals("Sector name for the first entry is not converted successfully", SECTOR_NAME, sectorDTOs.get(0).getName());
		assertEquals("Sector ID for the second entry is not converted successfully", SECTOR_ID_SECOND, sectorDTOs.get(1).getSectorId());
		assertEquals("Sector name for the second entry is not converted successfully", SECTOR_NAME_SECOND, sectorDTOs.get(1).getName());
	}
	
	@Test
	public void test_companyConversion() {
		Sector sector = new Sector();
		sector.setId(SECTOR_ID);
		Company company = CommonTestUtils.createCompany(COMPANY_NAME, sector, COMPANY_YEAR_OF_FOUNDED, COMPANY_YEAR_OF_IPO, COMPANY_LINK);
		company.setId(COMPANY_ID);
		RevenueData revenueData1 = new RevenueData();
		revenueData1.setCompany(company);
		revenueData1.setId(REVENUE_DATA_ID);
		RevenueData revenueData2 = new RevenueData();
		revenueData2.setId(REVENUE_DATA_ID_SECOND);
		revenueData2.setCompany(company);
		Set<RevenueData> revenueDataCollection = new HashSet<>(Arrays.asList(revenueData1, revenueData2));
		AssetsData assetsData1 = new AssetsData();
		assetsData1.setId(ASSETS_DATA_ID);
		assetsData1.setCompany(company);
		AssetsData assetsData2 = new AssetsData();
		assetsData2.setId(ASSETS_DATA_ID_SECOND);
		assetsData2.setCompany(company);
		Set<AssetsData> assetsDataCollection = new HashSet<>(Arrays.asList(assetsData1, assetsData2));
		company.setRevenueDataCollection(revenueDataCollection);
		company.setAssetsDataCollection(assetsDataCollection);
		
		CompanyDTO companyDTO = spiedMyStockConversionServiceImpl.convertToDTO(company);
		
		assertEquals("Company ID is not converted successfully", COMPANY_ID, companyDTO.getCompanyId());
		assertEquals("Company name is not converted successfully", COMPANY_NAME, companyDTO.getName());
		assertEquals("Company yearOfFounded is not converted successfully", COMPANY_YEAR_OF_FOUNDED, companyDTO.getYearOfFounded());
		assertEquals("Company yearOfIpo is not converted successfully", COMPANY_YEAR_OF_IPO, companyDTO.getYearOfIpo());
		assertEquals("Company link is not converted successfully", COMPANY_LINK, companyDTO.getLink());
		assertEquals("Company sector is not converted successfully", SECTOR_ID, companyDTO.getSectorId());
		assertTrue("Number of items in companyDTO revenueDataCollection is incorrect", companyDTO.getRevenueDataCollection().size() == 2);
		assertTrue("Number of items in companyDTO assetsDataCollection is incorrect", companyDTO.getAssetsDataCollection().size() == 2);
		
		company = spiedMyStockConversionServiceImpl.convertToPersistedObject(companyDTO);
		
		assertEquals("CompanyDTO ID is not converted successfully", COMPANY_ID, company.getId());
		assertEquals("CompanyDTO name is not converted successfully", COMPANY_NAME, company.getName());
		assertEquals("CompanyDTO yearOfFounded is not converted successfully", COMPANY_YEAR_OF_FOUNDED, company.getYearOfFounded());
		assertEquals("CompanyDTO yearOfIpo is not converted successfully", COMPANY_YEAR_OF_IPO, company.getYearOfIpo());
		assertEquals("CompanyDTO link is not converted successfully", COMPANY_LINK, company.getLink());
		assertEquals("CompanyDTO sector is not converted successfully", SECTOR_ID, company.getSector().getId());
		assertTrue("Number of items in company revenueDataCollection is incorrect", company.getRevenueDataCollection().size() == 2);
		assertTrue("Number of items in company assetsDataCollection is incorrect", company.getAssetsDataCollection().size() == 2);
	}
	
	@Test
	public void test_companiesConversion() {
		Sector sector1 = new Sector();
		sector1.setId(SECTOR_ID);
		Company company1 = CommonTestUtils.createCompany(COMPANY_NAME, sector1, null, null, null);
		company1.setId(COMPANY_ID);
		Sector sector2 = new Sector();
		sector2.setId(SECTOR_ID_SECOND);
		Company company2 = CommonTestUtils.createCompany(COMPANY_NAME_SECOND, sector2, null, null, null);
		company2.setId(COMPANY_ID_SECOND);
		List<Company> companies = Arrays.asList(company1, company2);
		
		List<CompanyDTO> companyDTOs = spiedMyStockConversionServiceImpl.convertToCompanyDTOs(companies);
		
		assertTrue("Number of items in companyDTOs is incorrect", companyDTOs.size() == 2);
		assertEquals("Company ID for the first entry is not converted successfully", COMPANY_ID, companyDTOs.get(0).getCompanyId());
		assertEquals("Company name for the first entry is not converted successfully", COMPANY_NAME, companyDTOs.get(0).getName());
		assertEquals("Company sector for the first entry is not converted successfully", SECTOR_ID, companyDTOs.get(0).getSectorId());
		assertEquals("Company ID for the second entry is not converted successfully", COMPANY_ID_SECOND, companyDTOs.get(1).getCompanyId());
		assertEquals("Company name for the second entry is not converted successfully", COMPANY_NAME_SECOND, companyDTOs.get(1).getName());
		assertEquals("Company sector for the second entry is not converted successfully", SECTOR_ID_SECOND, companyDTOs.get(1).getSectorId());
	}
	
	@Test
	public void test_revenueDataConversion() {
		Company company = CommonTestUtils.createCompany(COMPANY_NAME, null, null, null, null);
		company.setId(COMPANY_ID);
		RevenueData revenueData = CommonTestUtils.createRevenueData(company, REVENUE_DATA_YEAR, REVENUE_DATA_REVENUE, REVENUE_DATA_COST, REVENUE_DATA_GROSS_PROFIT, 
																	REVENUE_DATA_NET_INCOME, REVENUE_DATA_BASIC_EPS, REVENUE_DATA_DIVIDEND_PAYOUT);
		revenueData.setId(REVENUE_DATA_ID);
		
		RevenueDataDTO revenueDataDTO = spiedMyStockConversionServiceImpl.convertToDTO(revenueData);
		
		assertEquals("RevenueData id is not converted successfully", REVENUE_DATA_ID, revenueDataDTO.getRevenueDataId());
		assertEquals("RevenueData company is not converted successfully", COMPANY_ID, revenueDataDTO.getCompanyId());
		assertEquals("RevenueData year is not converted successfully", REVENUE_DATA_YEAR, revenueDataDTO.getYear());
		assertEquals("RevenueData revenue is not converted successfully", REVENUE_DATA_REVENUE, revenueDataDTO.getRevenue());
		assertEquals("RevenueData cost is not converted successfully", REVENUE_DATA_COST, revenueDataDTO.getCost());
		assertEquals("RevenueData gross profit is not converted successfully", REVENUE_DATA_GROSS_PROFIT, revenueDataDTO.getGrossProfit());
		assertEquals("RevenueData net income is not converted successfully", REVENUE_DATA_NET_INCOME, revenueDataDTO.getNetIncome());
		assertEquals("RevenueData basic EPS is not converted successfully", REVENUE_DATA_BASIC_EPS, revenueDataDTO.getBasicEps());
		assertEquals("RevenueData dividend payout is not converted successfully", REVENUE_DATA_DIVIDEND_PAYOUT, revenueDataDTO.getDividendPayout());
		
		revenueData = spiedMyStockConversionServiceImpl.convertToPersistedObject(revenueDataDTO);
		
		assertEquals("RevenueDataDTO id is not converted successfully", REVENUE_DATA_ID, revenueData.getId());
		assertEquals("RevenueDataDTO company is not converted successfully", COMPANY_ID, revenueData.getCompany().getId());
		assertEquals("RevenueDataDTO year is not converted successfully", REVENUE_DATA_YEAR, revenueData.getYear());
		assertEquals("RevenueDataDTO revenue is not converted successfully", REVENUE_DATA_REVENUE, revenueData.getRevenue());
		assertEquals("RevenueDataDTO cost is not converted successfully", REVENUE_DATA_COST, revenueData.getCost());
		assertEquals("RevenueDataDTO gross profit is not converted successfully", REVENUE_DATA_GROSS_PROFIT, revenueData.getGrossProfit());
		assertEquals("RevenueDataDTO net income is not converted successfully", REVENUE_DATA_NET_INCOME, revenueData.getNetIncome());
		assertEquals("RevenueDataDTO basic EPS is not converted successfully", REVENUE_DATA_BASIC_EPS, revenueData.getBasicEps());
		assertEquals("RevenueDataDTO dividend payout is not converted successfully", REVENUE_DATA_DIVIDEND_PAYOUT, revenueData.getDividendPayout());
	}
	
	@Test
	public void test_assetsDataConversion() {
		Company company = CommonTestUtils.createCompany(COMPANY_NAME, null, null, null, null);
		company.setId(COMPANY_ID);
		AssetsData assetsData = CommonTestUtils.createAssetsData(company, ASSETS_DATA_YEAR, ASSETS_DATA_CASH_ON_HAND, ASSETS_DATA_CURRENT_ASSETS, ASSETS_DATA_TOTAL_ASSETS, 
																ASSETS_DATA_CURRENT_LIABILITY, ASSETS_DATA_LONG_TERM_DEBT, ASSETS_DATA_EQUITY);
		assetsData.setId(ASSETS_DATA_ID);
		
		AssetsDataDTO assetsDataDTO = spiedMyStockConversionServiceImpl.convertToDTO(assetsData);
		
		assertEquals("AssetsData id is not converted successfully", ASSETS_DATA_ID, assetsDataDTO.getAssetsDataId());
		assertEquals("AssetsData company is not converted successfully", COMPANY_ID, assetsDataDTO.getCompanyId());
		assertEquals("AssetsData year is not converted successfully", ASSETS_DATA_YEAR, assetsDataDTO.getYear());
		assertEquals("AssetsData cash on hand is not converted successfully", ASSETS_DATA_CASH_ON_HAND, assetsDataDTO.getCashOnHand());
		assertEquals("AssetsData current assets is not converted successfully", ASSETS_DATA_CURRENT_ASSETS, assetsDataDTO.getCurrentAssets());
		assertEquals("AssetsData total assets is not converted successfully", ASSETS_DATA_TOTAL_ASSETS, assetsDataDTO.getTotalAssets());
		assertEquals("AssetsData current liability is not converted successfully", ASSETS_DATA_CURRENT_LIABILITY, assetsDataDTO.getCurrentLiability());
		assertEquals("AssetsData long term debt is not converted successfully", ASSETS_DATA_LONG_TERM_DEBT, assetsDataDTO.getLongTermDebt());
		assertEquals("AssetsData data equity is not converted successfully", ASSETS_DATA_EQUITY, assetsDataDTO.getEquity());
		
		assetsData = spiedMyStockConversionServiceImpl.convertToPersistedObject(assetsDataDTO);
		
		assertEquals("AssetsDataDTO id is not converted successfully", ASSETS_DATA_ID, assetsData.getId());
		assertEquals("AssetsDataDTO company is not converted successfully", COMPANY_ID, assetsData.getCompany().getId());
		assertEquals("AssetsDataDTO year is not converted successfully", ASSETS_DATA_YEAR, assetsData.getYear());
		assertEquals("AssetsDataDTO cash on hand is not converted successfully", ASSETS_DATA_CASH_ON_HAND, assetsData.getCashOnHand());
		assertEquals("AssetsDataDTO current assets is not converted successfully", ASSETS_DATA_CURRENT_ASSETS, assetsData.getCurrentAssets());
		assertEquals("AssetsDataDTO total assets is not converted successfully", ASSETS_DATA_TOTAL_ASSETS, assetsData.getTotalAssets());
		assertEquals("AssetsDataDTO current liability is not converted successfully", ASSETS_DATA_CURRENT_LIABILITY, assetsData.getCurrentLiability());
		assertEquals("AssetsDataDTO long term debt is not converted successfully", ASSETS_DATA_LONG_TERM_DEBT, assetsData.getLongTermDebt());
		assertEquals("AssetsDataDTO data equity is not converted successfully", ASSETS_DATA_EQUITY, assetsData.getEquity());
	}
	
	@Test
	public void test_stockConversion() {
		Sector sector1 = new Sector();
		sector1.setId(SECTOR_ID);
		Company company1 = CommonTestUtils.createCompany(COMPANY_NAME, sector1, null, null, null);
		company1.setId(COMPANY_ID);
		Stock stock = new Stock();
		stock.setId(STOCK_ID);
		stock.setSymbol(STOCK_SYMBOL);
		stock.setCompany(company1);
		StockSpecification stockSpecification = CommonTestUtils.createStockSpecification(stock, STOCK_PRICE, STOCK_PRICE_EARNING_RATIO, STOCK_PRICE_BOOK_RATIO);
		stock.setStockSpecification(stockSpecification);
		
		StockDTO stockDTO = spiedMyStockConversionServiceImpl.convertToDTO(stock);
		
		assertEquals("Stock id is not converted successfully", STOCK_ID, stockDTO.getStockId());
		assertEquals("Stock symbol is not converted successfully", STOCK_SYMBOL, stockDTO.getSymbol());
		assertEquals("Stock company is not converted successfully", COMPANY_ID, stockDTO.getCompanyId());
		assertEquals("Stock price is not converted successfully", STOCK_PRICE, stockDTO.getStockSpecification().getPrice());
		assertEquals("Stock price earning ratio is not converted successfully", STOCK_PRICE_EARNING_RATIO, stockDTO.getStockSpecification().getPriceEarningRatio());
		assertEquals("Stock price book ratio is not converted successfully", STOCK_PRICE_BOOK_RATIO, stockDTO.getStockSpecification().getPriceBookRatio());
		
		stock = spiedMyStockConversionServiceImpl.convertToPersistedObject(stockDTO);
		
		assertEquals("StockDTO id is not converted successfully", STOCK_ID, stock.getId());
		assertEquals("StockDTO symbol is not converted successfully", STOCK_SYMBOL, stock.getSymbol());
		assertEquals("StockDTO company is not converted successfully", COMPANY_ID, stock.getCompany().getId());
		assertEquals("StockDTO price is not converted successfully", STOCK_PRICE, stock.getStockSpecification().getPrice());
		assertEquals("StockDTO price earning ratio is not converted successfully", STOCK_PRICE_EARNING_RATIO, stock.getStockSpecification().getPriceEarningRatio());
		assertEquals("StockDTO price book ratio is not converted successfully", STOCK_PRICE_BOOK_RATIO, stock.getStockSpecification().getPriceBookRatio());
	}
	
	/**
	 * Get mock MyStockDataService
	 * @return mock MyStockDataService
	 */
	private MyStockDataService getMockMyStockDataService() {
		MyStockDataService mockMyStockDataService = mock(MyStockDataService.class);
		
		Sector sector = new Sector();
		sector.setId(SECTOR_ID);
		doReturn(sector).when(mockMyStockDataService).getSectorById(SECTOR_ID);
		
		Company company = new Company();
		company.setId(COMPANY_ID);
		doReturn(company).when(mockMyStockDataService).getCompanyById(COMPANY_ID);
		
		return mockMyStockDataService;
	}

}
