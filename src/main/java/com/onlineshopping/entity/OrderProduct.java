package com.onlineshopping.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="order_product")
public class OrderProduct {
	
	@EmbeddedId
	private OrderProductId id; 
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@MapsId("order_id")
    private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@MapsId("product_id")
    private Product product;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
