package com.onlineshopping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double price;
	private String description;
	private String url;
	
	@OneToMany(mappedBy = "product"
			,cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<OrderProduct> orders;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;

	@OneToMany(mappedBy = "product"
		,cascade={CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<CartProduct> carts;
	
	public Product() {

	}

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<OrderProduct> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderProduct> orders) {
		this.orders = orders;
	}

	public List<CartProduct> getCarts() {
	    return carts;
	}

	public void setCarts(List<CartProduct> carts) {
	    this.carts = carts;
	}

	public String getUrl() {
	    return url;
	}

	public void setUrl(String url) {
	    this.url = url;
	}

	@Override
	public String toString() {
	    return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}

	
}
