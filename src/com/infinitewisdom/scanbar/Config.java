package com.infinitewisdom.scanbar;

public class Config {
	
	private String webService = "http://ultim8.info/scanbar/";
	
	public static Config inst;

	private Config() {
	}

	public String getWebService() {
		return webService;
	}
	
	public static Config getInstance() {
		if (inst == null) {
			inst = new Config();
		}
		
		return inst;
	}
	

}
