package com.app.user_profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.user_profile.model.UserProfile;
import com.app.user_profile.dao.UserProfileDAO;
import com.app.user_profile.exception.ProfileExceptionCause;
import com.security.service.AppUserService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileDAO profileDAO;
	
	@Autowired
	AppUserService userService;
	
	@Override
	public UserProfile getProfileById(Long id) {
		UserProfile profile = profileDAO.getProfileById(id);
		if (profile == null)
			throw new ApplicationException(ProfileExceptionCause.NOT_FOUND);
		return profile;
	}
	
	@Override
	public UserProfile saveProfileData(Long userId, UserProfile update) {
		UserProfile profile = profileDAO.getProfileById(userId);
		if (profile == null)
			throw new ApplicationException(ProfileExceptionCause.NOT_FOUND);
		
		profile = updateProfile(profile, update);
		
		return profileDAO.saveUserProfile(profile);
	}
	
	@Override
	public boolean existsProfile(Long id) {
		return profileDAO.existsProfile(id);
	}
	
	private UserProfile updateProfile(UserProfile profile, UserProfile update) {		
		profile.setName(update.getName());
		profile.setSurname(update.getSurname());
		profile.setAddress(update.getAddress());
		profile.setPhoneNumber(update.getPhoneNumber());
		
		return profile;
	}
	

}
