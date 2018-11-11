package com.onlineshopping.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.CustomerDao;
import com.onlineshopping.entity.Address;
import com.onlineshopping.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Transactional
	@Override
	public void addCustomer(Customer customer) {

		customerDao.addCustomer(customer);
	}

	@Transactional
	@Override
	public void addCustomerAddress(int customerId, Address address) {
		customerDao.addCustomerAddress(customerId, address);
	}

	@Transactional
	@Override
	public Customer getCustomer(String email) {
		return customerDao.getCustomer(email);
	}

}
