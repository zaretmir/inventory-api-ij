package com.app.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecommerce.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Order findOneById(Long id);

	List<Order> findAllByUserId(Long id);

}
