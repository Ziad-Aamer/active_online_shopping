package com.onlineshopping.dao;

import com.onlineshopping.entity.Address;
import com.onlineshopping.entity.Customer;

public interface CustomerDao {

	public void addCustomer(Customer customer);

	public Customer getCustomer(String email);

	public void addCustomerAddress(int customerId, Address address);

}
