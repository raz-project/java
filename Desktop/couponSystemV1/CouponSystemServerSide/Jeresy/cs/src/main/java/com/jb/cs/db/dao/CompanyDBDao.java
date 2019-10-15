package com.jb.cs.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.common.ConnctionPool;
import com.jb.cs.common.ResorceUtils;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.Schema;
import com.jb.cs.model.Company;
import com.jb.cs.model.Coupon;


public class CompanyDBDao implements CompanyDao {

	public CompanyDBDao() {
		try {
			createTable();
		} catch (SystemMalfunctionException e) {

		}

	}

	@Override
	public void createTable() throws SystemMalfunctionException {
		Statement stmtCreateCompanyTable = null;
		Statement stmtCreateCompanyCouponTable = null;
		Connection connection = null;
		try {
			// Create Company table.
			connection = ConnctionPool.getInstance().getConnction();
			stmtCreateCompanyTable = connection.createStatement();
			stmtCreateCompanyTable.executeUpdate(Schema.getCreateTableCompany());
			// Create Company Coupon table.
			stmtCreateCompanyCouponTable = connection.createStatement();
			stmtCreateCompanyCouponTable.executeUpdate(Schema.getCreateTableCompanyCoupon());
			
		} catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to creating company table" + e.getMessage());

		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtCreateCompanyTable, stmtCreateCompanyCouponTable);

		}

	}

	@Override
	public void createCompany(Company company) throws SystemMalfunctionException {

		Connection connection = null;
		PreparedStatement preStmtCreateCompany = null;
		try {
			connection = ConnctionPool.getInstance().getConnction();
			// insert into company table
			preStmtCreateCompany = connection.prepareStatement(Schema.getInsertCompany());
			applyCompanyValuesOnStatment(preStmtCreateCompany, company);

			preStmtCreateCompany.executeUpdate();
		} catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to create a company" + e.getMessage());

		} finally {

			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtCreateCompany);
		}
	}

	private static void applyCompanyValuesOnStatment(PreparedStatement preStmtCreateCompany, Company company)
			throws SQLException {
		preStmtCreateCompany.setString(1, company.getName());
		preStmtCreateCompany.setString(2, company.getPassword());
		preStmtCreateCompany.setString(3, company.getEmail());
	}

	@Override
	public void removeCompany(long companyId) throws SystemMalfunctionException, NoSuchCompanyException {
		Connection connection = null;
		PreparedStatement preStmtRemoveCompany = null;
		

		if (companyId == Company.NO_ID) {
			throw new NoSuchCompanyException(String.format("unable to remove company invalid id %d ", companyId));

		}
		try {
			connection = ConnctionPool.getInstance().getConnction();
			
			preStmtRemoveCompany = connection.prepareStatement(Schema.getGetDeleteColCompany());
			preStmtRemoveCompany.setLong(1, companyId);

			preStmtRemoveCompany.executeUpdate();
		} catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to remove company");
		} finally {

			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtRemoveCompany);
		}
	}

	@Override
	public void updateCompany(Company company) throws SystemMalfunctionException, NoSuchCompanyException {
		Connection connection = null;
		PreparedStatement preStmtUpdateCompany = null;

		long companyId = company.getId();

		if (companyId == Company.NO_ID) {
			throw new NoSuchCompanyException("unable to update company ");
		}
		try {
			connection = ConnctionPool.getInstance().getConnction();
			
			preStmtUpdateCompany = connection.prepareStatement(Schema.getGetUpdateCompanyById());
			applyCompanyValuesOnStatment(preStmtUpdateCompany, company);
			preStmtUpdateCompany.setLong(4, companyId);

			int affectedRows = preStmtUpdateCompany.executeUpdate();

			if (affectedRows == 0) {
				throw new NoSuchCompanyException(String.format(" no such company with this id %d ", companyId));
			}
		} catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to update company" + e.getMessage());

		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtUpdateCompany);
		}
	}

	@Override
	public Company getCompany(long companyId) throws SystemMalfunctionException, NoSuchCompanyException {
		Connection connection = null;
		Company company = null;
		PreparedStatement preStmtSelectCompany = null;

		try {

			connection = ConnctionPool.getInstance().getConnction();
			
			preStmtSelectCompany = connection.prepareStatement(Schema.getGetSelectedCompanyById());
			preStmtSelectCompany.setLong(1, companyId);

			ResultSet rs = preStmtSelectCompany.executeQuery();

			if (rs.first()) {
				company = resultSetToCompany(rs);
				company.setCoupons(getCompanyCoupons(companyId, connection));
			} else {
				throw new NoSuchCompanyException(String.format("there is no company with this id %d ", companyId));
			}

		} catch (SQLException e) {
			throw new SystemMalfunctionException("unable to get company" + e.getMessage());

		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtSelectCompany);
		}
		return company;
	}

	private static Set<Coupon> getCompanyCoupons(long id, Connection connection) throws SystemMalfunctionException {
		PreparedStatement preStmtSelectCompanyCouponsStatment = null;
		Set<Coupon> coupons = null;
		try {
			preStmtSelectCompanyCouponsStatment = connection.prepareStatement(Schema.getGetCompanyCouponsInnerJoinById());
			preStmtSelectCompanyCouponsStatment.setLong(1, id);

			ResultSet rs = preStmtSelectCompanyCouponsStatment.executeQuery();

			coupons = new HashSet<>();

			while (rs.next()) {
				coupons.add(resultSetToCoupon(rs));
			}

		} catch (SQLException e) {

			throw new SystemMalfunctionException("unable to get companies coupon");

		} finally {

			ResorceUtils.close(preStmtSelectCompanyCouponsStatment);

		}
		return coupons;
	}
	

	private static Coupon resultSetToCoupon(ResultSet rs) throws SQLException {
		Coupon coupon = new Coupon();
		coupon.setId(rs.getLong(1));
		coupon.setTitle(rs.getString(2));
		coupon.setStartDate(rs.getDate(3).toLocalDate());
		coupon.setEndDate(rs.getDate(4).toLocalDate());
		coupon.setAmount(rs.getInt(5));
		coupon.setCategory(rs.getInt(6));
		coupon.setMessage(rs.getString(7));
		coupon.setPrice(rs.getDouble(8));
		coupon.setImageURL(rs.getString(9));

		return coupon;

	}

	private static Company resultSetToCompany(ResultSet rs) throws SQLException {
		long id = rs.getLong(1);
		String name = rs.getString(2);
		String password = rs.getString(3);
		String email = rs.getString(4);

		return new Company(id, name, password, email);

		/*
		 * Company company = new Company(id, name, password, email);
		 * 
		 * return company;
		 */

	}

	@Override
	public Collection<Company> getAllCompanies() throws SystemMalfunctionException, NoSuchCompanyException {
		Connection connection = null;
		Statement stmtGetAllCompanies = null;
		ResultSet rs = null;
		/* is it possible to put any array that extend collection */
		Collection<Company> listOfAllCompany = Collections.emptyList();

		try {
			connection = ConnctionPool.getInstance().getConnction();
			stmtGetAllCompanies = connection.createStatement();
			rs = stmtGetAllCompanies.executeQuery(Schema.getGetSelectAllIdCompanies());
			
			listOfAllCompany = new ArrayList<>();
			while (rs.next()) {

				long companyId = rs.getLong(1);
				Company company = getCompany(companyId);
				listOfAllCompany.add(company);
			}

		} catch (SQLException e) {
			throw new SystemMalfunctionException("faild to get all companies");
		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtGetAllCompanies);
			ResorceUtils.close(rs);
		}
		return listOfAllCompany;
	}

	@Override
	public Collection<Coupon> getCoupons(long companyId) throws SystemMalfunctionException {
		Connection connection = null;

		try {
			connection = ConnctionPool.getInstance().getConnction();
			return getCompanyCoupons(companyId, connection);

		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
		}
	}

	@Override
	public Company login(String name, String password)
			throws SystemMalfunctionException, InvalidLoginException, NoSuchCompanyException {
		Connection connection = null;
		PreparedStatement preStmtGetCompanyLogin = null;
		ResultSet rs = null;

		try {
			connection = ConnctionPool.getInstance().getConnction();
			
			preStmtGetCompanyLogin = connection.prepareStatement(Schema.getGetSelectedCompanyByNameAndPassword());
			preStmtGetCompanyLogin.setString(1, name);
			preStmtGetCompanyLogin.setString(2, password);

			rs = preStmtGetCompanyLogin.executeQuery();

			if (rs.first()) {
				long companyId = rs.getLong(1);
				return getCompany(companyId);
			} else {
				throw new InvalidLoginException(
						String.format("invalid login for name %s and password %s", name, password));
			}
		} catch (SQLException e) {
			throw new SystemMalfunctionException(
					String.format("invalid login for name %s and password %s", name, password));
		} finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtGetCompanyLogin);
			ResorceUtils.close(rs);
		}
	}

}
