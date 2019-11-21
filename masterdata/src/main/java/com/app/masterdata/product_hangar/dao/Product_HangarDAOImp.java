package com.app.masterdata.product_hangar.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.base.product_hangar.model.Product_Hangar_Id;
import com.app.masterdata.product_hangar.repository.Product_HangarRepository;
import com.app.products.dao.ProductDAO;
import com.app.products.projection.ProductSimplified;

@Component
public class Product_HangarDAOImp implements Product_HangarDAO {
	
	@Autowired
	Product_HangarRepository product_HangarRepository;
	
	@Autowired
	ProductDAO productDAO;

	@Override
	public Product_Hangar save(Product_Hangar stockEntry) {
		return product_HangarRepository.saveAndFlush(stockEntry);
	}
	
	@Override
	public List<Product_Hangar> getStockByProduct(Long productId) {
		return product_HangarRepository.findByProductPk(productId);
	}

	@Override
	public List<Product_Hangar> getStockByHangar(Long hangarId) {
		return product_HangarRepository.findByHangarPk(hangarId);
	}
	
	@Override
	public ProductSimplified getSimplifiedProductById(Long id) {
		return productDAO.getSimplifiedProductById(id);
	}
	
	@Override
	public Product_Hangar getStock(Long hangarId, Long productId) {
		return product_HangarRepository.findByHangarPkAndProductPk(hangarId, productId);
	}
	
	
	@Override
	public Product_Hangar getStockById(Product_Hangar_Id id) {
		return product_HangarRepository.findById(id);
	}
	/*
	@Override
	public List<StockLatestPrice> getStockProjectedByProduct(Long productId) {
		return product_HangarRepository.findProduct_HangarProjectedForLimitedDataByProductPk(productId);
	}*/

	@Override
	public boolean existsByHangarpkAndProductpk(Product_Hangar updateReq) {
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("qty");			
		Example<Product_Hangar> example = Example.of(updateReq, matcher); 
		
		return product_HangarRepository.exists(example);
	}

	@Override
	public List<Product_Hangar> getStockWithPriceByProduct(Long productId) {
		return product_HangarRepository.findByProductPriceNotNull(productId);
	}

}
