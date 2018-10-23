package com.onlineshopping.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	public void addCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		session.persist(customer);
		
		System.out.println("Customer added with id:" + customer.getId());
	}
	
	@Override
	public Customer getCustomer(String email) {
	    
    	    Session session = sessionFactory.getCurrentSession();
    	    Customer customer = null;
    	    try {
    		customer =session.createQuery("from Customer c where c.email=:email",Customer.class)
    	    		.setParameter("email", email).getSingleResult();
    	    }catch(NoResultException e) {
    		
    	    }
    	    System.out.println(customer);
    	    return customer;
	}
}
