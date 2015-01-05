package com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.sun.net.httpserver.HttpServer;

/**
 * Servlet Filter implementation class LogFilter
 */
// @WebFilter(filterName="/LogFilter",urlPatterns="/*")
public class LogFilter implements Filter {

	private FilterConfig config;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		this.config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		ServletContext context = this.config.getServletContext();
		long before = System.currentTimeMillis();
		System.out.println("开始过滤...");
		// 将请求转换成 HttpServletRequest 请求
		HttpServletRequest hrequest = (HttpServletRequest)request;
		System.out.println("Fileter 已经截取到用户的请求地址：" + hrequest.getServletPath());
		// pass the request along the filter chain
		chain.doFilter(request, response);
		long after = System.currentTimeMillis();
		System.out.println("过滤结束");
		System.out.println("请求被定位到" + hrequest.getRequestURI() + "  所花时间为：" + (after - before));
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}

}
