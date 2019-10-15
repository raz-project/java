package com.jb.cs.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.DAOException.ZeroCouponAmountException;
import com.jb.cs.common.ConnctionPool;
import com.jb.cs.common.ResorceUtils;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.Schema;
import com.jb.cs.model.Coupon;

public class CouponDBDao implements CouponDao {

	public CouponDBDao() {
		try {
			createTable();
		}
		catch (SystemMalfunctionException e) {

		}
	}

	@Override
	public void createTable() throws SystemMalfunctionException {
		Connection connection = null;
		Statement stmtCreateCouponTable = null;
		Statement stmtCreateCompanyCouponTable = null;
		try {
			connection = ConnctionPool.getInstance().getConnction();
			stmtCreateCouponTable = connection.createStatement();
			stmtCreateCouponTable.executeUpdate(Schema.getCreateTableCoupon());

			stmtCreateCompanyCouponTable = connection.createStatement();
			stmtCreateCompanyCouponTable.executeUpdate(Schema.getCreateTableCompanyCoupon());

		}
		catch (SQLException e) {

			throw new SystemMalfunctionException(" unable to create coupon table" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtCreateCouponTable, stmtCreateCompanyCouponTable);
		}

	}

	@Override
	public void createCoupon(Coupon coupon, long companyId) throws SystemMalfunctionException {
		Connection connection = null;
		PreparedStatement preStmtInsertCoupon = null;
		PreparedStatement preStmtInsertCompanyCoupon = null;

		try {
			
			connection = ConnctionPool.getInstance().getConnction();
			preStmtInsertCoupon = connection.prepareStatement(Schema.getInsertCoupon(),
					Statement.RETURN_GENERATED_KEYS);
			applyCouponValuesOnStatment(preStmtInsertCoupon, coupon);
			preStmtInsertCoupon.executeUpdate();

			long couponId = getGeneratedPrimeryKey(preStmtInsertCoupon);

			preStmtInsertCompanyCoupon = connection.prepareStatement(Schema.getInsertCompanyCoupon());
			preStmtInsertCompanyCoupon.setLong(1, companyId);
			preStmtInsertCompanyCoupon.setLong(2, couponId);
			preStmtInsertCompanyCoupon.executeUpdate();

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to insert to coupon table" + e.getMessage());

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtInsertCoupon, preStmtInsertCompanyCoupon);
		}
	}

	private long getGeneratedPrimeryKey(PreparedStatement preStmtInsertCoupon) throws SQLException {

		long key = -1;

		ResultSet generatedKeys = preStmtInsertCoupon.getGeneratedKeys();

		if (generatedKeys.next()) {
			key = generatedKeys.getInt(1);
		}
		return key;
	}

	@Override
	public void removeCoupon(long couponId)
			throws SystemMalfunctionException, CouponNotExistsException, NoSuchCouponException {
		Connection connection = null;
		PreparedStatement preStmtRemoveCoupon = null;
		if (couponId == Coupon.NO_ID) {
			throw new NoSuchCouponException(String.format("unable to remove coupon or invalid id %d", couponId));
		}

		try {

			connection = ConnctionPool.getInstance().getConnction();
			preStmtRemoveCoupon = connection.prepareStatement(Schema.getGetDeleteCoupon());
			preStmtRemoveCoupon.setLong(1, couponId);
			preStmtRemoveCoupon.executeUpdate();

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to remove coupon from table" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtRemoveCoupon);
		}

	}

	@Override
	public void updateCoupon(Coupon coupon) throws SystemMalfunctionException, NoSuchCouponException {

		Connection connection = null;
		PreparedStatement preStmtUpdateCoupon = null;

		long couponId = coupon.getId();

		if (couponId == Coupon.NO_ID) {
			throw new NoSuchCouponException(" unable to update coupon ");
		}
		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtUpdateCoupon = connection.prepareStatement(Schema.getGetUpdateCouponById());
			applyCouponValuesOnStatment(preStmtUpdateCoupon, coupon);
			preStmtUpdateCoupon.setLong(9, couponId);

			int affectedRows = preStmtUpdateCoupon.executeUpdate();

			if (affectedRows == 0) {
				throw new NoSuchCouponException(String.format(" no such coupon with this id %d ", couponId));
			}
		}
		catch (SQLException e) {

			throw new SystemMalfunctionException("there was a problem to update coupon" + e.getMessage());

		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtUpdateCoupon);
		}
	}

	private void applyCouponValuesOnStatment(PreparedStatement preStmtCreateCoupon, Coupon coupon) throws SQLException {
		preStmtCreateCoupon.setString(1, coupon.getTitle());
		preStmtCreateCoupon.setDate(2, Date.valueOf(coupon.getStartDate()));
		preStmtCreateCoupon.setDate(3, Date.valueOf(coupon.getEndDate()));
		preStmtCreateCoupon.setInt(4, coupon.getAmount());
		preStmtCreateCoupon.setInt(5, coupon.getCategory());
		preStmtCreateCoupon.setString(6, coupon.getMessage());
		preStmtCreateCoupon.setDouble(7, coupon.getPrice());
		preStmtCreateCoupon.setString(8, coupon.getImageURL());
	}

	@Override
	public void decrementCouponAmount(long couponId) throws SystemMalfunctionException, ZeroCouponAmountException {
		Connection connection = null;
		PreparedStatement preStmtDecrementCouponAmount = null;

		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtDecrementCouponAmount = connection.prepareStatement(Schema.getGetDecrementcouponAmount());
			preStmtDecrementCouponAmount.setLong(1, couponId);
			int effctedRows = preStmtDecrementCouponAmount.executeUpdate();
			if (effctedRows == 0) {
				throw new ZeroCouponAmountException(
						String.format(" there is no such coupon in the system %d ", couponId));
			}

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException(" there was a problem to dcrement coupon ");
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtDecrementCouponAmount);
		}

	}

	@Override
	public Coupon getCoupon(long couponId) throws SystemMalfunctionException, CouponNotExistsException {
		Connection connection = null;
		PreparedStatement preStmtGetCoupon = null;
		ResultSet rs = null;
		Coupon coupon = null;
		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtGetCoupon = connection.prepareStatement(Schema.getGetCouponById());
			preStmtGetCoupon.setLong(1, couponId);
			rs = preStmtGetCoupon.executeQuery();

			if (rs.first()) {
				coupon = resultSetToCoupon(rs);
			}
		}
		catch (SQLException e) {
			throw new SystemMalfunctionException(" unble to get coupon");
		}
		finally {

			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtGetCoupon);
			ResorceUtils.close(rs);
		}
		return coupon;
	}

	private Coupon resultSetToCoupon(ResultSet rs) throws SQLException {
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

	@Override
	public Collection<Coupon> getAllCoupon() throws SystemMalfunctionException, CouponNotExistsException {
		Connection connection = null;
		Statement stmtGetAllCoupons = null;
		ResultSet rs = null;
		Collection<Coupon> couponsCompanyList = Collections.emptyList();

		try {

			connection = ConnctionPool.getInstance().getConnction();

			stmtGetAllCoupons = connection.createStatement();
			rs = stmtGetAllCoupons.executeQuery(Schema.getGetSelectAllCouponsId());

			if (rs.next()) {
				couponsCompanyList = new ArrayList<>();
				do {
					long couponId = rs.getLong(1);
					Coupon coupon = getCoupon(couponId);
					couponsCompanyList.add(coupon);
				}
				while (rs.next());
			}

		}
		catch (SQLException e) {
			throw new SystemMalfunctionException(" faild geting all coupons" + e.getMessage());
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(stmtGetAllCoupons);
			ResorceUtils.close(rs);
		}
		return couponsCompanyList;

	}

	@Override
	public Collection<Coupon> getCouponsByCategory(CouponCategory category)
			throws SystemMalfunctionException, CouponNotExistsException {
		Connection connection = null;
		PreparedStatement preStmtCouponsByCategory = null;
		ResultSet rs = null;
		int c = category.returnCategoryByNumber();

		Collection<Coupon> couponsByCategory = Collections.emptyList();

		try {
			connection = ConnctionPool.getInstance().getConnction();

			preStmtCouponsByCategory = connection.prepareStatement(Schema.getGetSelectCouponByCategry());
			preStmtCouponsByCategory.setInt(1, c);
			rs = preStmtCouponsByCategory.executeQuery();

			couponsByCategory = new ArrayList<>();
			while (rs.next()) {
				long couponId = rs.getLong(1);
				Coupon coupon = getCoupon(couponId);
				couponsByCategory.add(coupon);
			}
			if (couponsByCategory.isEmpty()) {
				throw new CouponNotExistsException("there is no coupon under this category");
			}
			return couponsByCategory;
		}
		catch (SQLException e) {
			throw new SystemMalfunctionException("unable to get coupons");
		}
		finally {
			ConnctionPool.getInstance().returnConnction(connection);
			ResorceUtils.close(preStmtCouponsByCategory);
			ResorceUtils.close(rs);
		}
	}

	@Override
	public void purchaseCoupon(long couponId, long customerId) throws SystemMalfunctionException,
			ZeroCouponAmountException {
		Connection connection = null;
		CustomerDBDao customerDBDao = null;
		try {

			customerDBDao = new CustomerDBDao();
			connection = ConnctionPool.getInstance().getConnction();
			// Initialization object
			connection.setAutoCommit(false);
			// Put all function that want to run in same time
			customerDBDao.insertCustomerCoupon(couponId, customerId);
			decrementCouponAmount(couponId);
			// Commit all function are done (true)
			connection.commit();

			if (connection != null) {

				// If not all function are done cancel all (false)
				connection.rollback();
			}

		}
		catch (SQLException | CouponAllreadyPurchasedException | CouponNotExistsException | NoSuchCustomerException e) {
			throw new SystemMalfunctionException("cant purchase coupon");
		}
		finally {

			ConnctionPool.getInstance().returnConnction(connection);
		}

	}

}
