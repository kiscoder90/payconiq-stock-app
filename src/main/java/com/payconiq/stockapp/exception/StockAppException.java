package com.payconiq.stockapp.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * A class to handle custom exceptions thrown in the Stock app
 * 
 * @author sbasak
 *
 */

@Getter
@Setter
public class StockAppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085471819335565897L;
	private StockAppExceptionEnum exceptionEnum;
	private String messageDescription;

	public StockAppException(String message) {
		super(message);
	}

	public StockAppException(String message, Exception cause) {
		super(message, cause);
	}

	public StockAppException(StockAppExceptionEnum exceptionEnum, String message) {
		this.exceptionEnum = exceptionEnum;
		this.messageDescription = message;
	}
}
