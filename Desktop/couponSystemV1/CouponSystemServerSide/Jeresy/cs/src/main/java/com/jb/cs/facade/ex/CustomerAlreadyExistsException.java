package com.jb.cs.facade.ex;

@SuppressWarnings("serial")
public class CustomerAlreadyExistsException extends Exception {

	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}

}
