package com.app.ecommerce.dao;

import java.util.List;

import javax.validation.Valid;

import com.app.ecommerce.model.Order;

public interface OrderDAO {

	Order saveOrder(@Valid Order order);

	boolean existsById(Long id);

	Order getOrderById(Long id);

	List<Order> getOrdersByUserId(Long id);
	
}
