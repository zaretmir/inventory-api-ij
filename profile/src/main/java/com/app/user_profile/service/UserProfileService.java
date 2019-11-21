package com.app.user_profile.service;

import com.app.base.user_profile.model.UserProfile;

public interface UserProfileService {


	UserProfile getProfileById(Long id);

	UserProfile saveProfileData(Long userId, UserProfile profile);

	boolean existsProfile(Long id);

}
