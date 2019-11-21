package com.app.masterdata.price.builder;

import com.app.base.price.model.Price;
import com.app.masterdata.price.dto.PriceDto;

public class PriceBuilder {
	
	static public PriceDto convertToDto(Price price) {
		PriceDto dto = new PriceDto();
		dto.setPrice_id(price.getPrice_id());
		dto.setPrice(price.getPrice());
		dto.setStockEntry(price.getStockEntry());
		dto.setDateUpdated(price.getDateUpdated());
		
		return dto;
	}
	
	static public Price convertToEntity(PriceDto dto) {
		Price price = new Price();
		price.setPrice_id(dto.getPrice_id());
		price.setPrice(dto.getPrice());
		price.setStockEntry(dto.getStockEntry());
		price.setDateUpdated(dto.getDateUpdated());
		
		return price;
	}

}
