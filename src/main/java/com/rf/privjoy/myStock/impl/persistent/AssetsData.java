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
@Table(name="assets_data")
public class AssetsData {
	
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
	
	@Column(name="cash_on_hand")
	private BigDecimal cashOnHand;
	
	@Column(name="current_assets")
	private BigDecimal currentAssets;
	
	@Column(name="total_assets")
	private BigDecimal totalAssets;
	
	@Column(name="current_liability")
	private BigDecimal currentLiability;
	
	@Column(name="long_term_debt")
	private BigDecimal longTermDebt;
	
	@Column(name="equity")
	private BigDecimal equity;
	
	public AssetsData() {
		
	}
	
	public AssetsData(String year, BigDecimal cashOnHand, BigDecimal currentAssets, BigDecimal totalAssets,
			BigDecimal currentLiability, BigDecimal longTermDebt, BigDecimal equity) {
		super();
		this.year = year;
		this.cashOnHand = cashOnHand;
		this.currentAssets = currentAssets;
		this.totalAssets = totalAssets;
		this.currentLiability = currentLiability;
		this.longTermDebt = longTermDebt;
		this.equity = equity;
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
	
	public BigDecimal getCashOnHand() {
		return cashOnHand;
	}
	
	public void setCashOnHand(BigDecimal cashOnHand) {
		this.cashOnHand = cashOnHand;
	}
	
	public BigDecimal getCurrentAssets() {
		return currentAssets;
	}
	
	public void setCurrentAssets(BigDecimal currentAssets) {
		this.currentAssets = currentAssets;
	}
	
	public BigDecimal getTotalAssets() {
		return totalAssets;
	}
	
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}
	
	public BigDecimal getCurrentLiability() {
		return currentLiability;
	}
	
	public void setCurrentLiability(BigDecimal currentLiability) {
		this.currentLiability = currentLiability;
	}
	
	public BigDecimal getLongTermDebt() {
		return longTermDebt;
	}
	
	public void setLongTermDebt(BigDecimal longTermDebt) {
		this.longTermDebt = longTermDebt;
	}
	
	public BigDecimal getEquity() {
		return equity;
	}
	
	public void setEquity(BigDecimal equity) {
		this.equity = equity;
	}

	@Override
	public String toString() {
		return "AssetsData [id=" + id + ", company=" + company + ", year=" + year + ", cashOnHand=" + cashOnHand
				+ ", currentAssets=" + currentAssets + ", totalAssets=" + totalAssets + ", currentLiability="
				+ currentLiability + ", longTermDebt=" + longTermDebt + ", equity=" + equity + "]";
	}

}
