package com.test.payconiq.stockapp;

import static org.junit.Assert.*;

import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.payconiq.stockapp.controller.StockAppController;
import com.payconiq.stockapp.exception.StockAppException;
import com.payconiq.stockapp.exception.StockAppExceptionEnum;
import com.payconiq.stockapp.model.Stock;
import com.payconiq.stockapp.starter.StockAppStarter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=StockAppStarter.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddStockTest {

	private StockAppController controller = new StockAppController();
	private final String STOCK_NAME = "M&M";

	@Test
	public void firstTest() {
		try {
			Stock createdStock = controller.addStock(STOCK_NAME, 100.90, Optional.of("INR"));

			Stock getStock = controller.getStockByName(STOCK_NAME);

			Assert.assertSame(createdStock.getId(), getStock.getId());

		} catch (Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}

	@Test
	public void secondTest() {
		try {
			controller.addStock(STOCK_NAME, 100.90, Optional.of("INR"));

		} catch (Exception ex) {
			if (ex instanceof StockAppException) {
				Assert.assertSame(StockAppExceptionEnum.NOT_ACCEPTABLE, ((StockAppException) ex).getExceptionEnum());
			}
		}
	}

}
