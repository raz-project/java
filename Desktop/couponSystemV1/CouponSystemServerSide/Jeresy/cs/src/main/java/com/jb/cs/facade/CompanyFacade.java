package com.jb.cs.facade;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.jb.cs.DAOException.CouponAllReadyExistsExption;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.InvalidUpdateExeption;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.dao.CompanyDBDao;
import com.jb.cs.db.dao.CompanyDao;
import com.jb.cs.db.dao.CouponCategory;
import com.jb.cs.db.dao.CouponDBDao;
import com.jb.cs.db.dao.CouponDao;
import com.jb.cs.model.Company;
import com.jb.cs.model.Coupon;


public class CompanyFacade extends AbsFacade {

     //Fields 
	private final Company company;
	private final CompanyDao companyDao;
	private final CouponDao couponDao;

	//Constructor
	private CompanyFacade(Company company, CompanyDao companyDao, CouponDao couponDao) {
		this.company = company;
		this.companyDao = companyDao;
		this.couponDao = couponDao;
	}
/**
 *  * This function will create new CompanyDBDao by 'name' and 'password' and use
	 * the login function inside CompanyDBDao and return CompanyFacade type
 * @param name
 * @param password
 * @return
 * @throws SystemMalfunctionException
 * @throws InvalidLoginException
 * @throws NoSuchCompanyException
 */
	protected static CompanyFacade performLogin(String name, String password)
			throws SystemMalfunctionException, InvalidLoginException, NoSuchCompanyException {
		CompanyDBDao companyDBDao = new CompanyDBDao();
		Company company = companyDBDao.login(name, password);
		return new CompanyFacade(company, companyDBDao, new CouponDBDao());
	}

	
	/**
	 * 	 Create a coupon in data base coupon you wish to create new coupon with same title will reject
	 * @param coupon
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 * @throws NoSuchCompanyException
	 * @throws CouponNotExistsException
	 * @throws CouponAllReadyExistsExption
	 */
	public void createCoupon(Coupon coupon) throws SystemMalfunctionException, CouponNotExistsException,
			NoSuchCompanyException, CouponNotExistsException, CouponAllReadyExistsExption {

		Collection<Coupon> allCoupons = couponDao.getAllCoupon();
		for (Coupon c : allCoupons) {
			if (c.getTitle().equals(coupon.getTitle())) {
				throw new CouponAllReadyExistsExption(
						String.format("coupon with title %s already exists!", coupon.getTitle()));
			}
		}
		couponDao.createCoupon(coupon, company.getId());
	}
	

	/**
	 * This function get 'couponId' and search the correct coupon and remove it from DB
	 * @param couponId
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 * @throws NoSuchCouponException
	 */
	public void removeCoupon(long couponId)
			throws SystemMalfunctionException, CouponNotExistsException, NoSuchCouponException {
		couponDao.removeCoupon(couponId);
	}

	/**
	 * This function get 'couponId' and search table coupon in DB and return the right coupon
	 * @param couponId
	 * @return coupon
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 * @throws CouponNotExistsException
	 */
	public Coupon getCoupon(long couponId)
			throws SystemMalfunctionException, CouponNotExistsException, CouponNotExistsException {
		return couponDao.getCoupon(couponId);
	}

	
	/**
	 * This function get 'coupon' and search the correct coupon and update to DB
	 * @param coupon
	 * @throws SystemMalfunctionException
	 * @throws CouponNotExistsException
	 * @throws CouponNotExistsException
	 * @throws InvalidUpdateExeption
	 * @throws NoSuchCouponException
	 * @throws NoSuchCompanyException
	 */
	public void updateCoupon(Coupon coupon) throws SystemMalfunctionException, CouponNotExistsException,
			CouponNotExistsException, InvalidUpdateExeption, NoSuchCouponException, NoSuchCompanyException {
		Coupon c = getCoupon(coupon.getId());
//
//		if (!c.getTitle().equals(coupon.getTitle())) {
//			throw new InvalidUpdateExeption("cant update coupon");
//		}
		couponDao.updateCoupon( coupon);
	}

	//Empty constructor
	public Company getCompany() {
		return company;
	}

	
	/**This function will get all the coupons in DB
	 * Collection<Coupon>
	 * @return
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	public Collection<Coupon> getAllCoupons() throws SystemMalfunctionException, NoSuchCompanyException {
		return companyDao.getCoupons(company.getId());
	}

	
	/**
	 * This function will get a category number (enum class) and return all the coupons in the category 
	 * @param category
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	public Collection<Coupon> getCouponsByCategory(CouponCategory category)
			throws SystemMalfunctionException, NoSuchCompanyException {
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
	 * This function will get a companyId  and return all the coupons of correct company
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 */
	public Collection<Coupon> getCompanyCoupons() throws SystemMalfunctionException {
		return companyDao.getCoupons(company.getId());

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

	
	/**This function will get a date (LocalDate)  and return all the coupons below given date
	 * @param date
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 * @throws CouponNotExistsException
	 */
	public Collection<Coupon> getCouponsBeforeEndDate(LocalDate date) throws SystemMalfunctionException,
			NoSuchCompanyException, CouponNotExistsException {
		Collection<Coupon> getCouponsBeforeEndDate = couponDao.getAllCoupon();
		Collection<Coupon> couponsBeforeEndDate = new ArrayList<>();
		for (Coupon c : getCouponsBeforeEndDate) {
			if (c.getEndDate().isBefore(date)) {
				couponsBeforeEndDate.add(c);
			}
		}
		return couponsBeforeEndDate;
	}

	
}
