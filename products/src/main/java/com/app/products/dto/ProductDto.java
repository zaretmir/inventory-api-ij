package com.app.products.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.app.base.product_hangar.model.Product_Hangar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {
	
	private long id;
	
	@NotBlank(message = "Product name cannot be empty")
	private String name;
	
	private String description;
	
	private boolean isActive;
	
	private List<Product_Hangar> stockEntries;

}
