package com.app.ecommerce.dto;

import java.util.Date;
import java.util.List;

import com.app.base.auth.model.AppUser;
import com.app.ecommerce.model.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto {
	
	private Long id;
	
	private AppUser user;	
	
	private Date date;
	
	private double totalAmount;
	
	private int totalItems;
	
	private List<OrderItem> orderItems;

}
