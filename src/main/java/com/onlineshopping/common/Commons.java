package com.onlineshopping.common;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Customer;

public class Commons {

	public Commons() {
	}

	public static double calculateTotalPrice(List<CartProduct> cartProducts) {
		double totalPrice = 0;
		for (CartProduct cartProduct : cartProducts) {
			totalPrice += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
		}
		return totalPrice;
	}

	public static int getProductsQuantity(List<CartProduct> cartProducts) {
		int quantity = 0;
		for (CartProduct cartProduct : cartProducts) {
			quantity += cartProduct.getQuantity();
		}
		return quantity;
	}

	public static String determineTargetUrl(Collection<? extends GrantedAuthority> authorities) {
		boolean isCustomer = false;
		boolean isAdmin = false;

		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println("Authority of logged in user : " + grantedAuthority.getAuthority());
			if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
				isCustomer = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		if (isCustomer) {
			return "/user/showUser";
		} else if (isAdmin) {
			return "/orders/list";
		} else {
			throw new IllegalStateException();
		}
	}

	public static Customer isLoggedIn(HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		if (customer == null)
			return null;
		return customer;
	}

	public static String isAdminLoggedIn(HttpServletRequest request, HttpServletResponse response) {

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = determineTargetUrl(authorities);
		System.out.println("target for returning page !!!" + target);
		if (target.equals("/user/showUser")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}

		return "";
	}
}
