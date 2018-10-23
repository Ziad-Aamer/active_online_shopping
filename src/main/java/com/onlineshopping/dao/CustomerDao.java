package com.onlineshopping.dao;

import com.onlineshopping.entity.Customer;

public interface CustomerDao {
	
	public void addCustomer(Customer customer);

	public Customer getCustomer(String email);
}
