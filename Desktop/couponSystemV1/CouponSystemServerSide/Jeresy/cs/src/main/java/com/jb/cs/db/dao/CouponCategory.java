package com.jb.cs.db.dao;

public enum CouponCategory {

	TRAVELING(1), FOOD(2), ELECTTRICITY(3), HEALTH(4), SPORTS(5), CAMPING(6), FASHION(7), STUDIES(8);

	private  int category;

	private CouponCategory(int category) {
		this.category = category;

	}

	public int returnCategoryByNumber() {
		return category;
	}

}
