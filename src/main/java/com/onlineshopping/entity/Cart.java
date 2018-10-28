package com.onlineshopping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//cart size
	@Column(name = "number_of_products")
	private int totalNumberOfProducts;

	@Column(name = "total_price")
	private double totalPrice;

	@OneToOne(mappedBy = "cart", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private Customer customer;

	
	@OneToMany(mappedBy="cart",
		cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<CartProduct> products;
	
	public Cart() {}
	
	public Cart(int totalNumberOfProducts, double totalPrice) {
		this.totalNumberOfProducts = totalNumberOfProducts;
		this.totalPrice = totalPrice;
	}
	
	public void addCartProduct(CartProduct cartProduct) {
	    if(products==null)
		products = new ArrayList<>();
	    
	    products.add(cartProduct);
	    
	   // cartProduct.setCart(this);
	}

	public int getTotalNumberOfProducts() {
		return totalNumberOfProducts;
	}

	public void setTotalNumberOfProducts(int totalNumberOfProducts) {
		this.totalNumberOfProducts = totalNumberOfProducts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartProduct> getProducts() {
	    return products;
	}

	public void setProducts(List<CartProduct> products) {
	    this.products = products;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
	    return "Cart [id=" + id + ", totalNumberOfProducts=" + totalNumberOfProducts + ", totalPrice=" + totalPrice
		    + "]";
	}
	
}
