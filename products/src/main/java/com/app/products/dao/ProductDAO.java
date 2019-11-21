package com.app.products.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.base.product.model.Product;
import com.app.products.projection.ProductSimplified;

public interface ProductDAO {
	
	Product findById(Long productId);
	
	List<Product> findByIsStateTrue();
	
	Boolean existsById(Long productId);
	
	Product save(Product product);

	Product delete(Product product);

	Page<Product> getActiveProductsPage(Pageable pageRequest);

	List<Product> findByIsStateTrueAndNameContaining(String search);

	ProductSimplified getSimplifiedProductById(Long id);

	boolean existsByName(String name);

}
