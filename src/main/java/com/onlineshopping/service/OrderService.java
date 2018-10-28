package com.onlineshopping.service;

import java.util.List;

import com.onlineshopping.entity.Order;

public interface OrderService {

	public List<Order> getOrders();

	public List<Order> findByOrderStatus(String orderStatus);

	public Order findOrderById(int orderId);

	public void update(Order order);
}
