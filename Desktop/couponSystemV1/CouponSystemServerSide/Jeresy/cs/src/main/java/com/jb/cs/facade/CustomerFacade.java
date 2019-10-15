package com.jb.cs.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.DAOException.ZeroCouponAmountException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.dao.CouponCategory;
import com.jb.cs.db.dao.CouponDBDao;
import com.jb.cs.db.dao.CouponDao;
import com.jb.cs.db.dao.CustomerDBDao;
import com.jb.cs.db.dao.CustomerDao;
import com.jb.cs.model.Coupon;
import com.jb.cs.model.Customer;

public class CustomerFacade extends AbsFacade {

	//Fields 
	private final Customer customer;
	private final CustomerDao customerDao;
	private final CouponDao couponDao;;

	//Constructor
	public CustomerFacade(Customer customer, CustomerDao customerDao, CouponDao couponDao) {
		this.customer = customer;
		this.customerDao = customerDao;
		this.couponDao = couponDao;
	}

	/**
	 * This function will create new CustomerDBDao by 'name' and 'password' and use
	 * the login function inside customerDBDao and return CustomerFacade type
	 * 
	 * @param name
	 * @param password
	 * @return CustomerFacade
	 * @throws SystemMalfunctionException
	 * @throws InvalidLoginException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	protected static CustomerFacade performLogin(String name, String password) throws SystemMalfunctionException,
			InvalidLoginException, NoSuchCustomerException, CouponNotExistsException {

		CustomerDBDao customerDBDao = new CustomerDBDao();
		Customer customer = customerDBDao.login(name, password);
		return new CustomerFacade(customer, customerDBDao, new CouponDBDao());

	}
	
	
	
	

	/**
	 * This function will get couponId and customerId and porch the coupon and decrement coupon from table 
	 * @param coupnId
	 * @throws SystemMalfunctionException
	 * @throws CouponAllreadyPurchasedException
	 * @throws CouponNotExistsException
	 * @throws NoSuchCustomerException
	 * @throws ZeroCouponAmountException
	 * @throws SQLException
	 * @throws CouponNotExistsException
	 */
	public void purchaseCoupon(long coupnId)
			throws SystemMalfunctionException, CouponAllreadyPurchasedException, CouponNotExistsException,
			NoSuchCustomerException, ZeroCouponAmountException, SQLException, CouponNotExistsException {
		couponDao.getCoupon(coupnId);
		customerDao.insertCustomerCoupon(coupnId, customer.getId());
		couponDao.decrementCouponAmount(coupnId);;
	}

	
	/**
	 * This function will get all the coupons in DB
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 */
	public Collection<Coupon> getCustomerCoupons() throws SystemMalfunctionException, CouponNotExistsException {
		return customerDao.getCoupons(customer.getId());
	}
	
	public Collection<Coupon> getAllCoupons() throws SystemMalfunctionException, CouponNotExistsException {
		return couponDao.getAllCoupon();
	}
	


	
	/**This function will get a category number (enum class) and return all the coupons in the category 
	 * Collection<Coupon>
	 * @param category
	 * @return
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 * @throws CouponNotExistsException
	 */
	public Collection<Coupon> getCouponsByCategory(CouponCategory category)
			throws SystemMalfunctionException, NoSuchCompanyException, CouponNotExistsException {
		Collection<Coupon> getCouponsByCategory = getAllCoupons();
		Collection<Coupon> coupons = new ArrayList<>();
		for (Coupon coupon : getCouponsByCategory) {
			if (coupon.getCategory() == category.returnCategoryByNumber()) {
				coupons.add(coupon);
			}
		}
		return coupons;
	}

	
	/**
	 * This function will get a price  and return all the coupons below given price
	 * @param price
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 * @throws CouponNotExistsException
	 */
	public Collection<Coupon> getCouponsBelowPrice(double price)
			throws SystemMalfunctionException, NoSuchCompanyException, CouponNotExistsException {
		Collection<Coupon> getCouponsBelowPrice = couponDao.getAllCoupon();
		Collection<Coupon> couponsBelowPrice = new ArrayList<>();
		for (Coupon c : getCouponsBelowPrice) {
			if (c.getPrice() < price) {
				couponsBelowPrice.add(c);
			}
		}
		return couponsBelowPrice;
	}

}
