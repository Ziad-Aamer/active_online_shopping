package com.onlineshopping.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.CartDao;
import com.onlineshopping.dao.ProductDao;
import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.CartProductId;
import com.onlineshopping.entity.Product;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;
    
    @Transactional
    public void addProduct(Cart cart, int productId) {
	
	Product product = productDao.getProduct(productId);
	
	CartProduct cartProduct = cartDao.getCartProduct(cart, product);
	
	System.out.println(cartProduct);
	if(cartProduct == null) {   
	    cartProduct = new CartProduct(cart, product);
	    cartProduct.setCartProductId(new CartProductId(cart,product));
//	    cartProduct.setCart(cart);
//	    cartProduct.setProduct(product);
	}
	
	cartProduct.setQunatity(cartProduct.getQunatity() + 1);
	cartDao.addCartProduct(cartProduct);
	
	System.out.println(cartProduct);
	
    }

}
