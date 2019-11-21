package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.service.AppUserService;

@RestController
@CrossOrigin
public class AppUserController {
	
	@Autowired
	AppUserService userService;
		
	@RequestMapping(value = "/validate", params= { "username" } , method = RequestMethod.GET)
	public ResponseEntity<?> isUser(@RequestParam("username") String username) {
		return ResponseEntity.ok(userService.existsUsername(username));
	}

}
