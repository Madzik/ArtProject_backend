package com.onlinestore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jayway.jsonpath.internal.Path;
import com.onlinestore.model.Item;
import com.onlinestore.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Item addItemPost(@RequestBody Item item) {	
		return itemService.addItemPost(item);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Item updateItemPost(@RequestBody Item item) {	
		return itemService.addItemPost(item);
	}
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST) 
	public ResponseEntity upload(
			@RequestParam("id") Long id,
			ServletRequest request, ServletResponse response) {
		
		try {
			Optional<Item> item = itemService.findById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames(); 
			MultipartFile multipartFile = multipartRequest.getFile(iter.next());
			String fileName = id + ".png";
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream buffer = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/images/item/" + fileName)));
			buffer.write(bytes);
			buffer.close();
			
			return new ResponseEntity("Uploaded successfully!", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST) 
	public ResponseEntity update(
			@RequestParam("id") Long id,
			ServletRequest request, ServletResponse response) {
		
		try {			
			Optional<Item> item = itemService.findById(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames(); 
			MultipartFile multipartFile = multipartRequest.getFile(iter.next());
			String fileName = id + ".png";
			
			Files.delete(Paths.get("src/main/resources/static/images/item/"+fileName));
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream buffer = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/images/item/" + fileName)));
			buffer.write(bytes);
			buffer.close();
			
			return new ResponseEntity("Uploaded successfully!", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed.", HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="/itemList", method=RequestMethod.GET)
	public List<Item> getItemList() {
		return itemService.showAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Item> getItem (@PathVariable ("id") Long id) {
		return this.itemService.findById(id);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public ResponseEntity deleteItem(@PathVariable ("id") Long id) {
		this.itemService.deleteItem(id);
		
		return new ResponseEntity("Item deleted successfully", HttpStatus.OK);
	}
	
	

}
