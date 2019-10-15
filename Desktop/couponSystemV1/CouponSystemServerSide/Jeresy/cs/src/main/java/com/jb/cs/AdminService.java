package com.jb.cs;

import java.util.Collection;
import java.util.Collections;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidUpdateExeption;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCouponException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.facade.AdminFacade;
import com.jb.cs.facade.ex.CompanyAlreadyExistsException;
import com.jb.cs.facade.ex.CustomerAlreadyExistsException;
import com.jb.cs.model.Company;
import com.jb.cs.model.Customer;

@Path("admin")
public class AdminService {

	@Context
	private HttpServletRequest request;

	private AdminFacade getFacade() {
		HttpSession session = request.getSession(false);
		return (AdminFacade) session.getAttribute(LoginServlet.ADMIN_FACADE);
	}

	@POST
	@Path("createCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCustomer(Customer customer) {

		try {

			getFacade().createCustomer(customer);

			return "A new customer was created successfully with JSON!";

		}
		catch (SystemMalfunctionException | NoSuchCustomerException | CustomerAlreadyExistsException
				| CouponNotExistsException e) {

			return e.getMessage();
		}

	}

	@DELETE
	@Path("removeCustomer/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCustomer(@PathParam("id") long id) {

		try {

			getFacade().removeCustomer(id);

			return "A customer was removed successfully with JSON!";

		}
		catch (SystemMalfunctionException | NoSuchCustomerException e) {

			return e.getMessage();
		}

	}

	@PUT
	@Path("updateCustomer")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCustomer(Customer customer) {

		try {

			getFacade().updateCustomer(customer);

			return "A customer was update successfully with JSON!";

		}
		catch (SystemMalfunctionException | NoSuchCustomerException | InvalidUpdateExeption
				| CouponNotExistsException e) {

			return e.getMessage();
		}

	}

	@GET
	@Path("getCustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Customer getCustomer(@PathParam("id") long customerId) {

		try {

			return getFacade().getCustomerById(customerId);

		}
		catch (SystemMalfunctionException | NoSuchCustomerException | CouponNotExistsException e1) {
			return (Customer) Collections.emptyList();

		}

	}

	@GET
	@Path("getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)

	public Collection<Customer> getAllCustomers() {

		try {

			return getFacade().getAllCustomers();

		}
		catch (SystemMalfunctionException | NoSuchCustomerException | CouponNotExistsException e1) {
			return Collections.emptyList();

		}

	}

	@POST
	@Path("createCompany")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCompany(Company company) {

		try {

			getFacade().createCompany(company);

			return "A new company was created successfully with JSON!";

		}
		catch (SystemMalfunctionException | CompanyAlreadyExistsException | NoSuchCompanyException e) {

			return e.getMessage();
		}

	}

	@PUT
	@Path("updateCompany")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCompany(Company company) {

		try {

			getFacade().updateComppany(company);

			return "A company was update successfully with JSON!";

		}
		catch (SystemMalfunctionException | NoSuchCompanyException | InvalidUpdateExeption e) {

			return e.getMessage();
		}

	}

	@GET
	@Path("getCompany/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(@PathParam("id") long companyId) {
		try {
			return getFacade().getCompany(companyId);
		}
		catch (SystemMalfunctionException | NoSuchCompanyException e1) {
			return null;
		}
	}

	@DELETE
	@Path("removeCompany/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeCompany(@PathParam("id") long id) {

		try {

			getFacade().removeCompany(id);

			return "A company was removed successfully with JSON!";

		}
		catch (SystemMalfunctionException | NoSuchCompanyException
				| NoSuchCouponException e) {

			return e.getMessage();
		}

	}

	@GET
	@Path("getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompanies() {

		try {

			return getFacade().getAllCompanies();

		}
		catch (SystemMalfunctionException | NoSuchCompanyException e) {
			return Collections.emptyList();
		}
	}

}
