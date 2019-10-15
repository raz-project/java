package com.jb.cs;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionFilter implements Filter {

	public SessionFilter() {

		System.out.println("SessionFilter constructor invoked.");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// no-op.
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		
		String k = servletRequest.getRequestURI().split("/")[3]+"Facade";
		 

		HttpSession session = servletRequest.getSession(false);

		System.out.println("Filter was called.");

		if (session ==null||session.getAttribute(k)==null) {
			HttpServletResponse resp = (HttpServletResponse) response;
			/* This is the filtering */
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		else {
			chain.doFilter(servletRequest, response);
		}
	}

}
