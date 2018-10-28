package com.onlineshopping.service;

import com.onlineshopping.entity.Cart;

public interface CartService {

    void addProduct(Cart cart, int productId);

}
