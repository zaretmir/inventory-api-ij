package com.app.user_profile.dto;

import com.app.base.auth.model.AppUser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserProfileDto {
	
	private Long id;
	
	private AppUser user;
	
	private String name;

	private String surname;
	
	private String address;
	
	private Integer phoneNumber;

}
