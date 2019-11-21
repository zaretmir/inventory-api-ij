package com.app.base.product_hangar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.base.hangar.model.Hangar;
import com.app.base.price.model.Price;
import com.app.base.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@IdClass(Product_Hangar_Id.class)
@Table(name="product_hangar")
public class Product_Hangar {
	
	//@JsonView(StockViews.Basic.class)
	@EmbeddedId
	Product_Hangar_Id id;
	
	@MapsId("hangarPk")
	@JoinColumn(name="fk_hangar")
	@ManyToOne
	//@JsonView(StockViews.WithHangarInfo.class)
	private Hangar hangar;
	
	@MapsId("productPk")
	@JoinColumn(name="fk_product")
	@ManyToOne
	//@JsonView(StockViews.WithProductInfo.class)
	private Product product;
	
	@Column(name="qty")
	//@JsonView(StockViews.Basic.class)
	private Integer quantity;
	
	@OneToMany(
			mappedBy = "stockEntry",
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	//@JsonView(StockViews.Basic.class)
	private List<Price> priceHistory;
	
	public Product_Hangar() {}
	
	public Product_Hangar(Hangar hangar, Product product) {
		this.hangar = hangar;
		this.product = product;
	}
	
	public void addPriceEntry(Price price) {
		priceHistory.add(price);
		price.setStockEntry(this);
	}
	
	public void removePriceEntry(Price price) {
		priceHistory.remove(price);
		price.setStockEntry(null);
	}
	
}
