package com.onlineshopping.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="cart_product")
public class CartProduct {
    
    
    @EmbeddedId
    private CartProductId cartProductId;
    private int quantity;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @MapsId("cart_id")
    private Cart cart;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    @MapsId("product_id")
    private Product product;

    
    public CartProduct() {

    }

    public CartProduct(Cart cart, Product product) {
	super();
	this.cart = cart;
	this.product = product;
	this.quantity = 0;
    }
    
    public CartProductId getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(CartProductId cartProductId) {
        this.cartProductId = cartProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
	return "CartProduct [cartProductId=" + cartProductId + ", quantity=" + quantity + "]";
    }
    
    

}
