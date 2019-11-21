
package com.empresa;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.base.product_hangar.model.Product_HangarDto;
import com.app.base.product_hangar.model.StockViews;
import com.app.hangars.service.HangarService;
import com.app.masterdata.price.repository.PriceRepository;
import com.app.masterdata.product_hangar.builder.Product_HangarBuilder;
import com.app.masterdata.product_hangar.service.Product_HangarService;
import com.app.products.projection.ProductSimplified;
import com.app.products.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("api/stock-management")
@CrossOrigin
public class Product_HangarController {

	@Autowired
	private Product_HangarService product_HangarService;
	
	@Autowired
	private HangarService hangarService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PriceRepository priceRepository;

	@PostMapping(value = "/entries", produces = "application/json; charset=UTF-8")
	public ResponseEntity<Product_Hangar> mapProductToHangar(@Valid @RequestBody Product_HangarDto entryReq) {

		Product_Hangar entry = new Product_Hangar(
				hangarService.getHangarById(entryReq.getHangarpk()),
				productService.getProductById(entryReq.getProductpk())
				);
		entry.setQuantity(entryReq.getQuantity());

		return new ResponseEntity<Product_Hangar>(product_HangarService.save(entry), HttpStatus.OK);
	}
	
	@PutMapping(value = "/entries")
	public ResponseEntity<Product_Hangar> updateStockEntry(@Valid @RequestBody Product_HangarDto updateReq) {
		Product_Hangar entry = Product_HangarBuilder.convertToEntity(updateReq);
		
		return new ResponseEntity<Product_Hangar>(product_HangarService.updateStockEntry(entry), HttpStatus.OK);
	}
	
	

	//@JsonView(StockViews.WithProductInfo.class)
	@GetMapping(value = "/entries/hangar/{hangar-id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<List<Product_Hangar>> getProductsInHangar(@PathVariable("hangar-id") Long id,
			@RequestParam(defaultValue = "false") Boolean details) {

		List<Product_Hangar> products = product_HangarService.getStockByHangar(id);

		/*if (details) {
			List<ProductSimplified> productsExcerpt = product_HangarService.getProductsExcerpt(products);
			return new ResponseEntity<List<ProductSimplified>>(productsExcerpt, HttpStatus.OK);
		}*/

		return new ResponseEntity<List<Product_Hangar>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/entries/product/{product-id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> getProductStockEntries(@PathVariable("product-id") Long id) {

		List<Product_Hangar> products = product_HangarService.getStockByProduct(id);

		return new ResponseEntity<List<Product_Hangar>>(products, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/entries/product-excerpt/{product-id}", produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> getProductGlobalStock(@PathVariable("product-id") Long id,
			@RequestParam(defaultValue = "false") Boolean details) {

		List<Product_Hangar> products = product_HangarService.getStockByProduct(id);
		if (details) {
			List<ProductSimplified> productsExcerpt = product_HangarService.getProductsExcerpt(products);
			return new ResponseEntity<List<ProductSimplified>>(productsExcerpt, HttpStatus.OK);
		}

		return new ResponseEntity<List<Product_Hangar>>(products, HttpStatus.OK);
	}
	
	@GetMapping(value = "/entries/with-price/product/{product-id}")
	public ResponseEntity<List<Product_Hangar>> getStockEntryWithLatestPrice(@PathVariable(name = "product-id") Long productId) {
		
		List<Product_Hangar> stockEntries = product_HangarService.getStockWithPriceByProduct(productId);
		return new ResponseEntity<List<Product_Hangar>>(stockEntries, HttpStatus.OK);		
	}
	
	/*
	@GetMapping(value = "/entries/with-price/product/{product-id}")
	public ResponseEntity<List<StockLatestPrice>> getStockEntryWithLatestPrice(@PathVariable(name = "product-id") Long productId) {
		
		List<StockLatestPrice> stock = product_HangarService.getStockEntriesProjected(productId);
		return new ResponseEntity<List<StockLatestPrice>>(stock, HttpStatus.OK);		
	}*/
	
	
}
