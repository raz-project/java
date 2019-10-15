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

public class CORSfilter implements Filter {
	
	  public CORSfilter() {
	        System.out.println("CORS Filter invoked");
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        // TODO Auto-generated method stub      
	    }
	    
	    @Override
	    public void destroy() {
	    
	    }
	    
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        
	        System.out.println("CORS filter run!");
	        HttpServletResponse resp = (HttpServletResponse) response;
	        
	        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	        resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, PUT, POST, DELETE,PATCH");
	        resp.addHeader("Access-Control-Allow-Credentials", "true");
	        resp.addHeader("Access-Control-Allow-Headers", "content-type");
	        
	        if (req.getMethod().equals("OPTIONS")) {
	            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	            return;
	        }
	        
	        chain.doFilter(request, resp);
	    }

}
