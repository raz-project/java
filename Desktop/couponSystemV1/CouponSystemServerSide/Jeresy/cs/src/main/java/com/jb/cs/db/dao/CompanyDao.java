package com.jb.cs.db.dao;

import java.util.Collection;

import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.model.Company;
import com.jb.cs.model.Coupon;



public interface CompanyDao {

	/**
	 * Create table in company DB
	 * 
	 * @throws SystemMalfunctionException
	 */
	void createTable() throws SystemMalfunctionException;

	/**
	 * This function create a new company in table
	 * 
	 * @param company
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	void createCompany(Company company) throws SystemMalfunctionException, NoSuchCompanyException;

	/**
	 * This function remove specific company from table
	 * 
	 * @param companyId
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	void removeCompany(long companyId) throws SystemMalfunctionException, NoSuchCompanyException;

	/**
	 * This function update company in DB
	 * 
	 * @param company
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	void updateCompany(Company company) throws SystemMalfunctionException, NoSuchCompanyException;

	/**
	 * This function get companyId and return the specific company
	 * 
	 * @param companyId
	 * @return Company
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	Company getCompany(long companyId) throws SystemMalfunctionException, NoSuchCompanyException;

	/**
	 * This function return all company in DB
	 * 
	 * @return Collection<Company>
	 * @throws SystemMalfunctionException
	 * @throws NoSuchCompanyException
	 */
	Collection<Company> getAllCompanies() throws SystemMalfunctionException, NoSuchCompanyException;

	/**
	 * This function get companyId and return all coupons for specific company
	 * 
	 * @param companyId
	 * @return Collection<Coupon>
	 * @throws SystemMalfunctionException
	 */
	Collection<Coupon> getCoupons(long companyId) throws SystemMalfunctionException;

	/**
	 * This function get 'name' and 'password' and make a login to DB
	 * 
	 * @param name
	 * @param password
	 * @return Company
	 * @throws SystemMalfunctionException
	 * @throws InvalidLoginException
	 * @throws NoSuchCompanyException
	 */
	Company login(String name, String password) throws SystemMalfunctionException, InvalidLoginException,
			NoSuchCompanyException;

}
