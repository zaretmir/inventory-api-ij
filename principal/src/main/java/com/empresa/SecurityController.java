package com.empresa;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.base.auth.model.AppUser;
import com.app.base.auth.model.Role;
import com.security.builder.AppUserBuilder;
import com.security.dto.AppUserDTO;
import com.security.model.JwtRequest;
import com.security.model.JwtResponse;
import com.security.repository.UserRepository;
import com.security.service.AppUserService;
import com.security.service.JwtUserDetailsService;


@RestController
@CrossOrigin
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AppUserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = userDetailsService.generateToken(userDetails);
		AppUser user = userService.getByUsername(authenticationRequest.getUsername());
		AppUserDTO userDTO = AppUserBuilder.convertToDto(user);

		return ResponseEntity.ok(new JwtResponse(token, userDTO));
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AppUserDTO dto) throws Exception {
		
		AppUser user = AppUserBuilder.convertToEntity(dto);
		
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
