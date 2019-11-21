package com.app.ecommerce.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.product_hangar.model.Product_Hangar;
import com.app.ecommerce.dao.OrderDAO;
import com.app.ecommerce.exception.OrderExceptionCause;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;
import com.app.masterdata.price.service.PriceService;
import com.app.masterdata.product_hangar.service.Product_HangarService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	Product_HangarService stockService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	PriceService priceService;

	@Override
	public Order createOrder(@Valid Order orderReq) {
		return orderDAO.saveOrder(orderReq);
	}
	
	
	@Override
	public Order getOrderById(Long id) {
		Order order = orderDAO.getOrderById(id);
		if (orderDAO.getOrderById(id) == null)
			throw new ApplicationException(OrderExceptionCause.ORDER_NOT_FOUND);
		
		return order;
	}
	
	@Override
	public List<Order> getAllOrdersByUserId(Long id) {
		return orderDAO.getOrdersByUserId(id);
	}
	
	@Override
	public Order addItem(OrderItem requestedItem) { // TODO, Refactor for atomicity /!\
		Order origin = this.getOrderById(requestedItem.getOrder().getId());
		Order order = getOrderCopy(origin);
		
		Product_Hangar itemOrigin = stockService.getStockEntry(
				requestedItem.getItemOrigin().getId().getHangarPk(),
				requestedItem.getItemOrigin().getId().getProductPk());
		
		if (itemOrigin.getQuantity() < requestedItem.getOrderedQuantity())
			throw new ApplicationException(OrderExceptionCause.NOT_ENOUGH_STOCK);
		
		requestedItem.setItemOrigin(itemOrigin);
		
		OrderItem savedItem  = updateItem(order, requestedItem);
		
		try {
			updateOrderTotals(order);
			updateStockQuantity(requestedItem.getItemOrigin(), requestedItem.getOrderedQuantity());
		} catch (ApplicationException ex) {
			if (OrderExceptionCause.ORDER_TOTALS_ERROR.getCode().equals(ex.getMessage())) {
				order.removeItem(savedItem);
				return orderDAO.saveOrder(order);
			}						
 			if (OrderExceptionCause.STOCK_UPDATE_ERROR.getCode().equals(ex.getMessage()))
				return orderDAO.saveOrder(origin);
		}	
		
		return orderDAO.saveOrder(order);
	}
	
	private OrderItem updateItem(Order order, OrderItem requestedItem) {
		try {
			return updateExistentItem(requestedItem);				
		}
		catch (ApplicationException ex) {
			if (OrderExceptionCause.ITEM_NOT_FOUND.getCode().equals(ex.getMessage()))
				return addItemAsNew(order, requestedItem);
			else
				throw ex;
		}
	}
	
	private OrderItem updateExistentItem(OrderItem requestedItem) {
		OrderItem item = itemService.getOrderItem(requestedItem.getOrder(), requestedItem.getItemOrigin());
		
		int newQuantity = item.getOrderedQuantity() + requestedItem.getOrderedQuantity();
		item.setOrderedQuantity(newQuantity);
		return itemService.updateOrderItem(item);
	}
	
	private OrderItem addItemAsNew(Order order, OrderItem requestedItem) {
		OrderItem savedItem = itemService.saveOrderItem(requestedItem);
		order.addItem(savedItem);
		
		return savedItem;
	}
	
	private void updateStockQuantity(Product_Hangar stockEntry, int quantityRequested) { // trow
		try {
			int quantityRemaining =  stockEntry.getQuantity() - quantityRequested;
			stockEntry.setQuantity(quantityRemaining);
			stockService.updateStockEntry(stockEntry);
		} catch (ApplicationException ex) {
			throw new ApplicationException(OrderExceptionCause.STOCK_UPDATE_ERROR);
		}
		
	}
	
	private Order updateOrderTotals(Order order) { // throw ex
		try {
			order = updateTotalItems(order);
			order = updateTotalAmount(order);
		} catch (ApplicationException ex) {
			throw new ApplicationException(OrderExceptionCause.ORDER_TOTALS_ERROR);
		}				
		
		return orderDAO.saveOrder(order);
	}
	
	private Order updateTotalItems(Order order) {
		int totalItems = order.getOrderItems().stream()
				.mapToInt( product -> product.getOrderedQuantity() )
				.sum();
		
		order.setTotalItems(totalItems);
		
		return order;
	}
	
	private Order updateTotalAmount(Order order) {
		double totalAmount = order.getOrderItems().stream()
				.mapToDouble(item -> {
					return priceService.getLatestPrice(item.getItemOrigin()) * item.getOrderedQuantity();
				})
				.sum();
		
		order.setTotalAmount(totalAmount);
		
		return order;
	}
	
	private Order getOrderCopy(Order origin) {
		Order copy = new Order();
		copy.setId(origin.getId());
		copy.setUser(origin.getUser());
		copy.setDate(origin.getDate());
		copy.setTotalAmount(origin.getTotalAmount());
		copy.setTotalItems(origin.getTotalItems());
		copy.setOrderItems(origin.getOrderItems());
		
		return copy;
	}
	
}
