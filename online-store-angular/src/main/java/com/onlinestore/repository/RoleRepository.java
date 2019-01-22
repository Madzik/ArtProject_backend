package com.onlinestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlinestore.model.User;
import com.onlinestore.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
}

