package com.onlineshopping.service;

import com.onlineshopping.entity.Address;
import com.onlineshopping.entity.Customer;

public interface CustomerService {

	public void addCustomer(Customer customer);

	public Customer getCustomer(String email);

	public void addCustomerAddress(int customerId, Address address);

	public void deleteCustomer(String email);
}
