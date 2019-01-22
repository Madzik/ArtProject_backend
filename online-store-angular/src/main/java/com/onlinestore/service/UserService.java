package com.onlinestore.service;

import java.util.Set;

import com.onlinestore.model.User;
import com.onlinestore.model.security.UserRole;

public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles);

}
