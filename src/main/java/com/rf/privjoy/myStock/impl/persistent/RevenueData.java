package com.rf.privjoy.myStock.impl.persistent;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="revenue_data")
public class RevenueData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name="year")
	private String year;
	
	@Column(name="revenue")
	private BigDecimal revenue;
	
	@Column(name="cost")
	private BigDecimal cost;
	
	@Column(name="gross_profit")
	private BigDecimal grossProfit;
	
	@Column(name="net_income")
	private BigDecimal netIncome;
	
	@Column(name="basic_eps")
	private BigDecimal basicEps;
	
	@Column(name="dividend_payout")
	private BigDecimal dividendPayout;
	
	public RevenueData() {
		
	}
	
	public RevenueData(String year, BigDecimal revenue, BigDecimal cost, BigDecimal grossProfit, BigDecimal netIncome,
			BigDecimal basicEps, BigDecimal dividendPayout) {
		super();
		this.year = year;
		this.revenue = revenue;
		this.cost = cost;
		this.grossProfit = grossProfit;
		this.netIncome = netIncome;
		this.basicEps = basicEps;
		this.dividendPayout = dividendPayout;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public BigDecimal getRevenue() {
		return revenue;
	}
	
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	
	public BigDecimal getNetIncome() {
		return netIncome;
	}
	
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}
	
	public BigDecimal getBasicEps() {
		return basicEps;
	}
	
	public void setBasicEps(BigDecimal basicEps) {
		this.basicEps = basicEps;
	}
	
	public BigDecimal getDividendPayout() {
		return dividendPayout;
	}
	
	public void setDividendPayout(BigDecimal dividendPayout) {
		this.dividendPayout = dividendPayout;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RevenueData [id=" + id + ", company=" + company + ", year=" + year + ", revenue=" + revenue + ", cost="
				+ cost + ", grossProfit=" + grossProfit + ", netIncome=" + netIncome + ", basicEps=" + basicEps
				+ ", dividendPayout=" + dividendPayout + "]";
	}

}
