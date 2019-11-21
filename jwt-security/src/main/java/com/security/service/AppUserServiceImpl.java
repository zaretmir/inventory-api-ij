package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.auth.model.AppUser;
import com.app.base.exception.ApplicationException;
import com.security.dao.AppUserDAO;
import com.security.exception.SecurityExceptionCause;

@Service
public class AppUserServiceImpl implements AppUserService{
	
	@Autowired
	AppUserDAO userDAO;
	
	public boolean existsUsername(String username) {
		return userDAO.existsByUsername(username);
	}
	
	
	public boolean isUser(Long id) {
		return userDAO.existsById(id);
	}
	
	
	public AppUser getUserById(Long id) {
		AppUser user = userDAO.getUserById(id);
		if (user == null)
			throw new ApplicationException(SecurityExceptionCause.NOT_FOUND);
		
		return user;
	}
	
	
	public AppUser getByUsername(String username) {
		AppUser user = userDAO.findByUsername(username);
		if (user == null)
			throw new ApplicationException(SecurityExceptionCause.NOT_FOUND);
		
		return user;
	}
	

}
