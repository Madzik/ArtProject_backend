package com.onlinestore.service;

import java.util.Optional;
import java.util.Set;

import com.onlinestore.model.User;
import com.onlinestore.model.security.UserRole;
import com.onlinestore.repository.UserRepository;

public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles);
	
	public User findByUsername (String username);
	
	public User findByEmail(String email);
	
	public Optional<User> findById(Long id);
	
	public void save(User user);

}
