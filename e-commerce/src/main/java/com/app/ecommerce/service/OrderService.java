package com.app.ecommerce.service;

import java.util.List;

import javax.validation.Valid;

import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;

public interface OrderService {

	Order createOrder(@Valid Order orderReq);

	Order addItem(OrderItem item);

	List<Order> getAllOrdersByUserId(Long id);

	Order getOrderById(Long orderId);

}
