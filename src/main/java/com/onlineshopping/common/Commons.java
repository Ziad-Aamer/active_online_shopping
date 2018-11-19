package com.onlineshopping.common;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.onlineshopping.entity.CartProduct;

public class Commons {

	public Commons() {
	}

	public double calculateTotalPrice(List<CartProduct> cartProducts) {
		double totalPrice = 0;
		for (CartProduct cartProduct : cartProducts) {
			totalPrice += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
		}
		return totalPrice;
	}

	public int getProductsQuantity(List<CartProduct> cartProducts) {
		int quantity = 0;
		for (CartProduct cartProduct : cartProducts) {
			quantity += cartProduct.getQuantity();
		}
		return quantity;
	}

	public String determineTargetUrl(Collection<? extends GrantedAuthority> authorities) {
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
}
