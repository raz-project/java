package com.jb.cs;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.jb.cs.DAOException.CouponAllReadyExistsExption;
import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidUpdateExeption;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.db.dao.CouponCategory;
import com.jb.cs.facade.CompanyFacade;
import com.jb.cs.model.Coupon;
import com.jb.cs.model.RemoteCoupon;

@Path("company")
public class CompanyService {
	@Context
	private HttpServletRequest request;

	private CompanyFacade getFacade() {
		HttpSession session = request.getSession(false);
		return (CompanyFacade) session.getAttribute(LoginServlet.COMPANY_FASADE);
	}

	@POST
	@Path("createCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCoupon(RemoteCoupon coupon) {

		try {
			getFacade().createCoupon(coupon);

			return "A new coupon was created successfully with JSON!";

		}
		catch (SystemMalfunctionException | CouponNotExistsException | NoSuchCompanyException
				| CouponAllReadyExistsExption e) {
			return e.getMessage();
		}

	}

	@PUT
	@Path("updateCoupon")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCoupon(RemoteCoupon coupon) {

		try {

			getFacade().updateCoupon(coupon);

			return "A coupon was update successfully with JSON!";

		}
		catch (SystemMalfunctionException | CouponNotExistsException | NoSuchCouponException | InvalidUpdateExeption
				| NoSuchCompanyException e) {

			return e.getMessage();
		}

	}

	@GET
	@Path("getCoupon/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Coupon getCoupon(@PathParam("id") long couponId) {

		try {

			return getFacade().getCoupon(couponId);

		}
		catch (SystemMalfunctionException | CouponNotExistsException e1) {
			return (Coupon) Collections.emptyList();

		}

	}

	@DELETE
	@Path("removeCoupon/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCoupon(@PathParam("id") long id) {

		try {

			getFacade().removeCoupon(id);

			return "A coupon was removed successfully with JSON!";

		}
		catch (SystemMalfunctionException | CouponNotExistsException | NoSuchCouponException e) {

			return e.getMessage();
		}

	}

	@GET
	@Path("getAllCoupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupons() {

		try {

			return getFacade().getAllCoupons();

		}
		catch (SystemMalfunctionException | NoSuchCompanyException e) {
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

		}
		catch (SystemMalfunctionException | NoSuchCompanyException e) {
			return Collections.emptyList();
		}
	}

	@GET
	@Path("getCouponsBelowPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsBelowPrice(@QueryParam("price") double price) {

		try {

			return getFacade().getCouponsBelowPrice(price);

		}
		catch (SystemMalfunctionException | CouponNotExistsException
				| NoSuchCompanyException e) {
			return Collections.emptyList();
		}
	}

	@GET
	@Path("getCouponsBeforeEndDate")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsBeforeEndDate(@QueryParam("localDate") String strLocalDate) {

		try {

			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate parsedDate = LocalDate.parse(strLocalDate, dtFormat);
			return getFacade().getCouponsBeforeEndDate(parsedDate);

		}
		catch (SystemMalfunctionException | CouponNotExistsException
				| NoSuchCompanyException e) {
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

			// allCoupons.t = Collections.sort(lallCoupons);
			return allCoupons;

		}
		catch (SystemMalfunctionException | NoSuchCompanyException e) {
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
					// TODO Auto-generated method stub
					return (int) (o2.getPrice() - o1.getPrice());
				}
			}).collect(Collectors.toList());

			return allCoupons;

		}
		catch (SystemMalfunctionException | NoSuchCompanyException e) {
			return Collections.emptyList();
		}
	}

}