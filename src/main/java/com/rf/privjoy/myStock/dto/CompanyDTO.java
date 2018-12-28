package com.rf.privjoy.myStock.dto;

import java.util.Set;

public class CompanyDTO {
	
	private Long companyId;
	private String name;
	private SectorDTO sector;
	private String yearOfFounded;
	private String yearOfIpo;
	private String link;
	private Set<RevenueDataDTO> revenueDataCollection;
	private Set<AssetsDataDTO> assetsDataCollection;
	
	public CompanyDTO() {
		
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the sector
	 */
	public SectorDTO getSector() {
		return sector;
	}
	
	/**
	 * @param sector the sector to set
	 */
	public void setSector(SectorDTO sector) {
		this.sector = sector;
	}
	
	/**
	 * @return the yearOfFounded
	 */
	public String getYearOfFounded() {
		return yearOfFounded;
	}
	
	/**
	 * @param yearOfFounded the yearOfFounded to set
	 */
	public void setYearOfFounded(String yearOfFounded) {
		this.yearOfFounded = yearOfFounded;
	}
	
	/**
	 * @return the yearOfIpo
	 */
	public String getYearOfIpo() {
		return yearOfIpo;
	}
	
	/**
	 * @param yearOfIpo the yearOfIpo to set
	 */
	public void setYearOfIpo(String yearOfIpo) {
		this.yearOfIpo = yearOfIpo;
	}
	
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return the revenueDataCollection
	 */
	public Set<RevenueDataDTO> getRevenueDataCollection() {
		return revenueDataCollection;
	}
	
	/**
	 * @param revenueDataCollection the revenueDataCollection to set
	 */
	public void setRevenueDataCollection(Set<RevenueDataDTO> revenueDataCollection) {
		this.revenueDataCollection = revenueDataCollection;
	}
	
	/**
	 * @return the assetsDataCollection
	 */
	public Set<AssetsDataDTO> getAssetsDataCollection() {
		return assetsDataCollection;
	}
	
	/**
	 * @param assetsDataCollection the assetsDataCollection to set
	 */
	public void setAssetsDataCollection(Set<AssetsDataDTO> assetsDataCollection) {
		this.assetsDataCollection = assetsDataCollection;
	}

}
