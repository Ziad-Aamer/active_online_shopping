package com.onlineshopping.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProduct {

	@EmbeddedId
	private OrderProductId id;
	private double price;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId("product_id")
	private Product product;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderProduct() {
	}

	public OrderProduct(Order order, Product product) {
		this.order = order;
		this.product = product;
	}

	public OrderProduct(double price, Order order, Product product) {
		this.price = price;
		this.order = order;
		this.product = product;
	}

	public OrderProductId getId() {
		return id;
	}

	public void setId(OrderProductId id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
