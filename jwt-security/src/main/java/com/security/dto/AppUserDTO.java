package com.security.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.base.auth.model.Role;
import com.app.base.user_profile.model.UserProfile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppUserDTO {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	private UserProfile userProfile;
	
}
