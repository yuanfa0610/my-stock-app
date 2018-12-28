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
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="sector_id")
	private Sector sector;
	
	@Column(name="year_of_founded")
	private String yearOfFounded;
	
	@Column(name="year_of_ipo")
	private String yearOfIpo;
	
	@Column(name="link")
	private String link;
	
	@OneToMany(fetch=FetchType.EAGER,
			mappedBy="company",
			cascade=CascadeType.ALL)
	private Set<Stock> stocks;
	
	@OneToMany(fetch=FetchType.EAGER,
			mappedBy="company",
			cascade=CascadeType.ALL)
	private Set<RevenueData> revenueDataCollection;
	
	@OneToMany(fetch=FetchType.EAGER,
			mappedBy="company",
			cascade=CascadeType.ALL)
	private Set<AssetsData> assetsDataCollection;
	
	public Company() {
		
	}
	
	public Company(String name) {
		super();
		this.name = name;
	}
	
	public Company(String name, String yearOfFounded, String yearOfIpo, String link) {
		super();
		this.name = name;
		this.yearOfFounded = yearOfFounded;
		this.yearOfIpo = yearOfIpo;
		this.link = link;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Sector getSector() {
		return sector;
	}
	
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
	public String getYearOfFounded() {
		return yearOfFounded;
	}
	
	public void setYearOfFounded(String yearOfFounded) {
		this.yearOfFounded = yearOfFounded;
	}
	
	public String getYearOfIpo() {
		return yearOfIpo;
	}
	
	public void setYearOfIpo(String yearOfIpo) {
		this.yearOfIpo = yearOfIpo;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Set<Stock> getStocks() {
		return stocks;
	}
	
	public Set<RevenueData> getRevenueDataCollection() {
		return revenueDataCollection;
	}
	
	public void setRevenueDataCollection(Set<RevenueData> revenueDataCollection) {
		this.revenueDataCollection = revenueDataCollection ;
	}
	
	public Set<AssetsData> getAssetsDataCollection() {
		return assetsDataCollection;
	}
	
	public void setAssetsDataCollection(Set<AssetsData> assetsDataCollection) {
		this.assetsDataCollection = assetsDataCollection;
	}
	
	/**
	 * Add a new stock to company
	 * @param stock
	 */
	public void addStock(Stock stock) {
		
		if (stocks == null) {
			stocks = new HashSet<Stock>();
		}
		
		stocks.add(stock);
		stock.setCompany(this);
	}
	
	/**
	 * Add new revenue data to company
	 * @param revenueData
	 */
	public void addRevenueData(RevenueData revenueData) {
		
		if (revenueDataCollection == null) {
			revenueDataCollection = new HashSet<RevenueData>();
		}
		
		revenueDataCollection.add(revenueData);
		revenueData.setCompany(this);
	}
	
	/**
	 * Add new assets data to company
	 * @param assetsData
	 */
	public void addAssetsData(AssetsData assetsData) {
		
		if (assetsDataCollection == null) {
			assetsDataCollection = new HashSet<AssetsData>();
		}
		
		assetsDataCollection.add(assetsData);
		assetsData.setCompany(this);
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", sector=" + sector + ", yearOfFounded=" + yearOfFounded
				+ ", yearOfIpo=" + yearOfIpo + ", link=" + link + "]";
	}

}
