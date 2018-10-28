package com.onlineshopping.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory factory;
    
    @Override
    public Product getProduct(int productId) {
	
	Session session = factory.getCurrentSession();	
	
	return session.get(Product.class, productId);
    }

}
