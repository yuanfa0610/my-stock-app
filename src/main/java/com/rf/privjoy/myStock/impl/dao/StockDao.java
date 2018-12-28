package com.rf.privjoy.myStock.impl.dao;

import com.rf.privjoy.myStock.impl.persistent.Stock;

public interface StockDao extends GeneraicDao<Stock, Long> {

	/**
	 * Get stock with given symbol
	 * @param symbol symbol of the stock to search
	 * @return Stock
	 */
	public Stock getStockBySymbol(String symbol);
	
}
