package com.app.products.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.app.base.product.model.Product;
import com.app.products.projection.ProductSimplified;
import com.app.products.repository.ProductRepository;

@Component
public class ProductDAOImp implements ProductDAO {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product findById(Long productId) {
		return productRepository.findOneById(productId);
	}
	
	@Override
	public List<Product> findByIsStateTrue() {
		return productRepository.findByIsActiveTrue();
	}
	
	@Override
	public Boolean existsById(Long productId) {
		return productRepository.existsById(productId);
	}
	
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product delete(Product product) {
		product.setActive(false);
		return productRepository.save(product);
	}

	@Override
	public Page<Product> getActiveProductsPage(Pageable pageRequest) {
		return productRepository.findByIsActiveTrue(pageRequest);
	}

	@Override
	public List<Product> findByIsStateTrueAndNameContaining(String search) {
		return productRepository.findByIsActiveTrueAndNameContaining(search);
	}

	@Override
	public ProductSimplified getSimplifiedProductById(Long id) {
		return productRepository.findProductByIdProjectedForLimitedData(id);
	}

	@Override
	public boolean existsByName(String name) {
		return productRepository.existsByName(name);
	}
	
	

}
