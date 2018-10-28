package com.onlineshopping.dao;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Product;

public interface CartDao {
    
    public CartProduct getCartProduct(Cart cart, Product product);
    public CartProduct addCartProduct(CartProduct cp);
}
