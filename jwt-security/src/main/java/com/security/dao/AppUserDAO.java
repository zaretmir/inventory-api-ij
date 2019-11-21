package com.security.dao;

import com.app.base.auth.model.AppUser;

public interface AppUserDAO {
	
	AppUser findByUsername(String username);
	
	AppUser save(AppUser user);
	
	Boolean existsByUsername(String username);

	boolean existsById(Long id);

	AppUser getUserById(Long id);

}
