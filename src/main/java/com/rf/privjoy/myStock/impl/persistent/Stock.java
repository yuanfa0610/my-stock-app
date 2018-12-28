package com.rf.privjoy.myStock.impl.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="symbol")
	private String symbol;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="company_id")
	private Company company;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="stock_specification_id")
	private StockSpecification stockSpecification;
	
	@OneToMany(fetch=FetchType.EAGER, 
			mappedBy="stock",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
					 CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Inventory> inventories;
	
	@OneToMany(fetch=FetchType.EAGER, 
			mappedBy="stock",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
					 CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Record> records;
	
	public Stock() {
		
	}
	
	public Stock(String symbol) {
		super();
		this.symbol = symbol;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public StockSpecification getStockSpecification() {
		return stockSpecification;
	}
	
	public void setStockSpecification(StockSpecification stockSpecification) {
		this.stockSpecification = stockSpecification;
	}
	
	public Set<Inventory> getInventories() {
		return inventories;
	}

	public Set<Record> getRecords() {
		return records;
	}
	
	/**
	 * Add a new inventory to stock
	 * @param inventory
	 */
	public void addInventory(Inventory inventory) {
		
		if (inventories == null) {
			inventories = new HashSet<Inventory>();
		}
		
		inventories.add(inventory);
		inventory.setStock(this);
	}
	
	/**
	 * Remove an existing inventory from stock
	 * @param inventory
	 * @return true if inventory gets successfully removed
	 */
	public Boolean removeInventory(Inventory inventory) {
		
		if (inventories == null || !inventories.contains(inventory)) {
			return false;
		}
		
		inventory.setStock(null);
		inventories.remove(inventory);
		return true;
	}
	
	/**
	 * Add a new record to stock
	 * @param record
	 */
	public void addRecord(Record record) {
		
		if (records == null) {
			records = new HashSet<Record>();
		}
		
		records.add(record);
		record.setStock(this);
	}
	
	@Override
	public String toString() {
		return "Stock [id=" + id + ", symbol=" + symbol + ", company=" + company + ", stockSpecification="
				+ stockSpecification + "]";
	}
	
}
