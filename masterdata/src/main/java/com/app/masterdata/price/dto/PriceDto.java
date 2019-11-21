package com.app.masterdata.price.dto;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import com.app.base.product_hangar.model.Product_Hangar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PriceDto {
	
	private long price_id;
	
	private Product_Hangar stockEntry;
	
	@PositiveOrZero(message = "Price cannot be negative")
	private double price;
	
	@PastOrPresent(message = "Date updated is not valid")
	private Long dateUpdated;

}
