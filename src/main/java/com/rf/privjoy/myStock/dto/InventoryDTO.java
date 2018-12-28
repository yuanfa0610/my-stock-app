package com.rf.privjoy.myStock.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class InventoryDTO {
	
	private Long inventoryId;
	private StockDTO stock;
	private UserDTO user;
	private BigDecimal stockPrice;
	private BigDecimal purchaseQuantity;

	private String status;
	private Timestamp time;
	
	public InventoryDTO() {
		
	}

	/**
	 * @return the inventoryId
	 */
	public Long getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the stock
	 */
	public StockDTO getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(StockDTO stock) {
		this.stock = stock;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	/**
	 * @return the stockPrice
	 */
	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	/**
	 * @param stockPrice the stockPrice to set
	 */
	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	/**
	 * @return the purchaseQuantity
	 */
	public BigDecimal getPurchaseQuantity() {
		return purchaseQuantity;
	}

	/**
	 * @param purchaseQuantity the purchaseQuantity to set
	 */
	public void setPurchaseQuantity(BigDecimal purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
