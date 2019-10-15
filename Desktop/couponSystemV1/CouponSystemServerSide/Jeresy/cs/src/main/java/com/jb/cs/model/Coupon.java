package com.jb.cs.model;

import java.time.LocalDate;

public class Coupon {

	//Fileds
	
		//Default argument for filed
	public static final int NO_ID = -1;

	private long id = NO_ID;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private int category;
	private String message;
	private double price;
	private String imageURL;

	
	//Empty constructor
     public Coupon() {
	
            }
     
   //Constructor initializing fildes
	public Coupon( long id,String title, LocalDate startDate, LocalDate endDate, int amount, int category,
			String message, double price, String imageURL) {
	      this.id =id;
		  this.title = title;
		  this.startDate = startDate;
		  this.endDate = endDate;
		  this.amount = amount;
		  this.category = category;
		  this.message = message;
		  this.price = price;
		  this.imageURL = imageURL;
	}
	
	
	//Getters
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public int getAmount() {
		return amount;
	}

	public int getCategory() {
		return category;
	}

	public String getMessage() {
		return message;
	}

	public double getPrice() {
		return price;
	}

	public String getImageURL() {
		return imageURL;
	}

	
	//Setters
	public void setId(long id) {
		this.id = id;                                               
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	@Override
	public String toString() {
		return "this is the selected coupon id: " + id + " title: " + title + " start day: " + startDate + " end date: " + endDate + " amount: " + amount 
				+ " category: " + category + " messge: " + message + " price: " + price + " image url: " + imageURL;
	}

}
