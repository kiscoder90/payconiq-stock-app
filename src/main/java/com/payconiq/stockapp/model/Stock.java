/**
 * 
 */
package com.payconiq.stockapp.model;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author sbasak
 *
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@ApiModel(value = "Stock", description = "stock details of a company")
public class Stock {
	@ApiModelProperty(value = "stock Id")
	@Setter(AccessLevel.NONE)
	Long id;
	
	@ApiModelProperty(value = "company name")
	@Setter(AccessLevel.NONE)
	String name;
	
	@ApiModelProperty(value = "current price of each stock")
	Double currentPrice;
	
	@ApiModelProperty(value = "currency")
	CurrencyMaster currency;
	
	@ApiModelProperty(value = "when the stock price was last updated")
	Timestamp lastUpdate;
}
