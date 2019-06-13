package com.onlinestore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.onlinestore.model.User;

@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		
		String message = "\nPlease use the following credentials to log in and edit your personal information"
				+ "including your password"
				+ "\nUsername: " + user.getUsername()
				+ "\nPassword: " + password;
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("registration.email"));
		email.setSubject("ArtStore - registration");
		email.setText(message);
		return email;
	}
	

}
