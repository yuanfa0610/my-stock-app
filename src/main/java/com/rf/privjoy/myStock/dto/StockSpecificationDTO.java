package com.rf.privjoy.myStock.dto;

import java.math.BigDecimal;

public class StockSpecificationDTO {
	
	private Long stockSpecificationId;
	private Long stockId;
	private BigDecimal price;
	private BigDecimal priceEarningRatio;
	private BigDecimal priceBookRatio;
	
	public StockSpecificationDTO() {
		
	}

	/**
	 * @return the stockSpecificationId
	 */
	public Long getStockSpecificationId() {
		return stockSpecificationId;
	}

	/**
	 * @param stockSpecificationId the stockSpecificationId to set
	 */
	public void setStockSpecificationId(Long stockSpecificationId) {
		this.stockSpecificationId = stockSpecificationId;
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
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the priceEarningRatio
	 */
	public BigDecimal getPriceEarningRatio() {
		return priceEarningRatio;
	}

	/**
	 * @param priceEarningRatio the priceEarningRatio to set
	 */
	public void setPriceEarningRatio(BigDecimal priceEarningRatio) {
		this.priceEarningRatio = priceEarningRatio;
	}

	/**
	 * @return the priceBookRatio
	 */
	public BigDecimal getPriceBookRatio() {
		return priceBookRatio;
	}

	/**
	 * @param priceBookRatio the priceBookRatio to set
	 */
	public void setPriceBookRatio(BigDecimal priceBookRatio) {
		this.priceBookRatio = priceBookRatio;
	}
	
}
