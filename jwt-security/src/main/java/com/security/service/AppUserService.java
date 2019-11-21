package com.security.service;

import com.app.base.auth.model.AppUser;

public interface AppUserService {
	
	public boolean existsUsername(String username);
	
	public boolean isUser(Long id);
	
	public AppUser getUserById(Long id);
	
	public AppUser getByUsername(String username);
	
	

}
