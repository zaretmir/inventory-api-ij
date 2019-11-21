package com.app.hangars.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.base.product_hangar.model.Product_Hangar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HangarDto {
	
	private long id;
	
	@NotBlank(message = "Hangar name is required")
	private String name;

	private String address;
	
	private String owner;
	
	@Email(message = "E-mail not valid")
	private String ownerEmail;
	
	@Digits(message = "Phone number not valid", fraction = 0, integer = 11)
	private Integer phoneNumber;
	
	private boolean isActive; // isDeleted (borrado lógico)
	
	private List<Product_Hangar> stockEntries;

	public HangarDto() { }
	
	public HangarDto(String name, String address) {
		this.name = name;
		this.address = address;
	}
	

}
