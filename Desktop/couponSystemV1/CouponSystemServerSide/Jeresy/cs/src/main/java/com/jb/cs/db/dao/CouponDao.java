package com.jb.cs.db.dao;

import java.util.Collection;

import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.DAOException.ZeroCouponAmountException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.model.Coupon;


public interface CouponDao {

	/**
	 * Create table in coupon DB
	 * @throws SystemMalfunctionException
	 */
	void createTable() throws SystemMalfunctionException;
	
	
	/**
	 * This function  create a new coupon in table
	 * @param coupon
	 * @param companyId
	 * @throws SystemMalfunctionException
	 */
	void createCoupon(Coupon coupon, long companyId) throws SystemMalfunctionException;

	
	/**
	 * This function remove specific coupon from table
	 * @param couponId
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 * @throws NoSuchCouponException
	 */
	void removeCoupon(long couponId) throws SystemMalfunctionException, CouponNotExistsException, NoSuchCouponException;

	
	/**
	 * This function update coupon in DB
	 * @param coupon
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCouponException
	 * @throws NoSuchCompanyException
	 */
	void updateCoupon(Coupon coupon) throws SystemMalfunctionException, NoSuchCouponException, NoSuchCompanyException;

	
	/**
	 * This function decrement coupon amount from table coupon
	 * @param couponId
	 * @throws SystemMalfunctionException
	 * @throws ZeroCouponAmountException
	 */
	void decrementCouponAmount(long couponId) throws SystemMalfunctionException, ZeroCouponAmountException;

	
	/**
	 *This function purchase the specific coupon with couponId and customerId 
	 * @param couponId
	 * @param customerId
	 * @throws SystemMalfunctionException
	 * @throws ZeroCouponAmountException
	 */
	void purchaseCoupon(long couponId, long customerId) throws SystemMalfunctionException, ZeroCouponAmountException;
	
	
	/**
	 * This function get couponId and return the specific coupon
	 * @param couponId
	 * @return Coupon
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 */
	Coupon getCoupon(long couponId) throws SystemMalfunctionException, CouponNotExistsException;

	
	/**
	 * This function return all coupons in DB
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 */
	Collection<Coupon> getAllCoupon() throws SystemMalfunctionException, CouponNotExistsException;

	
	/**
	 * This function get a specific category and return all coupon under the category
	 * @param category
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 */
	Collection<Coupon> getCouponsByCategory(CouponCategory category) throws SystemMalfunctionException, CouponNotExistsException;
	
}