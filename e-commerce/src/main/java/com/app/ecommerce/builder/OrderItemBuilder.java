package com.app.ecommerce.builder;

import com.app.ecommerce.dto.OrderItemDto;
import com.app.ecommerce.model.OrderItem;

public class OrderItemBuilder {
	
	static public OrderItem convertToEntity(OrderItemDto dto) {
		OrderItem entity = new OrderItem();
		entity.setOrder(dto.getOrder());
		entity.setItemOrigin(dto.getItemOrigin());
		entity.setOrderedQuantity(dto.getOrderedQuantity());
		
		return entity;		
	}
	
	static public OrderItemDto convertToDto(OrderItem entity) {
		OrderItemDto dto = new OrderItemDto();
		dto.setOrder(entity.getOrder());
		dto.setItemOrigin(entity.getItemOrigin());
		dto.setOrderedQuantity(entity.getOrderedQuantity());
		
		return dto;		
	}

}
