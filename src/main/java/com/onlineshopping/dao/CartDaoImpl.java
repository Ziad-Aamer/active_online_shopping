package com.onlineshopping.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.CartProductId;
import com.onlineshopping.entity.Product;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory factory;
    
    
    public CartProduct getCartProduct(Cart cart, Product product) {
	
	Session session = factory.getCurrentSession();
	
	CartProductId id = new CartProductId(cart, product);
	CartProduct cartProduct= session.get(CartProduct.class, id);
	
	return cartProduct;
    }


    @Override
    public CartProduct addCartProduct(CartProduct cp) {
	
	Session session = factory.getCurrentSession();
	session.saveOrUpdate(cp);
	
	return cp;
    }

    
    @Override
    public List<CartProduct> getProducts(int cartId) {
	Session session = factory.getCurrentSession();
	
	Cart persistCart = session.get(Cart.class, cartId);
	List<CartProduct> products = persistCart.getProducts();
	System.out.println(products);
	return products;
    }

}
