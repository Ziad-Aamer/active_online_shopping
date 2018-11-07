package com.onlineshopping.dao;

import java.util.List;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.CartProductId;
import com.onlineshopping.entity.Product;

public interface CartDao {

	public CartProduct getCartProduct(Cart cart, Product product);

	public CartProduct addCartProduct(CartProduct cp);

	public CartProduct updateCartProduct(CartProduct cp);

	public void removeCartProduct(CartProductId id);

	public List<CartProduct> getProducts(int cartId);
}
