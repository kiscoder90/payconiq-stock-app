package com.payconiq.stockapp.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.payconiq.stockapp.IStockOperations;
import com.payconiq.stockapp.exception.StockAppException;
import com.payconiq.stockapp.model.CurrencyMaster;
import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.model.StockList;

import lombok.NonNull;

//class for various actions available for stocks
@Component
@Scope("singleton")
public class StockOperationsImpl implements IStockOperations {

	@Autowired
	private StockList stockList;
	
	private Date date = new Date();

	//get list of stocks
	@Override
	public List<Stock> listStocks() {
		return stockList.getStockByName().values().stream().parallel().collect(Collectors.toList());
	}

	// get stock by name
	public Stock getStock(@NonNull String stockName) throws StockAppException {
		Stock stock = stockList.getStockByName().get(stockName);// get stock detail from stock list

		if (null == stock) {
			throw new StockAppException("no_stock_found_with_name ".concat(stockName).concat(" : search_again"));
		}

		return stock;// get stock using stock id

	}

	// get stock by id
	public Stock getStock(@NonNull Long stockId) throws StockAppException {

		String stockName = stockList.getStockById().get(stockId);

		if (StringUtils.isBlank(stockName)) {
			throw new StockAppException(
					"no_stock_found_with_id ".concat(Long.toString(stockId)).concat(" : search_again"));
		}

		return this.getStock(stockName);
	}

	// update existing stock price, stock by name
	@Override
	public Stock updateStock(@NonNull String stockName, @NonNull Double updatedPrice) throws StockAppException {
		Stock stock = this.getStock(stockName);// get stock object

		stock.setCurrentPrice(updatedPrice);// update price
		stock.setLastUpdate(new Timestamp(date.getTime()));// update timestamp

		stockList.getStockByName().put(stockName, stock);// update the stock in stock-list

		return stock;
	}

	// update existing stock price, stock by id
	@Override
	public Stock updateStock(@NonNull Long stockId, @NonNull Double updatedPrice) throws StockAppException {
		Stock stock = this.getStock(stockId);// get stock object

		stock.setCurrentPrice(updatedPrice);// update price
		stock.setLastUpdate(new Timestamp(date.getTime()));// update timestamp

		stockList.getStockByName().put(stock.getName(), stock);// update the stock in stock-list

		return stock;
	}

	// add a new stock in the list
	@Override
	public Stock addStock(String name, Double currentPrice, CurrencyMaster currency) throws StockAppException {
		// check if the stock already exist in the list
		if(checkForExistingStock(name)) {
			throw new StockAppException("company_already_listed ".concat(name).concat(" : use_update_instead"));
		}
		
		Stock stock = new Stock(stockList.getNextStockId().incrementAndGet(), name, currentPrice, currency,new Timestamp(date.getTime()));
		stockList.getStockByName().put(name, stock);
		stockList.getStockById().put(stockList.getNextStockId().incrementAndGet(), name);
		
		return stock;
	}

	private boolean checkForExistingStock(@NonNull String stockName) {
		boolean isExistingStock = false;

		try {
			if (null != this.getStock(stockName)) {
				// stock found, alter the flag
				isExistingStock = true;
			}
		} catch (StockAppException e) {
			// do nothing
		}

		return isExistingStock;
	}

}
