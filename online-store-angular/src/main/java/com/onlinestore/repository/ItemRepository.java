package com.onlinestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.onlinestore.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	
	 
}
