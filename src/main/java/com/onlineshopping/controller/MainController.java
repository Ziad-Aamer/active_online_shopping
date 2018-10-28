package com.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.entity.Category;

@Controller
public class MainController {
    
    @Autowired
    private DataInitilizerBean beanInitilizer;
    
    @RequestMapping("/")
    public String gotoIndex(Model model) {
	
	List<Category> categoreis = beanInitilizer.getCategoriesEAGER();
	
//	List<String> catNames = new ArrayList<>();
//	for(Category cat : categoreis)
//	    catNames.add(cat.getCategoryName());
//	
//	System.out.println("All Categoriessssss: " + categoreis);
	model.addAttribute("categories", categoreis);
	
	return "index";
    }
    
    @RequestMapping("/pageNotFound")
    public String pageNotFound() {
	
	return "notfound-404";
    }
}
