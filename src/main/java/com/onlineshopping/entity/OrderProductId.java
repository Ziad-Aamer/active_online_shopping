package com.onlineshopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderProductId implements Serializable{

	@Column(name="order_id")
	private int orderId;
	@Column(name="product_id")
	private int productId;
	
	
	
	public OrderProductId() {
	
	}

	public OrderProductId(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}



	private static final long serialVersionUID = 1L;
	
}
