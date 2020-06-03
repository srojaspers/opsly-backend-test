package com.thepracticaldeveloper.reactiveweb.domain;

public class Tweet {
	
	private String username;
	private String tweet;
	
	
	public Tweet() {
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTweet() {
		return tweet;
	}


	public void setTweet(String tweet) {
		this.tweet = tweet;
	}


	public Tweet(String username, String tweet) {
		this.username = username;
		this.tweet = tweet;
	}
	
	
	
	



	
}
