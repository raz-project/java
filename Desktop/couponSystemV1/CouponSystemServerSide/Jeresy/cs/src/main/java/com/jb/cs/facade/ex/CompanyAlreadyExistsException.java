package com.jb.cs.facade.ex;

@SuppressWarnings("serial")
public class CompanyAlreadyExistsException extends Exception {

	public CompanyAlreadyExistsException(String msg) {
		super(msg);
	}
}
