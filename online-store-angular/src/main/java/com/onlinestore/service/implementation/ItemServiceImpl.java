package com.onlinestore.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.model.Item;
import com.onlinestore.repository.ItemRepository;
import com.onlinestore.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository repository;
	
	public List<Item> showAll() {
		
		List<Item> list = (List<Item>)repository.findAll();
		List<Item> availableList = new ArrayList<>();
		
		for (Item item : list) {
			if(item.isAvailable()) {
				availableList.add(item);
			}
		}
		return availableList;
	}
	
	public Optional<Item> findById(Long id) {
		 return (Optional<Item>)this.repository.findById(id);
	}
	
	public Item addItemPost(Item item) {
		return this.repository.save(item);
	}
	
	public void deleteItem(Long id) {
		this.repository.deleteById(id);
	}

}
