package com.rf.privjoy.myStock.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rf.privjoy.myStock.dto.RecordDTO;

public interface RecordExtService {
	
	@RequestMapping(path = "/{recordId}", method = RequestMethod.GET)
	public RecordDTO getRecord(@PathVariable("recordId") Long recordId);
	
	/**
	 * Get a list of dtos for all records
	 * @return list of record dtos
	 */
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<RecordDTO> getAllRecords();

}
