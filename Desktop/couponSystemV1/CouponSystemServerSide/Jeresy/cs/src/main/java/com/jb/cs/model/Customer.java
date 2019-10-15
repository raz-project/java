package com.jb.cs.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	//Fileds
	
	//Default argument for filed
	public static final int NO_ID = -1;

	private long id = NO_ID;
	private String name;
	private String password;
	private Set<Coupon> coupons;
	
	
	//Constructor initializing set of coupons
	public Customer() {
		coupons = new HashSet<>();
	}

	
	//Constructor initializing rest of fildes
	public Customer(long id, String name, String password) {
	    this.id =  id;
		this.name = name;
		this.password = password;
		
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

	public String getPassword() {
		return password;
	}
	
	public Set<Coupon> getCoupons() {
		return coupons;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return  " customer name " + name +" customer pass " + password +"coupons "  + coupons;
	}

}
