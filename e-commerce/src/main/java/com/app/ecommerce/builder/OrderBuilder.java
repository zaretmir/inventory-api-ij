package com.app.ecommerce.builder;

import com.app.ecommerce.dto.OrderDto;
import com.app.ecommerce.model.Order;

public class OrderBuilder {
	
	static public OrderDto convertToDto(Order order) {
		OrderDto dto = new OrderDto();
		
		dto.setId(order.getId());
		dto.setUser(order.getUser());
		dto.setDate(order.getDate());
		dto.setOrderItems(order.getOrderItems());
		dto.setTotalItems(order.getTotalItems());
		dto.setTotalAmount(order.getTotalAmount());
		
		return dto;		
	}
	
	static public Order convertToEntity(OrderDto dto) {
		Order entity = new Order();
		
		entity.setId(dto.getId());
		entity.setUser(dto.getUser());
		entity.setDate(dto.getDate());
		entity.setOrderItems(dto.getOrderItems());
		entity.setTotalItems(dto.getTotalItems());
		entity.setTotalAmount(dto.getTotalAmount());
		
		return entity;		
	}

}
