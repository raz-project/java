package com.jb.cs.DAOException;

@SuppressWarnings("serial")
public class ZeroCouponAmountException extends Exception { 

	public ZeroCouponAmountException(String msg) {
		super(msg);
	}
}
