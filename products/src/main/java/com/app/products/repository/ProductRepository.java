package com.app.products.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.base.product.model.Product;
import com.app.products.projection.ProductSimplified;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	//List<Product> findByHangar(Hangar hangar);
	List<Product> findByIsActiveTrue();
	
	Page<Product> findByIsActiveTrue(Pageable pageable);
	
	List<Product> findByNameContaining(String searchText);
	
	List<Product> findByIsActiveTrueAndNameContaining(String searchText);
	
	@Query(value = "SELECT p.product_id, p.name FROM product p WHERE p.product_id = :id", nativeQuery = true)
	Product getSimplifiedProductById(@Param("id") Long id);
	
	@Query(value = "SELECT p.product_id, p.name FROM product p WHERE p.product_id = :id", nativeQuery = true)
	ProductSimplified findProductByIdProjectedForLimitedData(Long id);
	
	
	
	boolean existsByName(String name);

	Product findOneById(Long productId);
	
	// https://stackoverflow.com/questions/40194614/spring-data-jpa-projection-selected-fields-from-the-db
	// https://www.baeldung.com/spring-data-rest-projections-excerpts
	// https://www.bluemagma.be/2018/10/content-negotiation-with-spring-data-jpa-projections
	// https://www.baeldung.com/spring-data-jpa-projections
	
	//
	

}
