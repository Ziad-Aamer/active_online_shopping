package com.onlineshopping.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<SubCategory> subCategories;

	@Override
	public String toString() {
	    return "Category [id=" + id + ", categoryName=" + categoryName + "]";
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getCategoryName() {
	    return categoryName;
	}

	public void setCategoryName(String categoryName) {
	    this.categoryName = categoryName;
	}

	public List<SubCategory> getSubCategories() {
	    return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
	    this.subCategories = subCategories;
	}
	
	
}
