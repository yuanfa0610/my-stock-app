package com.rf.privjoy.myStock.dto;

public class StockDTO {
	
	private Long stockId;
	private String symbol;
	private Long companyId;
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
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
