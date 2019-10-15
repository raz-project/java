package com.jb.cs.facade;

/*Enum class create the types*/
public enum LoginType {
	
	//Types
	ADMIN(1),
	COMPANY(2),
	CUSTOMER(3);
	
	//Filed
	private final int value;
	
	//Getters
	private LoginType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
