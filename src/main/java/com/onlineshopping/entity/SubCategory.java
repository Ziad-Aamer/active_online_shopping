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

}
