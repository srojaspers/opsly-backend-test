package com.thepracticaldeveloper.reactiveweb.domain;


import java.util.List;

public class SocialMedia {
	
	
	
	private List<Tweet> twitter;
	private List<Status> facebook;
	private List<Picture> instagram;
	public List<Tweet> getTwitter() {
		return twitter;
	}
	public void setTwitter(List<Tweet> twitter) {
		this.twitter = twitter;
	}
	public List<Status> getFacebook() {
		return facebook;
	}
	public void setFacebook(List<Status> facebook) {
		this.facebook = facebook;
	}
	public List<Picture> getInstagram() {
		return instagram;
	}
	public void setInstagram(List<Picture> instagram) {
		this.instagram = instagram;
	}
	public SocialMedia(List<Tweet> twitter, List<Status> facebook, List<Picture> instagram) {
		this.twitter = twitter;
		this.facebook = facebook;
		this.instagram = instagram;
	}
	public SocialMedia() {

	}
	
	
	
	
	
	
	
	
	
	
	
	

}
