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

import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.ConnctionPool;
import com.jb.cs.common.ResorceUtils;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.Schema;
import com.jb.cs.model.Company;
import com.jb.cs.model.Coupon;
import com.jb.cs.model.Customer;

public class CustomerDBDao implements CustomerDao {

	public CustomerDBDao() {
		try {
			createTable();
		}
		catch (SystemMalfunctionException e) {

		}
	}

	@Override
	public void createTable() throws SystemMalfunctionException {
		Statement stmtCreateCustomerTable = null;
		Statement stmtCreateCustomerCouponTable = null;
		Connection connection = null;

		try {
			// Create Customer table.
			connection = ConnctionPool.getInstance().getConnction();
			stmtCreateCustomerTable = connection.createStatement();
			stmtCreateCustomerTable.executeUpdate(Schema.getCreateTableCustomer());
			// Create Customer Coupon table.
			stmtCreateCustomerCouponTable = connection.createStatement();
			stmtCreateCustomerCouponTable.executeUpdate(Schema.getCreateTableCustomerCoupon());

		}
		catch (Exception e) {
			throw new SystemMalfunctionException("there was a problem to creating customer table" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtCreateCustomerTable, stmtCreateCustomerCouponTable);
		}

	}

	@Override
	public void createCustomer(Customer customer) throws SystemMalfunctionException, NoSuchCustomerException {

		Connection connection = null;
		PreparedStatement preStmtCreateCustomer = null;
		try {
			connection = ConnctionPool.getInstance().getConnction();
			// insert into customer table
			preStmtCreateCustomer = connection.prepareStatement(Schema.getInsertCustomer());
			applyCustomerValuesOnStatment(preStmtCreateCustomer, customer);

			preStmtCreateCustomer.executeUpdate();
		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("there was a problem to create customer" + e.getMessage());

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtCreateCustomer);
		}
	}

	private static void applyCustomerValuesOnStatment(PreparedStatement preStmtCreateCustomer, Customer customer)
			throws SQLException {
		preStmtCreateCustomer.setString(1, customer.getName());
		preStmtCreateCustomer.setString(2, customer.getPassword());
	}

	@Override
	public void removeCustomer(long customerId) throws SystemMalfunctionException, NoSuchCustomerException {
		Connection connection = null;
		PreparedStatement preStmtRemoveCustomer = null;

		if (customerId == Customer.NO_ID) {
			throw new NoSuchCustomerException(String.format("unable to remove customer invalid id %d ", customerId));

		}
		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtRemoveCustomer = connection.prepareStatement(Schema.getGetDeleteCustomerById());
			preStmtRemoveCustomer.setLong(1, customerId);

			preStmtRemoveCustomer.executeUpdate();
		}
		catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to remove customer" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtRemoveCustomer);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws SystemMalfunctionException, NoSuchCustomerException {
		Connection connection = null;
		PreparedStatement preStmtUpdateCustomer = null;

		long customerId = customer.getId();

		if (customerId == Company.NO_ID) {
			throw new NoSuchCustomerException("unable to update customer ");
		}
		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtUpdateCustomer = connection.prepareStatement(Schema.getGetUpdateCustomerById());
			applyCustomerValuesOnStatment(preStmtUpdateCustomer, customer);
			preStmtUpdateCustomer.setLong(3, customerId);

			int affectedRows = preStmtUpdateCustomer.executeUpdate();

			if (affectedRows == 0) {
				throw new NoSuchCustomerException(String.format(" no such customer with this id %d ", customerId));
			}
		}
		catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to update customer " + e.getMessage());

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtUpdateCustomer);
		}
	}

	@Override
	public Customer getCustomer(long customerId) throws SystemMalfunctionException, NoSuchCustomerException,
			CouponNotExistsException {
		Connection connection = null;
		Customer customer = null;
		PreparedStatement preStmtGetCustomer = null;

		try {

			connection = ConnctionPool.getInstance().getConnction();

			preStmtGetCustomer = connection.prepareStatement(Schema.getGetSelectCustomer());
			preStmtGetCustomer.setLong(1, customerId);

			ResultSet rs = preStmtGetCustomer.executeQuery();

			if (rs.first()) {
				customer = resultSetToCustomer(rs);
				customer.setCoupons(getCustomerCoupons(customerId, connection));
			}
			else {
				throw new NoSuchCustomerException(String.format("there is no customer with this id %d ", customerId));
			}

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to get customer " + e.getMessage());

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtGetCustomer);
		}
		return customer;
	}

	private static Set<Coupon> getCustomerCoupons(long id, Connection connection) throws SystemMalfunctionException,
			CouponNotExistsException {
		PreparedStatement preStmtSelectCustomerCouponsStatment = null;
		Set<Coupon> coupons = null;
		try {
			preStmtSelectCustomerCouponsStatment = connection
					.prepareStatement(Schema.getGetCustomerCouponsInnerJoinById());
			preStmtSelectCustomerCouponsStatment.setLong(1, id);

			ResultSet rs = preStmtSelectCustomerCouponsStatment.executeQuery();

			coupons = new HashSet<>();

			while (rs.next()) {
				coupons.add(resultSetToCoupon(rs));
			}

		}
		catch (SQLException e) {

			throw new SystemMalfunctionException("unable to get customers coupon");

		}
		finally {

			ResorceUtils.close(preStmtSelectCustomerCouponsStatment);

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

	private static Customer resultSetToCustomer(ResultSet rs) throws SQLException {
		long id = rs.getLong(1);
		String name = rs.getString(2);
		String password = rs.getString(3);

		return new Customer(id, name, password);
	}

	@Override
	public Collection<Customer> getAllCustomer() throws SystemMalfunctionException, NoSuchCustomerException,
			CouponNotExistsException {
		Connection connection = null;
		Statement stmtGetAllCustomers = null;
		ResultSet rs = null;
		Collection<Customer> listOfAllCustomers = Collections.emptyList();

		try {

			connection = ConnctionPool.getInstance().getConnction();
			stmtGetAllCustomers = connection.createStatement();
			rs = stmtGetAllCustomers.executeQuery(Schema.getGetSelectAllIdCustomers());

			listOfAllCustomers = new ArrayList<>();
			while (rs.next()) {

				long customerId = rs.getLong(1);
				Customer customer = getCustomer(customerId);
				listOfAllCustomers.add(customer);

			}

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to connect to get all customers" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtGetAllCustomers);
			ResorceUtils.close(rs);
		}

		return listOfAllCustomers;
	}

	@Override
	public Collection<Coupon> getCoupons(long customerId) throws SystemMalfunctionException, CouponNotExistsException {

		Connection connection = null;

		try {
			connection = ConnctionPool.getInstance().getConnction();
			return getCustomerCoupons(customerId, connection);

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
		}
	}

	@Override
	public void insertCustomerCoupon(long couponId, long customerId) throws SystemMalfunctionException,
			CouponAllreadyPurchasedException, CouponNotExistsException, NoSuchCustomerException {
		Connection connection = null;
		PreparedStatement preStmtInsertCustomerCoupon = null;

		try {

			connection = ConnctionPool.getInstance().getConnction();

			preStmtInsertCustomerCoupon = connection.prepareStatement(Schema.getInsertCustomerCoupon());
			preStmtInsertCustomerCoupon.setLong(1, customerId);
			preStmtInsertCustomerCoupon.setLong(2, couponId);
			int effctedRows = preStmtInsertCustomerCoupon.executeUpdate();
			if (effctedRows == 0) {
				throw new CouponAllreadyPurchasedException(
						String.format(" the coupon is ready purchased %d", couponId));
			}
		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to insert to customer couppon table " + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtInsertCustomerCoupon);
		}

	}

	@Override
	public Customer login(String name, String password)
			throws SystemMalfunctionException, InvalidLoginException, NoSuchCustomerException,
			CouponNotExistsException {
		Connection connection = null;
		PreparedStatement preStmtCustomerLogin = null;
		ResultSet rs = null;

		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtCustomerLogin = connection.prepareStatement(Schema.getGetSelectedCustomerByNameAndPassword());
			preStmtCustomerLogin.setString(1, name);
			preStmtCustomerLogin.setString(2, password);

			rs = preStmtCustomerLogin.executeQuery();
			if (rs.first()) {

				return getCustomer(rs.getLong(1));
			}
			else {
				throw new InvalidLoginException(
						String.format("invalid login for name %s and password %s", name, password));
			}
		}
		catch (SQLException e) {
			throw new SystemMalfunctionException(
					String.format("invalid login for name %s and password %s", name, password));
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtCustomerLogin);
			ResorceUtils.close(rs);
		}
	}

}
