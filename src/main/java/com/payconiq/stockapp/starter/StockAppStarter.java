package com.payconiq.stockapp.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.payconiq.stockapp.spring.StockAppSpringContext;

/**
 * 
 * @author sbasak
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.payconiq.stockapp.*" })
public class StockAppStarter {
	public static void main(String[] args) {
		SpringApplication.run(StockAppStarter.class, args);
		
		StockInitializer stockInitializer = StockAppSpringContext.getBean(StockInitializer.class);
		stockInitializer.loadDefaultStocks();
	}
}
