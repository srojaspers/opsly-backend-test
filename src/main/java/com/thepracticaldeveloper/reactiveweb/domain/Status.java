package com.thepracticaldeveloper.reactiveweb.domain;

public class Status {
	
	private String name;
	private String status;
	
	
	public Status() {
		
	}
	
	
	
	public Status(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
