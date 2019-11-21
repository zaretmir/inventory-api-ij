package com.app.products.projection;

import org.springframework.beans.factory.annotation.Value;


public interface ProductSimplified {
	
	String getName();
	
	Long getProduct_id();
	
	@Value("#{@product_HangarRepository.findQtyphByProductpk(target.product_id)}")
	Integer getQty();

}
