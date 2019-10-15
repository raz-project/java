package com.jb.cs.model;

import java.util.HashSet;
import java.util.Set;

public class Company {

	//Fileds
	
	//Default argument for filed
	public static final int NO_ID = -1;

	private long id = NO_ID;
	private String name;
	private String password;
	private String email;
	private Set<Coupon> coupons;

	
	//Constructor initializing set of coupons
	public Company() {
		coupons = new HashSet<>();
	}

	
	//Constructor initializing rest of fildes
	public Company(long id, String name, String password, String email) {
	
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;

	}

	public Company(String name, String password, String email, Set<Coupon> coupons) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	

	//Getters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}


	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "company name " + name + "company password " + password + "," + email;
	}
}
