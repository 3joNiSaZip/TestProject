package com.kh.jsp.member.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.kh.jsp.member.wrapper.LoginWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
@WebFilter("*.me")
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest h_request = (HttpServletRequest)request;
		
		LoginWrapper lw = new LoginWrapper(h_request);
		
		// pass the request along the filter chain
		chain.doFilter(lw, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
