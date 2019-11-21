package com.app.user_profile.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.base.user_profile.model.UserProfile;
import com.app.user_profile.repository.UserProfileRepository;
import com.security.dao.AppUserDAO;

@Component
public class UserProfileDAOImpl implements UserProfileDAO {
	
	@Autowired
	UserProfileRepository profileRepository;
	
	@Autowired
	AppUserDAO userDAO;
	
	@Override
	public UserProfile getProfileById(Long id) {
		UserProfile profile = profileRepository.getOne(id);
		return profile;
	}
	
	@Override
	public UserProfile saveUserProfile(UserProfile profile) {
		return profileRepository.saveAndFlush(profile);
	}

	@Override
	public boolean existsProfile(Long id) {
		return profileRepository.existsById(id);
	}

}
