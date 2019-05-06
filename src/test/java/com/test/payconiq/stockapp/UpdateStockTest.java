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
public class UpdateStockTest {

	private StockAppController controller = new StockAppController();
	private final String STOCK_NAME = "GOOGLE";

	@Test
	public void firstTest() {
		try {
			
			controller.addStock(STOCK_NAME, 100.90, Optional.of("INR"));
			
			Stock updatedStock = controller.updateStockByName(STOCK_NAME, 100.90);
			Stock getStock = controller.getStockByName(STOCK_NAME);

			Assert.assertSame(updatedStock.getCurrentPrice(), getStock.getCurrentPrice());

		} catch (Exception e) {
			fail(ExceptionUtils.getStackTrace(e));
		}
	}

	@Test
	public void secondTest() {
		try {
			controller.updateStockByName(STOCK_NAME.concat(STOCK_NAME), 100.90);

		} catch (Exception ex) {
			if (ex instanceof StockAppException) {
				Assert.assertSame(StockAppExceptionEnum.RESOURCE_NOT_FOUND, ((StockAppException) ex).getExceptionEnum());
			}
		}
	}

}
