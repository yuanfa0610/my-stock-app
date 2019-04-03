package com.rf.privjoy.myStock.dto;

import java.math.BigDecimal;

public class AssetsDataDTO {
	
	private Long assetsDataId;
	private Long companyId;
	private String year;
	private BigDecimal cashOnHand;
	private BigDecimal currentAssets;
	private BigDecimal totalAssets;
	private BigDecimal currentLiability;
	private BigDecimal longTermDebt;
	private BigDecimal equity;
	
	public AssetsDataDTO() {
		
	}
	
	/**
	 * @return the assetsDataId
	 */
	public Long getAssetsDataId() {
		return assetsDataId;
	}
	
	/**
	 * @param assetsDataId the assetsDataId to set
	 */
	public void setAssetsDataId(Long assetsDataId) {
		this.assetsDataId = assetsDataId;
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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * @param year the year to set
	 */
	
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the cashOnHand
	 */
	
	public BigDecimal getCashOnHand() {
		return cashOnHand;
	}
	
	/**
	 * @param cashOnHand the cashOnHand to set
	 */
	public void setCashOnHand(BigDecimal cashOnHand) {
		this.cashOnHand = cashOnHand;
	}
	
	/**
	 * @return the currentAssets
	 */
	public BigDecimal getCurrentAssets() {
		return currentAssets;
	}
	
	/**
	 * @param currentAssets the currentAssets to set
	 */
	public void setCurrentAssets(BigDecimal currentAssets) {
		this.currentAssets = currentAssets;
	}
	
	/**
	 * @return the totalAssets
	 */
	public BigDecimal getTotalAssets() {
		return totalAssets;
	}
	
	/**
	 * @param totalAssets the totalAssets to set
	 */
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}
	
	/**
	 * @return the currentLiability
	 */
	public BigDecimal getCurrentLiability() {
		return currentLiability;
	}
	
	/**
	 * @param currentLiability the currentLiability to set
	 */
	public void setCurrentLiability(BigDecimal currentLiability) {
		this.currentLiability = currentLiability;
	}
	
	/**
	 * @return the longTermDebt
	 */
	public BigDecimal getLongTermDebt() {
		return longTermDebt;
	}
	
	/**
	 * @param longTermDebt the longTermDebt to set
	 */
	public void setLongTermDebt(BigDecimal longTermDebt) {
		this.longTermDebt = longTermDebt;
	}
	
	/**
	 * @return the equity
	 */
	public BigDecimal getEquity() {
		return equity;
	}
	
	/**
	 * @param equity the equity to set
	 */
	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}

}
