package com.onlineshopping.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlineshopping.entity.Order;
import com.onlineshopping.entity.OrderProduct;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Order> getOrders() {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("from Order", Order.class);
		List<Order> orders = query.list();
		System.out.println("orders from orders DAO : " + orders.get(0).getStatus() + " total price: "
				+ orders.get(0).getTotalPrice());
		return orders;
	}

	@Override
	public List<Order> findByOrderStatus(String orderStatus) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query;
		if (orderStatus.isEmpty()) {
			query = session.createQuery("From Order", Order.class);
		} else {
			query = session.createQuery("From Order as r where r.status like '%" + orderStatus + "%'", Order.class);
			// query.setParameter("status", orderStatus);
		}

		List<Order> orders = query.list();
		System.out.println("orders from orders findByOrderName DAO : " + orders);
		return orders;
	}

	@Override
	public Order findOrderById(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Order> query = session.createQuery("From Order as r where r.id=:orderId", Order.class);
		query.setParameter("orderId", orderId);
		return query.getSingleResult();
	}

	@Override
	public void update(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
	}

	@Override
	public Order createOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
		return order;
	}

	@Override
	public void createOrderProduct(OrderProduct orderProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderProduct);
	}

}
