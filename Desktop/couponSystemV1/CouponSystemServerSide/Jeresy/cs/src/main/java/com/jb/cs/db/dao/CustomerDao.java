package com.jb.cs.db.dao;

import java.util.Collection;

import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.model.Coupon;
import com.jb.cs.model.Customer;



   public interface CustomerDao {

	   
	   /**
	    * Create table in customer DB
	    * @throws SystemMalfunctionException
	    */
	void createTable() throws SystemMalfunctionException;

	
	/**
	 * This function  create a new customer in table
	 * @param customer
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 */
	void createCustomer(Customer customer) throws SystemMalfunctionException, NoSuchCustomerException;

	
	/**
	 * This function remove specific customer from table
	 * @param customerId
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 */
	void removeCustomer(long customerId) throws SystemMalfunctionException, NoSuchCustomerException;

	
	/**
	 * This function update customer in DB
	 * @param customer
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 */
	void updateCustomer(Customer customer) throws SystemMalfunctionException, NoSuchCustomerException;

	
	/**
	 * This function get customerId and return the specific customer
	 * @param customerId
	 * @return Customer
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	Customer getCustomer(long customerId) throws SystemMalfunctionException, NoSuchCustomerException, CouponNotExistsException;

	
	/**
	 * This function return all customers in DB
	 * @return Collection<Customer>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	Collection<Customer> getAllCustomer() throws SystemMalfunctionException, NoSuchCustomerException, CouponNotExistsException;
	
	
	/**
	 * This function get customerId and return all coupons for specific customer
	 * @param CustomerId
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 */
	Collection<Coupon> getCoupons (long customerId) throws SystemMalfunctionException, CouponNotExistsException;
	
	
	
	/**
	 * This function get couponId and customerId and associate them in table in DB
	 * @param couponId
	 * @param customerId
	 * @throws SystemMalfunctionException
	 * @throws CouponAllreadyPurchasedException
	 * @throws CouponNotExistsException
	 * @throws NoSuchCustomerException
	 */
	void insertCustomerCoupon (long couponId , long customerId) throws SystemMalfunctionException ,
	                                                                                                        CouponAllreadyPurchasedException ,
	                                                                                                        CouponNotExistsException ,
	                                                                                                        NoSuchCustomerException ;
	
	
	/**
	 * This function get 'name' and 'password' and make a login to DB
	 * @param name
	 * @param password
	 * @return Customer
	 * @throws SystemMalfunctionException
	 * @throws InvalidLoginException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	Customer login(String name , String password) throws SystemMalfunctionException , InvalidLoginException, NoSuchCustomerException, CouponNotExistsException ;
	

}
