/**
 * 
 */
package com.payconiq.stockapp.starter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.payconiq.stockapp.model.CurrencyMaster;
import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.model.StockList;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sbasak
 *
 */

//initialize stock
@Component
@Scope("singleton")
@Slf4j
public class StockInitializer {

	@Autowired
	private StockList stockList;

	private Date date = new Date();

	public void loadDefaultStocks() {
		
		log.info("starting_stock_load");
		
		stockList.getStockByName().put("GOOGLE", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"GOOGLE", 1189.00, CurrencyMaster.USD));
		stockList.getStockById().put(stockList.getNextStockId().get(), "GOOGLE");

		stockList.getStockByName().put("VANGUARD", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"VANGUARD", 53.00, CurrencyMaster.USD));
		stockList.getStockById().put(stockList.getNextStockId().get(), "VANGUARD");

		stockList.getStockByName().put("AMAZON", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"AMAZON", 1962.46, CurrencyMaster.USD));
		stockList.getStockById().put(stockList.getNextStockId().get(), "AMAZON");

		stockList.getStockByName().put("AT&T",
				this.createNewStock(stockList.getNextStockId().incrementAndGet(), "AT&T", 30.70, CurrencyMaster.USD));
		stockList.getStockById().put(stockList.getNextStockId().get(), "AT&T");

		stockList.getStockByName().put("MARUTI SUZUKI", this.createNewStock(
				stockList.getNextStockId().incrementAndGet(), "MARUTI SUZUKI", 6578.40, CurrencyMaster.INR));
		stockList.getStockById().put(stockList.getNextStockId().get(), "MARUTI SUZUKI");

		stockList.getStockByName().put("RELAINCE", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"RELAINCE", 1470.05, CurrencyMaster.INR));
		stockList.getStockById().put(stockList.getNextStockId().get(), "RELAINCE");

		stockList.getStockByName().put("AXIS BANK", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"AXIS BANK", 875.00, CurrencyMaster.INR));
		stockList.getStockById().put(stockList.getNextStockId().get(), "AXIS BANK");

		stockList.getStockByName().put("YES BANK", this.createNewStock(stockList.getNextStockId().incrementAndGet(),
				"YES BANK", 154.00, CurrencyMaster.INR));
		stockList.getStockById().put(stockList.getNextStockId().get(), "YES BANK");
		
		log.info("completed_stock_load");
	}

	private Stock createNewStock(@NonNull Long id, @NonNull String name, @NonNull Double currentPrice,
			@NonNull CurrencyMaster currency) {
		return new Stock(id, name, currentPrice, currency, new Timestamp(date.getTime()));
	}
}
