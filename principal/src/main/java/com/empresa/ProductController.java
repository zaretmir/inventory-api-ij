package com.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.base.product.model.Product;
import com.app.products.builder.ProductBuilder;
import com.app.products.dto.ProductDto;
import com.app.products.service.ProductService;

@RestController
@RequestMapping("/api/product-management/")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("products")
	public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(required = false) String name) {
		List<Product> results = new ArrayList<Product>();

		if (name == null) {
			results = productService.getActiveProducts();
		} else {
			results = productService.getActiveProductsMatchingSearch(name);
		}

		List<ProductDto> response = results.stream()
				.map(product -> ProductBuilder.convertToDto(product))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("products/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		ProductDto responseProduct = ProductBuilder.convertToDto(product);

		return new ResponseEntity<>(responseProduct, HttpStatus.OK);
	}

	@GetMapping("products/{page}/{size}")
	public ResponseEntity<Page<ProductDto>> getProductsPage(
			@PathVariable("page") int page,
			@PathVariable("size") int size) {

		Pageable pageRequest = PageRequest.of(page, size);
		Page<Product> products = productService.getActiveProductsPage(pageRequest);

		Page<ProductDto> response = new PageImpl<ProductDto>(
				products.stream().map(p -> ProductBuilder.convertToDto(p)).collect(Collectors.toList()),
				pageRequest,
				products.getTotalElements());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("products")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto dto) {
		Product product = ProductBuilder.convertToEntity(dto);
		Product savedProduct = productService.createProduct(product);
		ProductDto responseProduct = ProductBuilder.convertToDto(savedProduct);

		return new ResponseEntity<>(responseProduct, HttpStatus.OK);
	}
	

	@PutMapping("products")
	public ResponseEntity<Product> editProduct(@Valid @RequestBody ProductDto dto) {
		Product product = ProductBuilder.convertToEntity(dto);

		return new ResponseEntity<>(productService.editProduct(product), HttpStatus.OK);
	}
	
	@PutMapping("products/delete/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id) {
		ProductDto response = ProductBuilder.convertToDto(productService.deleteProduct(id));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
