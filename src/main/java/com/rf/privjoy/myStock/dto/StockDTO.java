package com.rf.privjoy.myStock.dto;

public class StockDTO {
	
	private Long stockId;
	private String symbol;
	private CompanyDTO company;
	private StockSpecificationDTO stockSpecification;
	
	public StockDTO() {
		
	}

	/**
	 * @return the stockId
	 */
	public Long getStockId() {
		return stockId;
	}

	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the company
	 */
	public CompanyDTO getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	/**
	 * @return the stockSpecification
	 */
	public StockSpecificationDTO getStockSpecification() {
		return stockSpecification;
	}

	/**
	 * @param stockSpecification the stockSpecification to set
	 */
	public void setStockSpecification(StockSpecificationDTO stockSpecification) {
		this.stockSpecification = stockSpecification;
	}

}
