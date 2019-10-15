package com.jb.cs.facade;

import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.SystemMalfunctionException;

public abstract class AbsFacade {
	
	/**
	 * This function will inheritance
	 * @param name
	 * @param password
	 * @param taype
	 * @return AbsFacade
	 * @throws InvalidLoginException 
	 * @throws InvalidLoginExeption 
	 * @throws SystemMalfunctionException 
	 * @throws NoSuchCompanyException 
	 * @throws NoSuchCustomerException 
	 * @throws CouponNotExistsException 
	 */

	public static AbsFacade login(String name, String password, LoginType type) throws SystemMalfunctionException, InvalidLoginException, NoSuchCompanyException, NoSuchCustomerException, CouponNotExistsException {
		switch (type) {
			case ADMIN:
				System.out.println("admin");
				return AdminFacade.performLogin(name, password);
			case COMPANY:
				System.out.println("company");
				return CompanyFacade.performLogin(name, password);
			case CUSTOMER:
				System.out.println("customer");
				return CustomerFacade.performLogin(name, password);    //this is the wrong line
			default:
				throw new InvalidLoginException("login type is not supported");
		}
	}

}
