package com.onlinestore.resource;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.service.UserService;

@RestController
public class LoginResource {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/token")
	public Map<String,String> token(HttpSession session,HttpServletRequest request) {
		
		String remoteHost = request.getRemoteAddr();
		int port = request.getLocalPort();
		
		System.out.println("Remote Host: " + remoteHost);
		System.out.println("Port: " + port);
		
		return Collections.singletonMap("token", session.getId());
	}
	
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		System.out.println("check session ");
		return new ResponseEntity("Session active", HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout", method=RequestMethod.POST)
	public ResponseEntity logout(HttpSession session) {
		
		SecurityContextHolder.clearContext();
		//session.invalidate(); 
		System.out.println("context cleared");
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		
	}
	

}
