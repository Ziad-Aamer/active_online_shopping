package com.onlineshopping.service;

import java.util.List;

import com.onlineshopping.entity.Category;

public interface CategoryService {

	public List<Category> getCategoriesLAZY();

	public List<Category> getCategoriesEAGER();
}
