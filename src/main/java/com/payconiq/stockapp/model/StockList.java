/**
 * 
 */
package com.payconiq.stockapp.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author sbasak
 *
 */

//a class to maintain different representation stocks

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@Scope("singleton")
public class StockList {

	ConcurrentHashMap<Long, String> stockById = new ConcurrentHashMap<>();// search by id
	ConcurrentHashMap<String, Stock> stockByName = new ConcurrentHashMap<>();// search by name
	AtomicLong nextStockId = new AtomicLong(100000);
}
