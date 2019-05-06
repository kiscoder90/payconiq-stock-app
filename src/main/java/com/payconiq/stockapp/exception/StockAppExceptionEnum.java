package com.payconiq.stockapp.exception;

import org.springframework.http.HttpStatus;

public enum StockAppExceptionEnum {

	BAD_REQUEST("Malformed Request", HttpStatus.BAD_REQUEST,
			"The request is malformed. Check the message for the reason."),
	RESOURCE_NOT_FOUND("Resource not found", HttpStatus.NOT_FOUND, "The requested resource was not found."),
	NOT_ACCEPTABLE("Not Acceptable", HttpStatus.NOT_ACCEPTABLE, "The request was made with unacceptable data."),
	GENERIC_EXCEPTION("internal_error", HttpStatus.INTERNAL_SERVER_ERROR, "An internal error has occurred.");

	String errorCode;
	HttpStatus httpStatusCode;
	String description;

	StockAppExceptionEnum(String errorCode, HttpStatus httpStatusCode, String description) {
		this.errorCode = errorCode;
		this.httpStatusCode = httpStatusCode;
		this.description = description;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getDescription() {
		return description;
	}

}
