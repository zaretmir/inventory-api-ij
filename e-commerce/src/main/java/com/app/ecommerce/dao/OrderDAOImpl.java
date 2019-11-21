package com.app.ecommerce.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.ecommerce.model.Order;
import com.app.ecommerce.repository.OrderRepository;

@Component
public class OrderDAOImpl implements OrderDAO {
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order saveOrder(@Valid Order order) {
		return orderRepository.saveAndFlush(order);
	}
	
	@Override
	public boolean existsById(Long id) {
		return orderRepository.existsById(id);
	}
	
	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findOneById(id);
	}

	@Override
	public List<Order> getOrdersByUserId(Long id) {
		return orderRepository.findAllByUserId(id);
	}
}
