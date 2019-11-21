package com.app.base.product_hangar.model;

import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product_HangarDto {
	
	@PositiveOrZero(message = "Hangar id not valid")
	private Long hangarpk;
	
	@PositiveOrZero(message = "Product id not valid")
	private Long productpk;
	
	@PositiveOrZero(message = "Quantity cannot be negative")
	private int quantity;


}
