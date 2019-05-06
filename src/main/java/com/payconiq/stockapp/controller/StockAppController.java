/**
 * 
 */
package com.payconiq.stockapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.stockapp.IStockOperations;
import com.payconiq.stockapp.exception.ExceptionFormatter;
import com.payconiq.stockapp.exception.StockAppException;
import com.payconiq.stockapp.impl.StockOperationsImpl;
import com.payconiq.stockapp.model.CurrencyMaster;
import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.spring.StockAppSpringContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sbasak
 *
 */

//Controller for Stock App

@Slf4j
@RestController
@Api(value = "STOCK", tags = { "STOCK" }, description = "APIs related to stock listing")
@RequestMapping("/payconiq/stockapp/api/")
public class StockAppController {

	@Autowired
	private IStockOperations stockOps;

	@Autowired
	private ExceptionFormatter exFormatter;

	@ApiOperation(value = "LIST STOCKS", tags = { "STOCK" }, nickname = "list_stocks")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(method = RequestMethod.GET, value = "/stocks")
	@ResponseBody
	public List<Stock> listStocks() throws StockAppException {
		try {
			log.debug("list_stock_invoked");

			stockOps = this.getBean();// bean creation done
			List<Stock> stockList = stockOps.listStocks();// get stock list

			log.debug("list_stocks_returned");

			return stockList;
		} catch (Exception e) {
			throw exFormatter.errorFormatter(e.getMessage(), e);
		}
	}

	@ApiOperation(value = "GET AN EXISTING STOCK FROM THE LIST", tags = { "STOCK" }, nickname = "get_stock")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Stock.class),
			@ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(method = RequestMethod.GET, value = "/stocks/1")
	@ResponseBody
	public Stock getStockByName(
			@ApiParam(value = "name of the stock", required = true) @RequestParam("name") final String name)
			throws StockAppException {
		try {
			log.debug("get_stock_invoked");

			stockOps = this.getBean();// bean creation done
			Stock stock = stockOps.getStock(name);// get stock details

			log.debug("get_stock_returned");

			return stock;
		} catch (Exception e) {
			throw exFormatter.errorFormatter(e.getMessage(), e);
		}
	}

	@ApiOperation(value = "UPDATE AN EXISTING STOCK", tags = { "STOCK" }, nickname = "update_stock")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Stock.class),
			@ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(method = RequestMethod.PUT, value = "/stocks/1")
	@ResponseBody
	public Stock updateStockByName(
			@ApiParam(value = "name of the stock", required = true) @RequestParam("name") final String name,
			@RequestParam("current_price") final Double current_price) throws StockAppException {
		try {
			log.debug("update_stock_invoked");

			stockOps = this.getBean();// bean creation done
			Stock stock = stockOps.updateStock(name, current_price);// update stock details

			log.debug("update_stock_returned");

			return stock;
		} catch (Exception e) {
			throw exFormatter.errorFormatter(e.getMessage(), e);
		}
	}

	@ApiOperation(value = "ADD A NEW STOCK", tags = { "STOCK" }, nickname = "update_stock")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Stock.class),
			@ApiResponse(code = 400, message = "Bad request") })
	@RequestMapping(method = RequestMethod.POST, value = "/stocks")
	@ResponseBody
	public Stock addStock(
			@ApiParam(value = "name of the stock", required = true) @RequestParam("name") final String name,
			@ApiParam(value = "current price of the stock", required = true) @RequestParam("current_price") final Double current_price,
			@ApiParam(value = "currency (currently supporting: USD,INR,GBP,EUR; If not provided, USD will be considered as currency", required = false) @RequestParam("currency") final Optional<String> currency)
			throws StockAppException {
		try {
			log.debug("add_stock_invoked");

			stockOps = this.getBean();// bean creation done
			Stock stock = null;
			if (validateCuurency(currency)) {
				stock = stockOps.addStock(name, current_price, CurrencyMaster.valueOf(currency.get()));
			} else {
				// by default accepting USD
				stock = stockOps.addStock(name, current_price, CurrencyMaster.USD);
			}

			log.debug("add_stock_returned");

			return stock;
		} catch (Exception e) {
			throw exFormatter.errorFormatter(e.getMessage(), e);
		}
	}

	private IStockOperations getBean() {
		return StockAppSpringContext.getBean(StockOperationsImpl.class);
	}

	private boolean validateCuurency(Optional<String> currency) {
		boolean isCurrencyInList = false;

		if (currency.isPresent()) {
			if (null != CurrencyMaster.valueOf(currency.get())) {
				isCurrencyInList = true;
			}
		}

		return isCurrencyInList;
	}
}
