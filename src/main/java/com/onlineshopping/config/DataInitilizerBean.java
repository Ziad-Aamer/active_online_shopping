package com.onlineshopping.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineshopping.entity.Category;
import com.onlineshopping.service.CategoryService;

@Component
public class DataInitilizerBean {
    
    @Autowired
    private CategoryService categoryService;
    
    
    private List<Category> allCats=null;
	
    public List<Category> getCategoriesEAGER() {
	
	if(allCats!=null)
	    return allCats;
	
	allCats = categoryService.getCategoriesEAGER();
	System.out.println("All Categoriessssss Initilized: \n\n");

	return allCats;
    }
    
}
