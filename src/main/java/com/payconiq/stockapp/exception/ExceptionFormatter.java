/**
 * 
 */
package com.payconiq.stockapp.exception;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sbasak
 *
 */

//A class to format business exception to Http error code
@Component
@Scope("singleton")
@Slf4j
public class ExceptionFormatter {

	public StockAppException errorFormatter(@NonNull String message, Exception cause) {
		

		// log the exception stack
		log.error(message);
		if (null != cause) {
			log.error(ExceptionUtils.getStackTrace(cause));
		}
		
		
		StockAppException ex = new StockAppException(message);
		
		if(cause instanceof StockAppException) {
			if (StringUtils.startsWith(message, "no_stock_found")) {
				ex.setExceptionEnum(StockAppExceptionEnum.RESOURCE_NOT_FOUND);
			} else if (StringUtils.startsWith(message, "company_already_listed")) {
				ex.setExceptionEnum(StockAppExceptionEnum.NOT_ACCEPTABLE);
			} else {
				ex.setExceptionEnum(StockAppExceptionEnum.GENERIC_EXCEPTION);
			}
		}
		

		

		return ex;
	}
}
