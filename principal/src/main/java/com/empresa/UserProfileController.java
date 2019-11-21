package com.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.base.auth.model.AppUser;
import com.app.base.user_profile.model.UserProfile;
import com.app.user_profile.builder.UserProfileBuilder;
import com.app.user_profile.dto.UserProfileDto;
import com.app.user_profile.service.UserProfileService;
import com.security.service.AppUserService;
import com.security.service.JwtUserDetailsService;

@Controller
@RequestMapping("/api/user-management/")
@CrossOrigin
public class UserProfileController {
	
	@Autowired
	UserProfileService profileService;
	
	@Autowired
	AppUserService userService;
	
	@Autowired
	JwtUserDetailsService detailsService;
	
	
	@PutMapping("users")
	public ResponseEntity<UserProfileDto> saveLoggedUserProfile(@RequestHeader(name="Authorization") String token, @RequestBody UserProfileDto profileReq) {
		
		Long loggedUserId = this.getLoggedUserId(token); // Logged user modifies only its own profile		
		
		UserProfile profile = UserProfileBuilder.convertToEntity(profileReq);
		UserProfile saved = profileService.saveProfileData(loggedUserId, profile);
		UserProfileDto profileRes = UserProfileBuilder.convertToDto(saved);
		
		return new ResponseEntity<UserProfileDto>(profileRes, HttpStatus.OK);
	}
	
	
	@PutMapping("users/{id}") //TODO: Autorizar solo al admin a este método
	public ResponseEntity<UserProfileDto> saveUserProfile(@PathVariable Long id, @RequestBody UserProfileDto profileReq) {
		
		UserProfile profile = UserProfileBuilder.convertToEntity(profileReq);
		UserProfile saved = profileService.saveProfileData(id, profile);
		UserProfileDto profileRes = UserProfileBuilder.convertToDto(saved);
		
		return new ResponseEntity<UserProfileDto>(profileRes, HttpStatus.OK);
	}
	
	
	@GetMapping("users")
	public ResponseEntity<UserProfileDto> getUserprofile(@RequestHeader(name="Authorization") String token) {
		
		Long loggedUserId = this.getLoggedUserId(token);
		
		UserProfile req = profileService.getProfileById(loggedUserId);
		UserProfileDto reqDto = UserProfileBuilder.convertToDto(req);
		
		return new ResponseEntity<UserProfileDto>(reqDto, HttpStatus.OK);		
	}
	
	
	@GetMapping("users-auth/{id}") //TODO: Autorizar solo al admin a este método
	public ResponseEntity<AppUser> getUser(@PathVariable("id") Long id) {
		AppUser req = userService.getUserById(id);
		
		return new ResponseEntity<AppUser>(req, HttpStatus.OK);		
	}
	
	
	private Long getLoggedUserId(String token) {
		String username = detailsService.getUsernameFromToken(token);
		Long loggedUserId = userService.getByUsername(username).getId();
		
		return loggedUserId;		
	}
	

}
