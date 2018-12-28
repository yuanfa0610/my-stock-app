package com.rf.privjoy.myStock.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.SectorDTO;

public interface SectorExtService {
	
	/**
	 * Get the dto of sector with given id
	 * @param sectorId id of the sector
	 * @return dto of sector
	 */
	@RequestMapping(path = "/{sectorId}", method = RequestMethod.GET)
	public SectorDTO getSector(@PathVariable("sectorId") Long sectorId);

}
