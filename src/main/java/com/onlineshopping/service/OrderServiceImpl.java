package com.onlineshopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.OrderDao;
import com.onlineshopping.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Transactional
	@Override
	public List<Order> getOrders() {

		return orderDao.getOrders();
	}

	@Transactional
	@Override
	public List<Order> findByOrderStatus(String orderStatus) {

		return orderDao.findByOrderStatus(orderStatus);
	}

	@Transactional
	@Override
	public Order findOrderById(int orderId) {
		return orderDao.findOrderById(orderId);
	}

	@Transactional
	@Override
	public void update(Order order) {
		orderDao.update(order);
	}
}
