package  com.jb.cs;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.jb.cs.DAOException.CouponAllreadyPurchasedException;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.DAOException.ZeroCouponAmountException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.dao.CouponCategory;
import com.jb.cs.facade.CustomerFacade;
import com.jb.cs.model.Coupon;

@Path("customer")
public class CustomerService {

	@Context
	private HttpServletRequest request;

	private CustomerFacade getFacade() {

		HttpSession session = request.getSession(false);
		return (CustomerFacade) session.getAttribute(LoginServlet.CUSTOMER_FACADE);
	}

	@DELETE
	@Path("purchaseCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String purchaseCoupon(@QueryParam("id") long couponId) {

		try {
			getFacade().purchaseCoupon(couponId);
			return "Coupon Purchase successfully! ";
		} catch (SystemMalfunctionException | CouponAllreadyPurchasedException| CouponNotExistsException
				| NoSuchCustomerException | ZeroCouponAmountException | SQLException e) {
			return ("Unable to purchase coupon " + e.getMessage());
		}
	}

	@GET
	@Path("getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupons() {

		try {
			return getFacade().getAllCoupons();
		} catch (SystemMalfunctionException | CouponNotExistsException e) {
			return Collections.emptyList();
		}

	}
	
	@GET
	@Path("getCustomerCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCustomerCoupons() {

		try {
			return getFacade().getCustomerCoupons();
		} catch (SystemMalfunctionException | CouponNotExistsException e) {
			return Collections.emptyList();
		}

	}

	@GET
	@Path("getCouponsByCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCategory(@QueryParam("category") String strCategory) {

		try {

			CouponCategory cat = CouponCategory.valueOf(strCategory);
			return getFacade().getCouponsByCategory(cat);

		} catch (SystemMalfunctionException | NoSuchCompanyException | CouponNotExistsException e) {
			return Collections.emptyList();
		}
	}

	@GET
	@Path("getCouponsBelowPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsBelowPrice(@QueryParam("price") double price) {

		try {
			return getFacade().getCouponsBelowPrice(price);
		} catch (SystemMalfunctionException | NoSuchCompanyException | CouponNotExistsException e) {
			return Collections.emptyList();
		}

	}


	@GET
	@Path("getCouponsByPriceLowToHigh")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByPriceLowToHigh() {

		try {
			Collection<Coupon> allCoupons = getFacade().getAllCoupons();
			
			allCoupons.stream().sorted(new Comparator<Coupon>() {

				@Override
				public int compare(Coupon o1, Coupon o2) {
				
					return (int) (o1.getPrice() - o2.getPrice());
				}
			}).collect(Collectors.toList());
			return allCoupons;
		} catch (SystemMalfunctionException | CouponNotExistsException e) {
			return Collections.emptyList();
		}

	}

	@GET
	@Path("getCouponsByPriceHighToLow")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByPriceHighToLow() {
		try {
			Collection<Coupon> allCoupons = getFacade().getAllCoupons();
			allCoupons.stream().sorted(new Comparator<Coupon>() {

				@Override
				public int compare(Coupon o1, Coupon o2) {
				
					return (int) (o2.getPrice()-o1.getPrice());
				}
			}).collect(Collectors.toList());
			return allCoupons;
			
		} catch (SystemMalfunctionException | CouponNotExistsException e) {
			return Collections.emptyList();
		}

	}
	

}
