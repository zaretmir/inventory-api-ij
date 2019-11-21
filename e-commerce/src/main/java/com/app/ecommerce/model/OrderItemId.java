package com.app.ecommerce.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.app.base.product_hangar.model.Product_Hangar_Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
public class OrderItemId implements Serializable {
	
	private static final long serialVersionUID = -4351475789696540461L;
	
	private Long order;
	private Product_Hangar_Id itemOrigin;

	public OrderItemId(Product_Hangar_Id itemOrigin, Long order) {
		super();
		this.itemOrigin = itemOrigin;
		this.order = order;
	}
	
	public OrderItemId() { }

}
