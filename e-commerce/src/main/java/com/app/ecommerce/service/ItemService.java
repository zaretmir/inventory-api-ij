package com.app.ecommerce.service;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;

public interface ItemService {

	OrderItem updateOrderItem(OrderItem updatedItem);

	OrderItem saveOrderItem(OrderItem item);

	OrderItem getOrderItem(Order order, Product_Hangar itemOrigin);

}
