package com.rf.privjoy.myStock.dto;

public class SectorDTO {
	
	private Long sectorId;
	private String name;
	
	public SectorDTO() {
		
	}

	/**
	 * @return the id
	 */
	public Long getSectorId() {
		return sectorId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long sectorId) {
		this.sectorId = sectorId;
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
	
}
