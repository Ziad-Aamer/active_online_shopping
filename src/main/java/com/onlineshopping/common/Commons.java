package com.onlineshopping.common;

import java.util.List;

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
}
