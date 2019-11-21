package com.app.hangars.builder;

import com.app.base.hangar.model.Hangar;
import com.app.hangars.dto.HangarDto;

public class HangarBuilder {
	
	
	static public Hangar convertToEntity(HangarDto dto) {
		Hangar hangar = new Hangar();
		hangar.setId(dto.getId());
		hangar.setName(dto.getName());
		hangar.setAddress(dto.getAddress());
		hangar.setOwner(dto.getOwner());
		hangar.setOwnerEmail(dto.getOwnerEmail());
		hangar.setPhoneNumber(dto.getPhoneNumber());
		hangar.setActive(dto.isActive());
		hangar.setStockEntries(dto.getStockEntries());
		
		
		return hangar;		
	}
	
	static public HangarDto convertToDto(Hangar hangar) {
		HangarDto dto = new HangarDto();
		dto.setId(hangar.getId());
		dto.setName(hangar.getName());
		dto.setAddress(hangar.getAddress());
		dto.setOwner(hangar.getOwner());
		dto.setOwnerEmail(hangar.getOwnerEmail());
		dto.setPhoneNumber(hangar.getPhoneNumber());
		dto.setActive(hangar.isActive());
		dto.setStockEntries(hangar.getStockEntries());
		
		return dto;
	}

}
