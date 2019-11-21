package com.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.base.auth.model.AppUser;
import com.app.base.auth.model.Role;
import com.app.base.exception.ApplicationException;
import com.app.base.user_profile.model.UserProfile;
import com.security.config.JwtTokenUtil;
import com.security.dao.AppUserDAO;
import com.security.exception.SecurityExceptionCause;
import com.security.repository.RoleRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	AppUserDAO userDAO;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	AppUserService userService;
	
	@Autowired
    JwtTokenUtil tokenUtil;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser us = userDAO.findByUsername(username);
		
		Collection<? extends GrantedAuthority> roles = getAuthorities(us);
		
		UserDetails usDetails = new User(us.getUsername(), us.getPassword(), roles);
		
		return usDetails;
	}
	
	
	public String generateToken(UserDetails userDetails) {
		return tokenUtil.generateToken(userDetails);
	}
	
	
	public String getUsernameFromToken(String token) {
		token = token.replace("Bearer ", "");
		return tokenUtil.getUsernameFromToken(token);
	}
	
	public AppUser save(AppUser user) {
		
		if (userService.getByUsername(user.getUsername()) != null) 
			throw new ApplicationException(SecurityExceptionCause.ALREADY_EXISTS);
			
		user.setPassword(encoder.encode(user.getPassword()));
		
		UserProfile profile = new UserProfile(user);
		user.setUserProfile(profile);		
		
		assignRole_User(user);		
		AppUser savedUser = userDAO.save(user);
		
		
		return savedUser;
	}
	
	
	
	private void assignRole_User(AppUser user) {
		Role ROLE_USER = roleRepo.findByRolename("USER");
		user.getRoles().add(ROLE_USER);
	}
	
	
	private Collection<? extends GrantedAuthority> getAuthorities(AppUser user) {

		String[] roleNames = user.getRoles().stream()
				.map(role -> role.getRolename())
				.toArray(String[]::new);
		
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleNames);
		return authorities;		
    }
	

}
