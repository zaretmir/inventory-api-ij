package com.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.auth.model.AppUser;
import com.security.repository.UserRepository;

@Component
public class AppUserDAOImpl implements AppUserDAO {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public AppUser findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public AppUser save(AppUser user) {
		return userRepo.save(user);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepo.existsById(id);
	}

	@Override
	public AppUser getUserById(Long id) {
		return userRepo.getOne(id);
	}

}
