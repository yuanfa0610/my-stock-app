package com.rf.privjoy.myStock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class MyStockTestController {

	@RequestMapping("/populateData")
	public String populateData() {
		
		MyStockTest myStockTest = new MyStockTest();
		
		myStockTest.connectViaConfigurationFile();
		
		return "populate_data";
	}
	
}
