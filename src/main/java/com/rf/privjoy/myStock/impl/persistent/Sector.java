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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sector")
public class Sector {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,
			mappedBy="sector",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
					 CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Company> companies;
	
	public Sector() {
		
	}
	
	public Sector(String name) {
		super();
		this.name = name;
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
	
	public Set<Company> getCompanies() {
		return companies;
	}
	
	/**
	 * Add a new company to sector
	 * @param company
	 */
	public void addCompany(Company company) {
		
		if (companies == null) {
			companies = new HashSet<Company>();
		}
		
		companies.add(company);
		company.setSector(this);
	}

	@Override
	public String toString() {
		return "Sector [id=" + id + ", name=" + name + "]";
	}
	
}
