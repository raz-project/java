package com.jb.cs.facade;

import java.util.Collection;

import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.InvalidUpdateExeption;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.dao.CompanyDBDao;
import com.jb.cs.db.dao.CompanyDao;
import com.jb.cs.db.dao.CouponDBDao;
import com.jb.cs.db.dao.CouponDao;
import com.jb.cs.db.dao.CustomerDBDao;
import com.jb.cs.db.dao.CustomerDao;
import com.jb.cs.facade.ex.CompanyAlreadyExistsException;
import com.jb.cs.facade.ex.CustomerAlreadyExistsException;
import com.jb.cs.model.Company;
import com.jb.cs.model.Coupon;
import com.jb.cs.model.Customer;

public class AdminFacade extends AbsFacade {
    //Fields 
	private final CouponDao couponDao;
	private final CustomerDao customerDao;
	private final CompanyDao companyDao;
	
    //Constructor 
	public AdminFacade(CouponDao couponDao, CustomerDao customerDao, CompanyDao companyDao)
			throws InvalidLoginException {

		this.couponDao = couponDao;
		this.customerDao = customerDao;
		this.companyDao = companyDao;
	}
/**
 * This function will use login by 'name' and 'password'   and use
	 * the login function inside Admin and return absFacade type
 * @param name
 * @param password
 * @return
 * @throws InvalidLoginException
 */
	protected static AdminFacade performLogin(String name, String password) throws InvalidLoginException {
		if ("admin".equals(name) && "1234".equals(password)) {
			return new AdminFacade(new CouponDBDao(), new CustomerDBDao(), new CompanyDBDao());
		} else {
			throw new InvalidLoginException("cant log to admin with this name and pasword");
		}
	}
/**
 * This function will create a new company table if not exist in DB
 * @param company
 * @throws SystemMalfunctionException
 * @throws CompanyAlreadyExistsException
 * @throws NoSuchCompanyException
 */
	public void createCompany(Company company)
			throws SystemMalfunctionException, CompanyAlreadyExistsException, NoSuchCompanyException {

		Collection<Company> allCompanies = companyDao.getAllCompanies();
		for (Company c : allCompanies) {
			if (c.getName().equals(company.getName())) {
				throw new CompanyAlreadyExistsException(
						String.format("this company %s already exist", company.getName()));
			}
		}
		companyDao.createCompany(company);
	}

	/**
	 * This function get 'companyId' and search the correct company and remove it from DB
	 * @param compnyId
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 * @throws NoSuchCouponException
	 */
	public void removeCompany(long compnyId)
			throws SystemMalfunctionException, NoSuchCompanyException, NoSuchCouponException {

		Collection<Coupon> coupons = companyDao.getCoupons(compnyId);

		companyDao.removeCompany(compnyId);

		for (Coupon coupon : coupons) {
			try {
				couponDao.removeCoupon(coupon.getId());
			} catch (CouponNotExistsException e) {

			}
		}
	}

	/**
	 * This function get 'Company' and search the correct company and update to DB
	 * @param company
	 * @throws InvalidUpdateExeption
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	public void updateComppany(Company company)
			throws InvalidUpdateExeption, SystemMalfunctionException, NoSuchCompanyException {

		Company c = companyDao.getCompany(company.getId());

		if (!c.getName().equals(company.getName())) {

			throw new InvalidUpdateExeption("Unable to update company's name!");
		}
		companyDao.updateCompany(company);
	}

	/**
	 *This function will return all companies from table company in DB
	 * @return Collection<Company> 
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	public Collection<Company> getAllCompanies()
			throws SystemMalfunctionException, NoSuchCompanyException {
		return companyDao.getAllCompanies();
	}

	/**
	 * This function will get specific companyId and  return Company.
	 * @param companyId
	 * @return Company 
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */

	/**
	 * * This function will create a new company table if not exist in DB
	 * @param companyId
	 * @return
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	public Company getCompany(long companyId) 
			throws SystemMalfunctionException, NoSuchCompanyException {

		return companyDao.getCompany(companyId);
	}
	
	/**
	 *  * This function will create a new customer table if not exist in DB
	 * @param customer
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CustomerAlreadyExistsException
	 * @throws CouponNotExistsException
	 */
	public void createCustomer(Customer customer) 
			throws SystemMalfunctionException, NoSuchCustomerException, CustomerAlreadyExistsException, CouponNotExistsException {
		
		Collection<Customer> allCustomers = customerDao.getAllCustomer();
		
		for (Customer c : allCustomers) {
			if (c.getName().equals(customer.getName())) {
				throw new CustomerAlreadyExistsException
				      (String.format("this customer %s is in the system", customer.getName()));
			}
		}
		customerDao.createCustomer(customer);
	}
	
	
	/**
	 * This function get 'customerId' and search the correct customer and remove it from DB
	 * @param customerId
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 */
	public void removeCustomer(long customerId) 
			throws SystemMalfunctionException, NoSuchCustomerException {
		
		customerDao.removeCustomer(customerId);
	}
	
	/**
	 * This function get 'Customer' and search the correct customer and update to DB
	 * @param customer
	 * @throws InvalidUpdateExeption
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	public void updateCustomer(Customer customer) 
			throws InvalidUpdateExeption, SystemMalfunctionException, NoSuchCustomerException, CouponNotExistsException {
		
	Collection<Customer>allCustomers = customerDao.getAllCustomer();
	
	for (Customer c : allCustomers) {
		if (c.getId() == customer.getId() && !c.getName().equals(customer.getName())) {
			throw new InvalidUpdateExeption("Changing customer's name is not allowed!");
		}
	}
	customerDao.updateCustomer(customer);
	}
	
	
	/**
	 * This function will return all companies from table company in DB
	 * @return Collection<Customer>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	public Collection<Customer>getAllCustomers() 
			throws SystemMalfunctionException, NoSuchCustomerException, CouponNotExistsException{
		return customerDao.getAllCustomer();
	}
	
	/**
	 * This function will get customerId and return the customer from table customer in DB.
	 * @param customerId
	 * @return customer by customerId
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCustomerException
	 * @throws CouponNotExistsException
	 */
	public Customer getCustomerById(long customerId)
			throws SystemMalfunctionException, NoSuchCustomerException, CouponNotExistsException {
		return customerDao.getCustomer(customerId);
	}
	
	

	

	
	

}
