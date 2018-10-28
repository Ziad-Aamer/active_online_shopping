package com.onlineshopping.dao;

import java.util.List;

import com.onlineshopping.entity.Order;

public interface OrderDao {

	public List<Order> getOrders();

	public List<Order> findByOrderStatus(String orderStatus);

	public Order findOrderById(int orderId);

	public void update(Order order);
}
