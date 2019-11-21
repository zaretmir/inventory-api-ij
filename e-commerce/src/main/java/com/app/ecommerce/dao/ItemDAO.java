package com.app.ecommerce.dao;

import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;

public interface ItemDAO {

	OrderItem saveOrderItem(OrderItem productOrd);

	OrderItem findByOrderAndItemOrigin(Order order, Product_Hangar itemOrigin);

	//OrderItem findByOrderPkAndProductPk(Long orderId, Long productId);
	
}
