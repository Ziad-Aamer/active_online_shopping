package com.onlineshopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("urlllllllllllll" + request.getRequestURI());
//		if (request.getRequestURI().equals("/online-shopping/customer/updatePassword")) {
//			response.sendRedirect("/online-shopping/facebook/");
//			return false;
//		}
		System.out.println("---Before Method Execution---" + request.getParameter("email") + "   password : "
				+ request.getParameter("password"));
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("---method executed---" + request.getParameter("email") + "   password : "
				+ request.getParameter("password"));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("---Request Completed---" + request.getParameter("email") + "   password : "
				+ request.getParameter("password"));
	}
}