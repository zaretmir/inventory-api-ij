package com.app.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.model.Order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {
	
	@NotNull(message = "Order id cannot be null")
	private Order order;
	
	@NotNull(message = "Item origin cannot be null")
	private Product_Hangar itemOrigin;;
	
	@NotNull(message = "Product id cannot be null")
	@PositiveOrZero(message = "Product id not valid")
	private int orderedQuantity;

}
