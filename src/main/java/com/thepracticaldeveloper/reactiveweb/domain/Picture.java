package com.thepracticaldeveloper.reactiveweb.domain;

public class Picture {
	
	String username;
	String picture;
	
	
	public Picture(String username, String picture) { 
		this.username = username;
		this.picture = picture;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	

}
