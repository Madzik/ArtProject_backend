package com.onlinestore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinestore.config.SecurityUtility;
import com.onlinestore.model.User;
import com.onlinestore.model.security.Role;
import com.onlinestore.model.security.UserRole;
import com.onlinestore.service.UserService;

@SpringBootApplication
public class OnlineStoreAngularApplication implements CommandLineRunner {
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreAngularApplication.class, args);
	}
	@Override
	public void run(String ... args) throws Exception {
		
		User user1 = new User();
		user1.setFirstName("David");
		user1.setLastName("Jack");
		user1.setEmail("dj@gmail.com");
		user1.setUsername("username1");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("password1"));
		
		
		Role roleUser1 = new Role();
		roleUser1.setRoleId(1);
		roleUser1.setRoleName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user1, roleUser1));
		
		userService.createUser(user1, userRoles);
		
		userRoles.clear();
		
		User user2 = new User();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setEmail("admin@gmail.com");
		user2.setUsername("Admin");
		user2.setPassword(SecurityUtility.passwordEncoder().encode("pass"));
		
		Role roleUser2 = new Role();
		roleUser2.setRoleId(2);
		roleUser2.setRoleName("ROLE_ADMIN");
		userRoles.add(new UserRole(user2, roleUser2));	
		
		userService.createUser(user2, userRoles);
		
	}
}
