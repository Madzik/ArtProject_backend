package com.onlinestore.service;

import java.util.List;
import java.util.Optional;

import com.onlinestore.model.Item;

public interface ItemService {
	
	public List<Item> showAll();
	
	public Optional<Item> findById(Long id);
	
	public Item addItemPost(Item item);
	
	public void deleteItem(Long id);
		
}
