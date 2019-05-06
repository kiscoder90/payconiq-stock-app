/**
 * 
 */
package com.payconiq.stockapp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.payconiq.stockapp.exception.StockAppException;
import com.payconiq.stockapp.model.CurrencyMaster;
import com.payconiq.stockapp.model.Stock;


/**
 * @author sbasak
 *
 */

@Component
public interface IStockOperations {

	// list stocks
	public List<Stock> listStocks();

	// get stock
	public Stock getStock(String name) throws StockAppException;
	public Stock getStock(Long id) throws StockAppException;

	// update stock
	public Stock updateStock(String name, Double updatedPrice) throws StockAppException;
	public Stock updateStock(Long id, Double updatedPrice) throws StockAppException;

	// create stock
	public Stock addStock(String name, Double currentPrice, CurrencyMaster currency) throws StockAppException;
}
