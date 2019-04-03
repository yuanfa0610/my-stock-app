package com.rf.privjoy.myStock.dto;

import java.math.BigDecimal;

public class RevenueDataDTO {

	private Long revenueDataId;
	private Long companyId;
	private String year;
	private BigDecimal revenue;
	private BigDecimal cost;
	private BigDecimal grossProfit;
	private BigDecimal netIncome;
	private BigDecimal basicEps;
	private BigDecimal dividendPayout;

	public RevenueDataDTO() {
		
	}

	/**
	 * @return the revenueDataId
	 */
	public Long getRevenueDataId() {
		return revenueDataId;
	}

	/**
	 * @param revenueDataId the revenueDataId to set
	 */
	public void setRevenueDataId(Long revenueDataId) {
		this.revenueDataId = revenueDataId;
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
	 * @return the revenue
	 */
	public BigDecimal getRevenue() {
		return revenue;
	}

	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the grossProfit
	 */
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	/**
	 * @param grossProfit the grossProfit to set
	 */
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	/**
	 * @return the netIncome
	 */
	public BigDecimal getNetIncome() {
		return netIncome;
	}

	/**
	 * @param netIncome the netIncome to set
	 */
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	/**
	 * @return the basicEps
	 */
	public BigDecimal getBasicEps() {
		return basicEps;
	}

	/**
	 * @param basicEps the basicEps to set
	 */
	public void setBasicEps(BigDecimal basicEps) {
		this.basicEps = basicEps;
	}
	
	/**
	 * @return the dividendPayout
	 */
	public BigDecimal getDividendPayout() {
		return dividendPayout;
	}

	/**
	 * @param dividendPayout the dividendPayout to set
	 */
	public void setDividendPayout(BigDecimal dividendPayout) {
		this.dividendPayout = dividendPayout;
	}
	
}
