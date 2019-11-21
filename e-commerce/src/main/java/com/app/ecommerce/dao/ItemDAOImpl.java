package com.app.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;
import com.app.ecommerce.repository.ItemRepository;

@Component
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public OrderItem saveOrderItem(OrderItem item) {
		return itemRepository.saveAndFlush(item);
	}
	
	@Override
	public OrderItem findByOrderAndItemOrigin(Order order, Product_Hangar itemOrigin) {
		return itemRepository.findByOrderAndItemOrigin(order, itemOrigin);
	}
}
