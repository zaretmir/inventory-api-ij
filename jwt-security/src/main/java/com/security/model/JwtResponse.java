package com.security.model;

import java.io.Serializable;
import java.util.Set;

import com.security.dto.AppUserDTO;

import lombok.Getter;

@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -1357520910371819924L;
	
	private final String jwttoken;
	private AppUserDTO user; // names only

	public JwtResponse(String jwttoken, AppUserDTO user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}
	

}
