package com.app.products.service;

import java.beans.FeatureDescriptor;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.product.model.Product;
import com.app.products.dao.ProductDAO;
import com.app.products.exception.ProductExceptionCause;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public Product getProductById(Long productId) {
		Product product = productDAO.findById(productId);
		if (product == null)
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND);
		
		return product;		
	}
	
	@Override
	public List<Product> getActiveProducts() {
		List<Product> products = productDAO.findByIsStateTrue();
		
		if (products == null || products.isEmpty())
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND);
		
		return products;		
	}
	
	@Override
	public Page<Product> getActiveProductsPage(Pageable pageRequest) {
		Page<Product> products = productDAO.getActiveProductsPage(pageRequest);
		
		if (products.getContent() == null || products.getContent().isEmpty())
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND_ACTIVE);
		
		return products;
	}
	
	@Override
	public List<Product> getActiveProductsMatchingSearch(String search) {
		List<Product> products = productDAO.findByIsStateTrueAndNameContaining(search);
		if (products == null || products.isEmpty())
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND_ACTIVE);
		return products;
	}
	
	@Override
	public Product createProduct(Product product) {
		if (productDAO.existsByName(product.getName()))
			throw new ApplicationException(ProductExceptionCause.NAME_DUPLICATED);
		
		return productDAO.save(product);		
	}
	
	@Override
	public Product editProduct(Product update) { // Modifies not null fields present in "update" only
		Product original = productDAO.findById(update.getId());
		if (original == null)
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND);		
		
		String[] nullPropertyNames = getNullPropertyNames(update);
		BeanUtils.copyProperties(update, original, nullPropertyNames);
		
		return productDAO.save(original);
	}
	
	@Override
	public Product deleteProduct(Long id) {
		Product product = productDAO.findById(id);
		if (product == null)
			throw new ApplicationException(ProductExceptionCause.NOT_FOUND);
		
		return productDAO.delete(product);		
	}

	@Override
	public List<Product> listProductsByFirstLetter(char letter) {
		// ¿Se podría hacer con el repo?
		List<Product> allProducts = productDAO.findByIsStateTrue();
		
		List<Product> matches = allProducts.stream()
				.filter( p -> p.getName().charAt(0) == letter )
				.collect(Collectors.toList());
		
		return matches;
	}
	
	@Override
	public List<Product> listProductsUpperCase() {
		
		List<Product> lower = productDAO.findByIsStateTrue();
		
		List<Product> upper = lower.stream().map( p -> {
			p.setName(p.getName().toUpperCase());
			return p;
		}).collect(Collectors.toList());
		
		return upper;
	}
	
	@Override
	public Optional<Product> listProductsLongestName() {
		List<Product> allProducts = productDAO.findByIsStateTrue();
		Optional<Product> longest = allProducts.stream().max(Comparator.comparingInt( a -> a.getName().length() ));
		return longest;
	}

	
	/*
	 * Returns string array containing the property names of not-null properties of a product
	 */
	public String[] getNullPropertyNames(Product product) {
		
		final BeanWrapper wrappedProduct = new BeanWrapperImpl(product);
		
		return Stream.of(wrappedProduct.getPropertyDescriptors())
				.map(FeatureDescriptor::getName)
				.filter(propertyName -> wrappedProduct.getPropertyValue(propertyName) == null)
				.toArray(String[]::new);
	}

}
