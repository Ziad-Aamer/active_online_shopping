package com.onlineshopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "customer_id")
	private List<Address> addresses;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<Order> orders;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "cart_id")
	private Cart cart;

	Customer() {
	}

	public void addOrder(Order order) {
		if (orders == null)
			orders = new ArrayList<>();
		orders.add(order);
		order.setCustomer(this);
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	};

	public void addAddress(Address address) {
		if (addresses == null)
			addresses = new ArrayList<>();
		addresses.add(address);

	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
