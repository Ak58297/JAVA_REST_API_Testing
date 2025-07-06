package Pojo.Get_PojoClasses;

import java.util.Arrays;
import java.util.List;

import Pojo.Post.locations2;

public class getResponse {
	
	
	private locations2 location2;
	private int accuracy;
	private String name;
	private int phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;

	
	public locations2 getLocation() {
		return location2;
	}
	public void setLocation(locations2 location2) {
		this.location2 = location2;
	}
	public int getAccuracy() {
		return accuracy;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhone_number() {
		return phone_number;
	}
	
	public String getAddress() {
		return address;
	}

	public List<String> getTypes() {
		
		return types;
	}

	public String getWebsite() {
		return website;
	}

	public String getLanguage() {
		return language;
	}
	


}
