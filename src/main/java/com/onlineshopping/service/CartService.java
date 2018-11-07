package com.onlineshopping.service;

import java.util.List;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;

public interface CartService {

	void addProduct(Cart cart, int productId);

	void removeProduct(Cart cart, int productId);

	List<CartProduct> getProducts(int cartId);

	public CartProduct updateCartProduct(CartProduct cp);
}
