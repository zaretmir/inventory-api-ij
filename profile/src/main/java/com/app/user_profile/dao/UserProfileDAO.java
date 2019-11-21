package com.app.user_profile.dao;

import com.app.base.user_profile.model.UserProfile;

public interface UserProfileDAO {

	UserProfile getProfileById(Long id);

	boolean existsProfile(Long id);

	UserProfile saveUserProfile(UserProfile profile);

}
