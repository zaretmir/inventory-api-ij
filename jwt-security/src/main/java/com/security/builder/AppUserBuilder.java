package com.security.builder;

import com.app.base.auth.model.AppUser;
import com.security.dto.AppUserDTO;

public class AppUserBuilder {
	
		
	
	static public AppUser convertToEntity(AppUserDTO dto) {
		AppUser user = new AppUser();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRoles(dto.getRoles());
		user.setUserProfile(dto.getUserProfile());
		return user;
	}
	

	static public AppUserDTO convertToDto(AppUser user) {
		AppUserDTO dto = new AppUserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		//dto.setPassword(user.getPassword());
		dto.setRoles(user.getRoles());
		dto.setUserProfile(user.getUserProfile());
		return dto;
	}

}
