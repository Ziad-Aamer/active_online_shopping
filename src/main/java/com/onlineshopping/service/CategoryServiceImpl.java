package com.onlineshopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.CategoryDao;
import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.SubCategory;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Transactional
	@Override
	public List<Category> getCategoriesLAZY() {

		return categoryDao.getCategories();
	}
	
	@Transactional
	@Override
	public List<Category> getCategoriesEAGER() {
	    
	    	List<Category> cats = categoryDao.getCategories();
	    	
	    	for(Category c:cats) {
	    	    List<SubCategory> subCats = c.getSubCategories();
	    	    System.out.println("All sub-Cat for Category: " + c.getCategoryName() + " =>" + subCats);
	    	    for(SubCategory sub: subCats) {
	    		System.out.println("All Products for Sub-Cat: " + 
	    				sub.getName() + " =>" + sub.getProducts());
	    	    }
	    	}
	    	    
		return cats;
	}
	
}
