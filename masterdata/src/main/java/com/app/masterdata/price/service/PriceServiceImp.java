package com.app.masterdata.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.price.model.Price;
import com.app.base.product_hangar.model.Product_Hangar;
import com.app.masterdata.price.dao.PriceDAO;
import com.app.masterdata.price.exception.PriceExceptionCause;
import com.app.masterdata.product_hangar.service.Product_HangarService;
import com.app.products.service.ProductService;

@Service
public class PriceServiceImp implements PriceService {
	
	@Autowired
	PriceDAO priceDAO;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Product_HangarService stockService;
	
	@Override
	public Price createPriceEntry(Price price) {
		Product_Hangar stockEntry = stockService.getStockEntryById(price.getStockEntry().getId());
		
		price.setStockEntry(stockEntry);
		
		return priceDAO.createPriceEntry(price);
	}
	
	@Override
	public List<Price> getPriceHistory(Product_Hangar stockEntry) {
		List<Price> entries = priceDAO.getPriceHistory(stockEntry);
		if (entries == null || entries.isEmpty())
			throw new ApplicationException(PriceExceptionCause.NO_PRICES_SET);
		
		return entries;
	}
	
	@Override
	public double getLatestPrice(Product_Hangar stockEntry) {
		Price latestPrice = priceDAO.getLatestPrice(stockEntry);
		if (latestPrice == null)
			throw new ApplicationException(PriceExceptionCause.NO_PRICES_SET);
		
		return priceDAO.getLatestPrice(stockEntry).getPrice();
	}
	
	@Override
	public void deleteById(Long id) {
		priceDAO.deleteById(id);
	}
	
	@Override
	public void deleteByStockEntry(Product_Hangar stockEntry) {
		priceDAO.deleteByProductHangar(stockEntry);
	}
	

}
