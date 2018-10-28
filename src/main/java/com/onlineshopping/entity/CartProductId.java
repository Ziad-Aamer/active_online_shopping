package com.onlineshopping.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CartProductId implements Serializable{

    private static final long serialVersionUID = 1L;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JoinColumn(name="cart_id")
    private Cart cart;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    
    
    public CartProductId() {

    }

    public CartProductId(Cart cart, Product product) {
	super();
	this.cart = cart;
	this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
	return "CartProductId [cart=" + cart + ", product=" + product + "]";
    }
    
    
	
}
