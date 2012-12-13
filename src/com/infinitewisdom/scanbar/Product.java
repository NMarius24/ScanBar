package com.infinitewisdom.scanbar;

public class Product {
	
	private final String webservice="";	
	private String name,description,photo;
	private int id,rating;
	
	public void getProduct()
	{
		//get from webservice
	}
	
	public void setProduct()
	{
		//put product to webservice		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Product(){}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
