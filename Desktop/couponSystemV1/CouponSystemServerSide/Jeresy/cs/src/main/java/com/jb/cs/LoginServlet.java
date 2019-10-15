package com.jb.cs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jb.cs.DAOException.CouponNotExistsException;
import com.jb.cs.DAOException.InvalidLoginException;
import com.jb.cs.DAOException.NoSuchCompanyException;
import com.jb.cs.DAOException.NoSuchCustomerException;
import com.jb.cs.common.SystemMalfunctionException;
import com.jb.cs.facade.AbsFacade;
import com.jb.cs.facade.LoginType;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
//	public static final String KEY_FACADE = "keyFacade";
	
	public static final String ADMIN_FACADE = "adminFacade";
	public static final String COMPANY_FASADE = "companyFacade";
	public static final String CUSTOMER_FACADE = "customerFacade";
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("WEB-INF/login.html").forward(req, resp);

	}


	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1.Create session.
		// 2. user value ("admin") -> LoginType..
		// user value ("1234") -> LoginType..
		// user value ("ADMIN") -> LoginType.
		// 3. save facade in session
		// 4. forward the user to the appropriate page: admin, company, customer.
		
		// true - if there is no session create one.
		// false - session exists.
		HttpSession session = req.getSession(true);

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String loginTypeStr = req.getParameter("loginType");
		
		// Convert loginType.
		

		 LoginType type = LoginType.valueOf(loginTypeStr);
		
		
		try {
			
			// Login to get facade.
			AbsFacade facade = AbsFacade.login(userName, password, type);
			// Save the facade in the session.
			

			String k;
			
			// Facade that return
			
			switch (type) {
			case ADMIN:
				k = ADMIN_FACADE;
				break;
			case COMPANY:
				k = COMPANY_FASADE;
				break;
			default: /* Customer */
				k = CUSTOMER_FACADE;
				break;
				
			}
			
			// Save Session 
			
			session.setAttribute(k, facade);
			
			
			// Sends the user to appropriate page.
//			req.getRequestDispatcher(pagePath).forward(req, resp);

		} catch (InvalidLoginException | SystemMalfunctionException | NoSuchCustomerException
				| CouponNotExistsException | NoSuchCompanyException e) {
			// Send the user again to the login page.

//			resp.sendRedirect(req.getContextPath() + "/login");
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		// 1.Create session.
//		// 2. user value ("admin") -> LoginType..
//		// user value ("1234") -> LoginType..
//		// user value ("ADMIN") -> LoginType.
//		// 3. save facade in session
//		// 4. forward the user to the appropriate page: admin, company, customer.
//		
//		// true - if there is no session create one.
//		// false - session exists.
//		HttpSession session = req.getSession(true);
//
//		// Get all request parameters.
//
//		String userName = req.getParameter("userName");
//		String password = req.getParameter("password");
//		String loginType = req.getParameter("loginType");
//		
//		// Convert loginType.
//		LoginType type ;
//		
//		if (loginType!= null) {
//			type = LoginType.valueOf(loginType);
//		} else {
//			type = LoginType.valueOf("ADMIN");
//		}
//
//		try {
//			// Login to get facade.
//			AbsFacade facade = AbsFacade.login(userName, password, type);
//			// Save the facade in the session.
//			session.setAttribute(KEY_FACADE, facade);
//
//			String pagePath;
//			// Page selection.
//			switch (type) {
//			case ADMIN:
//				pagePath = "WEB-INF/admin.html";
//				break;
//			case COMPANY:
//				pagePath = "WEB-INF/company.html";
//				break;
//			default: /* Customer */
//				pagePath = "WEB-INF/customer.html";
//				break;
//
//			}
//			// Sends the user to appropriate page.
//			req.getRequestDispatcher(pagePath).forward(req, resp);
//
//		} catch (InvalidLoginException | SystemMalfunctionException | NoSuchCustomerException
//				| CouponNotExistsException | NoSuchCompanyException e) {
//			// Send the user again to the login page.
//
//			resp.sendRedirect(req.getContextPath() + "/login");
//		}
//
//	}

}
	
	
	
	
	
