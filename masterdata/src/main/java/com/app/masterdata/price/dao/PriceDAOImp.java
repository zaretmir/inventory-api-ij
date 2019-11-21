package com.app.masterdata.price.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.price.model.Price;
import com.app.base.product_hangar.model.Product_Hangar;
import com.app.masterdata.price.repository.PriceRepository;
import com.app.products.dao.ProductDAO;

@Component
public class PriceDAOImp implements PriceDAO {
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public Price createPriceEntry(Price price) {
		return priceRepository.save(price);
	}

	@Override
	public List<Price> getAllEntries() {
		return priceRepository.findAll();
	}
	
	@Override
	public Price getLatestPrice(Product_Hangar stockEntry) {
		return priceRepository.findTopByStockEntryOrderByDateUpdatedDesc(stockEntry);
	}

	@Override
	public List<Price> getPriceHistory(Product_Hangar stockEntry) {
		return priceRepository.findByStockEntryOrderByDateUpdatedDesc(stockEntry);
	}

	@Override
	public void deleteById(Long priceId) {
		priceRepository.deleteById(priceId);
	}
	
	@Override
	@Transactional
	public void deleteByProductHangar(Product_Hangar stockEntry) {
		priceRepository.deleteByStockEntry(stockEntry);
	}

}
