package com.app.base.hangar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.base.product_hangar.model.Product_Hangar;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HANGAR", schema="db_inventory")
@Getter @Setter
public class Hangar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hangar_id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "owner_email")
	private String ownerEmail;
	
	@Column(name = "phone_number")
	private Integer phoneNumber;
	
	@Column(name = "is_active")
	private boolean isActive;	
	
	@OneToMany(
			mappedBy = "hangar",
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product_Hangar> stockEntries;

}
