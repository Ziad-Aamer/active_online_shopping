package com.onlineshopping.dao;

import java.util.List;

import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.OrderProduct;

public interface OrderDao {

	public Order createOrder(Order order);

	public List<Order> getOrders();

	public List<Order> findByOrderStatus(String orderStatus);

	public Order findOrderById(int orderId);

	public void update(Order order);

	public void createOrderProduct(OrderProduct orderProduct);
}
