package com.app.base.price.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.base.product_hangar.model.Product_Hangar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="price", schema="db_inventory")
@Getter @Setter
public class Price {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="price_id")
	private long price_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "stock_entry_hangarpk", referencedColumnName = "fk_hangar"),
		@JoinColumn(name = "stock_entry_productpk", referencedColumnName = "fk_product")})
	//@JoinColumn(name="stock_entry_id", referencedColumnName = "id")
	@JsonIgnore
	private Product_Hangar stockEntry;
	
	@Column
	@NotNull(message = "price may not be null")
	private Double price;
	
	@Column
	//@NotNull
	private Long dateUpdated;
	
	
}
