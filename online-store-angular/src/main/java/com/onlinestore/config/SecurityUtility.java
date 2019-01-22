package com.onlinestore.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	
	private static final String SALT = "bmrtty12bn";
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	@Bean
	public String generateRandomPassword() {
		
		String saltChars = "ABCDEFGHIJKLMNOPQRSTUWXYZ123456789";
		StringBuilder salt = new StringBuilder();
		Random random = new Random();
		
		while (salt.length() < 12) {
			int index = (int)random.nextFloat() * saltChars.length();
			salt.append(saltChars.charAt(index));
		}
		String pass = salt.toString();
		return pass;
	}
	

}
