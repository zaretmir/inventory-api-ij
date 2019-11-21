package com.empresa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.base.price.model.Price;
import com.app.base.product_hangar.model.Product_Hangar;
import com.app.base.product_hangar.model.Product_Hangar_Id;
import com.app.masterdata.price.builder.PriceBuilder;
import com.app.masterdata.price.dto.PriceDto;
import com.app.masterdata.price.service.PriceService;
import com.app.masterdata.product_hangar.service.Product_HangarService;

@RestController
@RequestMapping("/api/price-management")
@CrossOrigin
public class PriceController {
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private Product_HangarService product_HangarService;

	@PostMapping("/entries")
	public Price createPriceEntry(@RequestBody PriceDto priceReq) {
		priceReq.setDateUpdated(new Date(System.currentTimeMillis()).getTime());
		
		Price price = PriceBuilder.convertToEntity(priceReq);
		
		return priceService.createPriceEntry(price);
	}
	
	@GetMapping("/entries/{hangar-id}/{product-id}") 
	public ResponseEntity<List<Price>> getAllEntries(
			@PathVariable(name = "hangar-id") Long hangarId,
			@PathVariable(name = "product-id") Long productId) {
		
		Product_Hangar_Id id = new Product_Hangar_Id(hangarId, productId);
		
		Product_Hangar productHangar = product_HangarService.getStockEntryById(id);
		return new ResponseEntity<List<Price>>(priceService.getPriceHistory(productHangar), HttpStatus.OK);
	}
	
	/*
	
	@DeleteMapping("/entries/products/{product-id}")
	public void deleteByProductId(@PathVariable("product-id") Long id) {
		priceService.deleteByProductId(id);
	}
	
	@DeleteMapping("/entries/{entry-id}")
	public void deletePrice(@PathVariable("entry-id") Long id) {
		priceService.deleteById(id);
	}
	
	*/
	
	
	

}
