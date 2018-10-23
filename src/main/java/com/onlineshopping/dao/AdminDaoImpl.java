package com.onlineshopping.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Admin;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Admin getAdmin(String email) {
	    Session session = sessionFactory.getCurrentSession();
	    Admin admin = null;
	    
	    try {
		admin = session.createQuery("from Admin a where a.email=:email",Admin.class)
				.setParameter("email", email).getSingleResult();
		
	    } catch (Exception e) {
	    }
	    
	    return admin;
    }

}
