package com.jb.cs.test;

import java.sql.SQLException;

import com.jb.cs.DAOException.CouponAllReadyExistsExption;
import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.InvalidUpdateExeption;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.DAOException.ZeroCouponAmountException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.facade.ex.CompanyAlreadyExistsException;
import com.jb.cs.facade.ex.CustomerAlreadyExistsException;


public class Test {
	public static void main(String[] args)
			throws SystemMalfunctionException, InvalidLoginException, NoSuchCompanyException, NoSuchCustomerException,
			CompanyAlreadyExistsException, NoSuchCouponException,
			 CouponNotExistsException, CouponAllReadyExistsExption, InvalidUpdateExeption, CouponAllreadyPurchasedException, ZeroCouponAmountException, SQLException, CustomerAlreadyExistsException {

	     //TEST
		
		//Create all Dao classes for use their function
//		
//		CouponDao coupon = new CouponDBDao();
//		CompanyDao company = new CompanyDBDao();
//		CustomerDao customer = new CustomerDBDao();
//
		
		//Create tables in SQL (DB)
//		
//		company.createTable();
//		coupon.createTable();
//		customer.createTable();

		
		//Create company in DB
//		
//		Company a1 = new Company(45, "aaa", "12345", " ww@rgf");
//		Company a2 = new Company (456, "bbb", "222", "bbb");
//		Company a3 = new Company(356, "ccc", "333", "ccc");
//		Company a4 = new Company(3, "ddd", "434", "ddd");
//		Company a5 = new Company(89, "eee", "555", "eee");
//
//		
		//Create customer in DB
//		
//		Customer b1 = new Customer(222, "ppp", "456");
//		Customer b2 = new Customer(9, "bbb", "222");
//		Customer b3 = new Customer(65, "ccc", "333");
//		Customer b4 = new Customer(4, "ddd", "abc");
//		Customer b5 = new Customer(1, "eee", "555");
//
//		
		//Create coupon in DB
//		
//		Coupon d1 = new Coupon(1, "zzz", LocalDate.of(2019, 12, 11), LocalDate.of(2019, 12, 23), 450, 1, "aaa", 2.3,
//				"aaa");
//		Coupon d2 = new Coupon(3, "bbb", LocalDate.of(2019, 4, 5), LocalDate.of(2019, 12, 23), 324, 3, "bbb", 34,
//				"bbb");
//		Coupon d3 = new Coupon(5, "ccc", LocalDate.of(2019, 1, 14), LocalDate.of(2019, 12, 23), 543, 4, "ccc", 200,
//				"ccc");
//		Coupon d4 = new Coupon(6, "ddd", LocalDate.of(2019, 2, 24), LocalDate.of(2019, 12, 23), 1200, 2, "ddd", 25,
//				"ddd");
//		Coupon d5 = new Coupon(9, "eee", LocalDate.of(2019, 1, 17), LocalDate.of(2019, 12, 23), 2400, 1, "eee", 50,
//				"eee");
//
//		
//		
//		----------------------------------------------------------------------ADMIN---------------------------------------------------------------------------
//		
		//Login Admin facade make sure all details is compatible with exception
//		
//		AdminFacade adminFacade = (AdminFacade) AbsFacade.login("admin", "1234", LoginType.ADMIN);
//
//		
		//Admin create company in DB
//		
//	adminFacade.createCompany(a1); 
//	adminFacade.createCompany(a2);
//	adminFacade.createCompany(a3);
//	adminFacade.createCompany(a4);
//	adminFacade.createCompany(a5);
////
//		
		//Admin create customer in DB
//		
//		adminFacade.createCustomer(b1);
//		adminFacade.createCustomer(b2);
//		adminFacade.createCustomer(b3);
//		adminFacade.createCustomer(b4);
//		adminFacade.createCustomer(b5);
//
//		
		//Admin remove company from DB
//	adminFacade.removeCompany(4);
//		
//		
		//Admin remove customer from DB
//		
//	adminFacade.removeCustomer(5);
//
//	adminFacade.removeCustomer(6);
//
//		
//		
		//Admin update company in DB make sure all details is compatible with exception (title)
//		
//	adminFacade.updateComppany(a4);
//		
//		
		//Admin update customer in DB make sure all details is compatible with exception(name)
//		
//	adminFacade.updateCustomer(b4);
//
//		
//		
		//Admin get company from DB by ID of company
//		
//     System.out.println(adminFacade.getCompnyById(1));
//		
//		
		//Admin get customer from DB by ID of customer
//		
//	  System.out.println( adminFacade.getCustomerById(4));	
//		
//		
		//Admin get all companies in DB
//		
//	System.out.println(adminFacade.getAllCompanies());
//		
//		
		//Admin get all customers in DB
//		
//	System.out.println(adminFacade.getAllCustomers());
//
//		
//		
//		------------------------------------------------------------------CompanyFacade-------------------------------------------------------------------------------------------------------------------------
//		
//		
		//Login company facade make sure all details is compatible with exception
//		
//		CompanyFacade companyFacade = (CompanyFacade) AbsFacade.login("aaa", "12345", LoginType.COMPANY);
//		CompanyFacade companyFacade1 = (CompanyFacade) AbsFacade.login("bbb", "222", LoginType.COMPANY);
//
//		
//		
		//Company create new coupon for sale in DB
//		
//	companyFacade.createCoupon(d1);
//	companyFacade.createCoupon(d2);
//	companyFacade.createCoupon(d3);
//	companyFacade.createCoupon(d4);
//	companyFacade.createCoupon(d5);
//	companyFacade1.createCoupon(d5);
//
//		
//		
		//Company remove customer from DB
//		
//	companyFacade.removeCoupon(1);
//	companyFacade1.removeCoupon(4);
//
//
//
		//Trying to update coupon to DB 
//		
//		Coupon d6 = new Coupon(7, "aaa", LocalDate.of(2019, 2, 23), LocalDate.of(2019, 12, 22), 1200, 2, "ddd", 25,
//				"ddd");
//		
		//Company update coupon to DB
//		companyFacade.updateCoupon(d6);

//		
//		Coupon d7 = new Coupon(11, "eee", LocalDate.of(2019, 2, 24), LocalDate.of(2019, 12, 23), 1200, 2, "Rany", 25,
//				"ddd");
//		
//		
//		companyFacade1.updateCoupon(d7);
//
//
		//Company get the coupon by id
//		
//	System.out.println(companyFacade.getCoupon(2));
//		
//		
		//Company get all coupons (of her)
//		
//	System.out.println(companyFacade.getAllCoupons());
//		
//		
		//Company get all coupons below given price
//		
//	System.out.println(companyFacade.getCouponsBelowPrice(1));	
//		System.out.println(companyFacade.getCouponsBelowPrice(100));
//		
//				
		//Company get all coupons under given category
//	System.out.println(companyFacade.getCouponsByCategory(CouponCategory.SPORTS));
//	System.out.println(companyFacade.getCouponsByCategory(CouponCategory.FOOD));
//		
//		
		//Company get all coupons under given date
//		
//	System.out.println(companyFacade.getCouponsBeforeEndDate(LocalDate.of(2020, 12, 22)));
//		
//
//		
//		-------------------------------------------------------------------------------CustomerFacade-------------------------------------------------------------------------------------------------
//
//		
//		
		//Login customer facade make sure all details is compatible with exception
//		
//	CustomerFacade customerFacade = (CustomerFacade) AbsFacade.login("ddd", "abc", LoginType.CUSTOMER);
//
//		
//		
		//Customer purchase coupon  
//		
//	customerFacade.purchaseCoupon(2);
//
//		
//		
		//Customer get all coupon (his)
//		
//	System.out.println(customerFacade.getAllCoupons());
//
//
		//Customer get all coupons under given category 
//		
//	System.out.println(customerFacade.getCouponsByCategory(CouponCategory.TRAVELING));
//
//		
//		
		//Company get all coupons below given price
//		
//	System.out.println(customerFacade.getCouponsBelowPrice(0));
	}

}
