package com.app.masterdata.product_hangar.dao;

import java.util.List;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.base.product_hangar.model.Product_Hangar_Id;
import com.app.masterdata.product_hangar.projection.StockLatestPrice;
import com.app.products.projection.ProductSimplified;

public interface Product_HangarDAO {

	Product_Hangar save(Product_Hangar stockEntry);

	ProductSimplified getSimplifiedProductById(Long id);

	List<Product_Hangar> getStockByProduct(Long productId);

	List<Product_Hangar> getStockByHangar(Long hangarId);

	Product_Hangar getStock(Long hangarId, Long productId);

	boolean existsByHangarpkAndProductpk(Product_Hangar updateReq);

	Product_Hangar getStockById(Product_Hangar_Id id);

	List<Product_Hangar> getStockWithPriceByProduct(Long productId);

	//List<StockLatestPrice> getStockProjectedByProduct(Long productId);

}
