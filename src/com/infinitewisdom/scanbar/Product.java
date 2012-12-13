package com.infinitewisdom.scanbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Product {

	private final String webservice;
	private String name, description, image, code;
	private int id, rating, num_votes;
	private ArrayList<ProductLocation> locations;
	private ArrayList<Comment> comments;

	public Product(String code) {
		Config conf = Config.getInstance();
		this.webservice = conf.getWebService();
		this.code = code;
		getProduct();		
		loadComments();
		loadLocations();
	}

	public void getProduct() {
		String str = this.webservice + "product/" + this.code;
		try {
			URL url = new URL(str);
			URLConnection urlc = url.openConnection();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line;
			while ((line = bfr.readLine()) != null) {
				JSONArray jsa = new JSONArray(line);
				for (int i = 0; i < jsa.length(); i++) {
					JSONObject jo = (JSONObject) jsa.get(i);
					code = jo.getString("code");
					rating = jo.getInt("rating");
					id = jo.getInt("id");
					setNum_votes(jo.getInt("num_votes"));
					name = jo.getString("name");
					setImage(jo.getString("image"));
					description = jo.getString("description");
				}
			}
		} catch (Exception e) {

		}
	}
	
	private void loadComments() {
		String str = this.webservice + "comments/" + this.code;
		comments = new ArrayList<Comment>();
		try {
			URL url = new URL(str);
			URLConnection urlc = url.openConnection();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line;
			while ((line = bfr.readLine()) != null) {
				JSONArray jsa = new JSONArray(line);
				for (int i = 0; i < jsa.length(); i++) {
					JSONObject jo = (JSONObject) jsa.get(i);
					
					JSONArray loc = jo.getJSONArray("comments");
					for (int j = 0; i < loc.length(); j++) {
						JSONObject jo2 = (JSONObject) jsa.get(j);
						String name = jo2.getString("name");
						String comm = jo2.getString("comment");
						
						comments.add(new Comment(name, comm));
					}
				}
			}
		} catch (Exception e) {

		}
	}
	
	private void loadLocations() {
		String str = this.webservice + "locations/" + this.code;
		locations = new ArrayList<ProductLocation>();
		try {
			URL url = new URL(str);
			URLConnection urlc = url.openConnection();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(
					urlc.getInputStream()));
			String line;
			while ((line = bfr.readLine()) != null) {
				JSONArray jsa = new JSONArray(line);
				for (int i = 0; i < jsa.length(); i++) {
					JSONObject jo = (JSONObject) jsa.get(i);
					
					JSONArray loc = jo.getJSONArray("locations");
					for (int j = 0; i < loc.length(); j++) {
						JSONObject jo2 = (JSONObject) jsa.get(j);
						String latitude = jo2.getString("latitude");
						String longitude = jo2.getString("longitude");
						
						locations.add(new ProductLocation(latitude, longitude));
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void setProduct() {
		// put product to webservice
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNum_votes() {
		return num_votes;
	}

	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWebservice() {
		return webservice;
	}

	public void setLocations(ArrayList<ProductLocation> locations) {
		this.locations = locations;
	}


	public ArrayList<ProductLocation> getLocations() {
		return locations;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	
	

}
