package com.app.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.base.product_hangar.model.Product_Hangar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item")
@Getter @Setter
@IdClass(OrderItemId.class)
public class OrderItem {

	@Id
	@ManyToOne // Required due to IdClass
	@JsonIgnore
	private Order order;
	
	@Id	
	@ManyToOne
	private Product_Hangar itemOrigin;
	
	@Column(name = "qty_ordered")
	private int orderedQuantity;
	
	public OrderItem() { }
	
	public OrderItem(Order order, Product_Hangar itemOrigin) {
		this.order = order;
		this.itemOrigin = itemOrigin;
	}
	
	public OrderItem(Order order, Product_Hangar itemOrigin, int orderedQuantity) {
		this.order = order;
		this.itemOrigin = itemOrigin;
		this.orderedQuantity = orderedQuantity;
	}
	

}
