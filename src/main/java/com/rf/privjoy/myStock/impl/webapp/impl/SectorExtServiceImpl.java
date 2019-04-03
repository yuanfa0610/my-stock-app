package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.SectorExtService;
import com.rf.privjoy.myStock.dto.SectorDTO;
import com.rf.privjoy.myStock.impl.persistent.Sector;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;

@RestController
@RequestMapping("/sector")
public class SectorExtServiceImpl implements SectorExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;

	@Override
	public SectorDTO getSector(Long sectorId) {
		Sector sector = dataService.getSectorById(sectorId);
		if (sector == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sector with given ID is not found");
		}
		return conversionService.convertToDTO(sector);
	}
	
	@Override
	public List<SectorDTO> getAllSectors() {
		List<Sector> sectors = dataService.getAllSectors();
		return conversionService.convertToSectorDTOs(sectors);
	}

	/**
	 * @param dataService the dataService to set
	 */
	@Autowired
	public void setMyStockDataService(MyStockDataService dataService) {
		this.dataService = dataService;
	}
	
	/**
	 * @param conversionService the conversionService to set
	 */
	@Autowired
	public void setMyStockConversionService(MyStockConversionService conversionService) {
		this.conversionService = conversionService;
	}

}
