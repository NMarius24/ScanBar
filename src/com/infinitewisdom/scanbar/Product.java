package com.infinitewisdom.scanbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Product {

	private final String webservice;
	private String name, description, image, code;
	private int id, rating, num_votes;
	private ArrayList locations;
	private ArrayList<HashMap<String, String>> comments;

	public Product(String code) {
		Config conf = Config.getInstance();
		this.webservice = conf.getWebService();
		this.code = code;
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
					code = jo.getInt("code");
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
	
	private void 

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

}
