package com.onlinestore.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.config.SecurityConfig;
import com.onlinestore.config.SecurityUtility;
import com.onlinestore.model.User;
import com.onlinestore.model.security.Role;
import com.onlinestore.model.security.UserRole;
import com.onlinestore.service.UserService;
import com.onlinestore.service.implementation.UserServiceImpl;
import com.onlinestore.utils.MailConstructor;

@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public ResponseEntity addUserPost(HttpServletRequest request, @RequestBody HashMap<String, String> map) 
			throws Exception{
		
		String username = map.get("username");
		String email = map.get("email");
		
		if(null != userService.findByUsername(username)) {
			return new ResponseEntity("This username already exists", HttpStatus.BAD_REQUEST);
		}
		if(null != userService.findByEmail(email)) {
			return new ResponseEntity("This e-mail already exists", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		
		String password = SecurityUtility.generateRandomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		
		user.setPassword(encryptedPassword);
		
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("USER");
		Set<UserRole> roles = new HashSet<>();
		roles.add(new UserRole(user, role));
		
		userService.createUser(user, roles);
		
		SimpleMailMessage mail = mailConstructor.constructNewUserEmail(user, password);
		mailSender.send(mail);
		
		return new ResponseEntity("New user created successfully!", HttpStatus.OK);	
	}
	
	@RequestMapping(value="/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity sendNewPassword(HttpServletRequest request, @RequestBody HashMap<String, String> map) 
			throws Exception {
		
		String username = map.get("email");
		User user = userService.findByEmail(username);
		
		if(user == null) {
			return new ResponseEntity("User not found.", HttpStatus.BAD_REQUEST);
		}
		else {
			String password = SecurityUtility.generateRandomPassword();
			String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
			
			user.setPassword(encryptedPassword);
			userService.save(user);
			
			SimpleMailMessage message = mailConstructor.constructNewUserEmail(user, password);
			return new ResponseEntity("New password created successfully.", HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/updateUserDetails", method = RequestMethod.POST)
	public ResponseEntity updateUserPost(HttpServletRequest http, @RequestBody HashMap<String, Object> map) 
		throws Exception {
		
		int id = (int) map.get("id");
		String email = (String) map.get("email");
		String newPassword = (String) map.get("newPassword");
		String currentPassword = (String) map.get("currentPassword");
		String username = (String) map.get("username");
		String firstName = (String) map.get("firstName");
		String lastName = (String) map.get("lastName");
		String phone = (String) map.get("phone");

		Optional<User> currentUser = userService.findById(Long.valueOf(id));
		
		if(currentUser == null) {
			throw new Exception("Username not found.");
		}
		if(userService.findByUsername(username) != null) {
			if(userService.findByUsername(username).getId() != currentUser.get().getId()) {
			return new ResponseEntity("Username not found", HttpStatus.BAD_REQUEST);
			}
		}
		SecurityConfig securityConfig = new SecurityConfig();
		
		if(newPassword != null && !newPassword.equals("") && !newPassword.isEmpty()) {
			PasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.get().getPassword();
			
			if(currentPassword.equals(dbPassword)) {
				currentUser.get().setPassword(passwordEncoder.encode(newPassword));
			}
			
			
		}
		
		
		// need to be completed
		return new ResponseEntity("User details have been updated successfully.", HttpStatus.OK);
		
		
		
		
	}

}
