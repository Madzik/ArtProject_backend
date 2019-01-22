package com.onlinestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Item {
	
	private static final long serialVersionUID = 234546L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private String authorName;
	private String authorSurname;
	private String category;
	
	@Column(columnDefinition="text")
	private String description;
	
	private double inStockPrice;
	private double ourPrice;
	private double weight;
	private boolean isAvailable = true;
	private int inStockNumber;
	
	@Transient
	private MultipartFile images;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorSurname() {
		return authorSurname;
	}
	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getInStockPrice() {
		return inStockPrice;
	}
	public void setInStockPrice(double inStockPrice) {
		this.inStockPrice = inStockPrice;
	}
	public double getOurPrice() {
		return ourPrice;
	}
	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getInStockNumber() {
		return inStockNumber;
	}
	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

}
