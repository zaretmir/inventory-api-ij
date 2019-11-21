package com.app.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.dao.ItemDAO;
import com.app.ecommerce.dao.OrderDAO;
import com.app.ecommerce.exception.OrderExceptionCause;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;
import com.app.products.dao.ProductDAO;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
	@Override
	public OrderItem saveOrderItem(OrderItem item) {
		if (!orderDAO.existsById(item.getOrder().getId()))
			throw new ApplicationException(OrderExceptionCause.ORDER_NOT_FOUND);
		
		return itemDAO.saveOrderItem(item);
	}
	
	@Override
	public OrderItem getOrderItem(Order order, Product_Hangar itemOrigin) {
		OrderItem item = itemDAO.findByOrderAndItemOrigin(order, itemOrigin);
		if (item == null)
			throw new ApplicationException(OrderExceptionCause.ITEM_NOT_FOUND);
		return item;
	}
	
	@Override
	public OrderItem updateOrderItem(OrderItem updatedItem) {
		OrderItem item = itemDAO.findByOrderAndItemOrigin(updatedItem.getOrder(), updatedItem.getItemOrigin());
		
		if (item == null)
			throw new ApplicationException(OrderExceptionCause.ITEM_NOT_FOUND);
		item.setOrderedQuantity(updatedItem.getOrderedQuantity());
		
		return itemDAO.saveOrderItem(item);
	}

}
