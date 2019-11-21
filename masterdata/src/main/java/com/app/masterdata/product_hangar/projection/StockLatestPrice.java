package com.app.masterdata.product_hangar.projection;

import org.springframework.beans.factory.annotation.Value;

import com.app.base.price.model.Price;


public interface StockLatestPrice {
	
	Long getHangarPk();
	
	Long getProductPk();
	
	Long getQuantity();
	
	@Value("#{@priceRepository.findTopByStockEntryOrderByDateUpdatedDesc(target)}")
	Price getLatestPrice();
}
