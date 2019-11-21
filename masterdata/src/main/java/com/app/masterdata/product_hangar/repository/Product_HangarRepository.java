package com.app.masterdata.product_hangar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.base.product_hangar.model.Product_Hangar_Id;

@Repository
public interface Product_HangarRepository  extends JpaRepository<Product_Hangar, Long>{

	//List<Product_Hangar> findByHangarPk(Long hangarId);
	
	//List<Product_Hangar> findByProductPk(Long productId);
	
	@Query(value = "SELECT ph.qty FROM product_hangar ph WHERE ph.productpk = :id", nativeQuery = true)
	Integer findQtyphByProductPk(Long id);
	
	@Query(value = "SELECT * FROM product_hangar WHERE fk_product = :id", nativeQuery = true)
	List<Product_Hangar> findByProductPk(Long id);
	
	@Query(value = "SELECT * FROM product_hangar WHERE fk_hangar = :id", nativeQuery = true)
	List<Product_Hangar> findByHangarPk(Long id);

	@Query(value = "SELECT entry FROM product_hangar entry WHERE entry.fk_product = :productid and entry.fk_hangar = :hangarid", nativeQuery = true)
	Product_Hangar findByHangarPkAndProductPk(@Param("hangarid") Long hangarId, @Param("productid") Long productId);
	
	Product_Hangar findById(Product_Hangar_Id id);

	@Query(value = "SELECT * FROM product_hangar WHERE fk_product = :productId AND priceHistory IS NOT EMPTY", nativeQuery = true)
	List<Product_Hangar> findByProductPriceNotNull(Long productId);
	
	//StockLatestPrice findProduct_HangarProjectedForLimitedDataByHangarPkAndProductPk(Long hangarId, Long productId);
	
	//List<StockLatestPrice> findProduct_HangarProjectedForLimitedDataByProductPk(Long productId);
}
