package com.onlineshopping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sub_category")
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
	private List<Product> products;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.DETACH
				,CascadeType.MERGE,CascadeType.REFRESH} )
	@JoinColumn(name="category_id")
	private Category category;

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

	public List<Product> getProducts() {
	    return products;
	}

	public void setProducts(List<Product> products) {
	    this.products = products;
	}

	public Category getCategory() {
	    return category;
	}

	public void setCategory(Category category) {
	    this.category = category;
	}

	@Override
	public String toString() {
	    return "SubCategory [id=" + id + ", name=" + name + "]";
	}

	
}
