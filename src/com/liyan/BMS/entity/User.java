package com.liyan.BMS.entity;

public class User {
	private String userId;
	private int userType;
	public User() {
		
	}
	public User(String userId, int userType) {
		super();
		this.userId = userId;
		this.userType = userType;
	}
	
	public User(int userType) {
		super();
		this.userType = userType;
	}

	public String getId() {
		return userId;
	}
	public void setId(String id) {
		this.userId = id;
	}
	public int getType() {
		return userType;
	}
	public void setType(int userType) {
		this.userType = userType;
	}
	
	
}
