package com.app.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.base.product.model.Product;

public interface ProductService {
	
	Product getProductById(Long productId);
	
	List<Product> getActiveProducts();
	
	Product editProduct(Product product);
	
	List<Product> listProductsUpperCase();
	
	Optional<Product> listProductsLongestName();
	
	List<Product> listProductsByFirstLetter(char letter);

	Product createProduct(Product product);

	Product deleteProduct(Long id);

	Page<Product> getActiveProductsPage(Pageable pageRequest);

	List<Product> getActiveProductsMatchingSearch(String search);


}
