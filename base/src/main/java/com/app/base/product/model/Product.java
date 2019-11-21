package com.app.base.product.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.app.base.product_hangar.model.Product_Hangar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product", schema="db_inventory")
@Getter @Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private long id;
	
	@Column(name = "name")
	@NotBlank(message = "Product name cannot be empty")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	
	@OneToMany(
			mappedBy = "product",
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product_Hangar> stockEntries;

	public Product() { }
	
	public Product(String name) {
		this.name = name;
	}
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Product(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Product(Long id, String name, String description, boolean isActive) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
	}
	
	

}
