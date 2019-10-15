package com.jb.cs.DAOException;

@SuppressWarnings("serial")
public class CouponAllreadyPurchasedException extends Exception {
	
	public CouponAllreadyPurchasedException (String msg) {
		super( msg);
	}

}
