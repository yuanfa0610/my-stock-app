package com.rf.privjoy.myStock.impl.webapp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rf.privjoy.myStock.api.RecordExtService;
import com.rf.privjoy.myStock.dto.RecordDTO;
import com.rf.privjoy.myStock.impl.persistent.Record;
import com.rf.privjoy.myStock.impl.utils.MyStockConversionService;
import com.rf.privjoy.myStock.impl.utils.MyStockDataService;

@RestController
@RequestMapping("/record")
public class RecordExtServiceImpl implements RecordExtService {
	
	private MyStockDataService dataService;
	private MyStockConversionService conversionService;

	@Override
	public RecordDTO getRecord(Long recordId) {
		Record record = dataService.getRecordById(recordId);
		if (record == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record with given ID is not found");
		}
		return conversionService.convertToDTO(record);
	}
	
	@Override
	public List<RecordDTO> getAllRecords() {
		List<Record> records = dataService.getAllRecords();
		return conversionService.convertToRecordDTOs(records);
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
