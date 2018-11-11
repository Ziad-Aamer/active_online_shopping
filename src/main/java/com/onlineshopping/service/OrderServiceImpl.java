package com.onlineshopping.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.OrderDao;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.OrderProduct;
import com.onlineshopping.entity.OrderProductId;

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

	@Transactional
	@Override
	public void createOrder(List<CartProduct> cartProducts, Order order) {

		order.setStatus("pending");
		order.setTimestamp(new Date());
		Order createdOrder = orderDao.createOrder(order);
		for (CartProduct cartProduct : cartProducts) {
			OrderProductId opId = new OrderProductId(createdOrder.getId(), cartProduct.getProduct().getId());
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setId(opId);
			orderProduct.setOrder(createdOrder);
			orderProduct.setProduct(cartProduct.getProduct());
			orderProduct.setPrice(cartProduct.getProduct().getPrice());
			orderDao.createOrderProduct(orderProduct);
		}
	}
}
