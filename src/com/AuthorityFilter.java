package com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
// @WebFilter("/AuthorityFilter")
public class AuthorityFilter implements Filter {

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
		// 获取该filter的配置参数
		String encoding = config.getInitParameter("encoding");
		String loginPage = config.getInitParameter("loginPage");
		String proLogin = config.getInitParameter("proLogin");
		// 设置request的编码字符集
//		request.setCharacterEncoding(encoding);
		HttpServletRequest requ = (HttpServletRequest)request;
		HttpSession session = requ.getSession(true);
		// 获取用户请求的页面
		String requestPath = requ.getServletPath();
		// 如果session范围内的user为null, 即表明没有登录
		// 且用户请求的既不是登陆页面，也不是处理登录的页面
		if (session.getAttribute("name") == null && !requestPath.endsWith(loginPage) && !requestPath.endsWith(proLogin)) {
			// forward 到登陆界面
			request.setAttribute("tip", "您还没有登录");
			request.getRequestDispatcher(loginPage).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
	}

}
