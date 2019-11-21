package com.empresa;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.builder.OrderBuilder;
import com.app.ecommerce.builder.OrderItemBuilder;
import com.app.ecommerce.dto.OrderDto;
import com.app.ecommerce.dto.OrderItemDto;
import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.OrderItem;
import com.app.ecommerce.service.ItemService;
import com.app.ecommerce.service.OrderService;
import com.app.masterdata.product_hangar.service.Product_HangarService;

@RestController
@RequestMapping("/api/order-management")
@CrossOrigin
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private Product_HangarService stockService;
	
	@GetMapping("/orders/{order-id}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable("order-id") Long id) {
		
		Order order = orderService.getOrderById(id);
			
		return new ResponseEntity<OrderDto>(OrderBuilder.convertToDto(order), HttpStatus.OK);		
	}
	
	
	@GetMapping("users/{user-id}")
	public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable("user-id") Long id) {
		List<Order> orders = orderService.getAllOrdersByUserId(id);
		
		List<OrderDto> ordersRes = orders.stream().map(order -> OrderBuilder.convertToDto(order) ).collect(Collectors.toList());
		
		return new ResponseEntity<List<OrderDto>>(ordersRes, HttpStatus.OK);		
	}
	
	
	@PostMapping("/orders")
	public ResponseEntity<OrderDto> postOrder(@Valid @RequestBody OrderDto orderReq) {
		
		Order order = OrderBuilder.convertToEntity(orderReq);		
		
		OrderDto orderRes = OrderBuilder.convertToDto(orderService.createOrder(order));
		
		return new ResponseEntity<OrderDto>(orderRes, HttpStatus.OK);		
	}
	
	@PutMapping("/orders/add-item")
	public ResponseEntity<OrderDto> addItemToBasket(@Valid @RequestBody OrderItemDto itemReq) {
		OrderItem item = OrderItemBuilder.convertToEntity(itemReq);
		
		Order updated = orderService.addItem(item);
		
		OrderDto response = OrderBuilder.convertToDto(updated);
		
		return new ResponseEntity<OrderDto>(response, HttpStatus.OK);
	}

}
