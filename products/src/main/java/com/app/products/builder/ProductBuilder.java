package com.app.products.builder;

import com.app.base.product.model.Product;
import com.app.products.dto.ProductDto;

public class ProductBuilder {
	
	static public Product convertToEntity(ProductDto dto) {
		Product product = new Product();		
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setActive(dto.isActive());
		product.setStockEntries(dto.getStockEntries());
		
		return product;
	}
	
	static public ProductDto convertToDto(Product product) {
		ProductDto dto = new ProductDto();		
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setActive(product.isActive());
		dto.setStockEntries(product.getStockEntries());
		
		return dto;
	}

}
