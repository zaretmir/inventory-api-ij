package com.app.ecommerce.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.base.auth.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "apporder")
@Getter @Setter
public class Order {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id")
	Long id;
	
	@ManyToOne(optional = false)
	@JsonIgnore
	AppUser user;	
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "total_amount")
	private double totalAmount;
	
	@Column(name= "total_items")
	private int totalItems;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems;
	
	public void addItem(OrderItem item) {
		this.orderItems.add(item);
	}
	
	public void removeItem(OrderItem item) {
		this.orderItems.remove(item);
		item.setOrder(null);
	}

}
