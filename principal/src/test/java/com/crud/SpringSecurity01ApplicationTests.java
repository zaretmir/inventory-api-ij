package com.crud;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.base.auth.model.AppUser;
import com.app.base.auth.model.Role;
import com.empresa.ProductApplication;
import com.security.repository.RoleRepository;
import com.security.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class SpringSecurity01ApplicationTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
		
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void UserRepoTest() {
		
		// Get roles
		Role adminRole = roleRepo.findByRolename("ADMIN");
		Role userRole = roleRepo.findByRolename("USER");
		
		
		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		roles.add(userRole);
		
		// Create users		
		AppUser sami = new AppUser(
				"sami",
				encoder.encode("sami"),
				roles);
		
		AppUser kevin = new AppUser();
		kevin.setUsername("kevin");
		kevin.setPassword(encoder.encode("kevin"));
		kevin.getRoles().add(userRole);
		
		AppUser samiFetched = userRepo.save(sami);
		AppUser kevinFetched = userRepo.save(kevin);

		assertTrue(samiFetched.getUsername().equalsIgnoreCase(samiFetched.getUsername()));
		
		}

}
