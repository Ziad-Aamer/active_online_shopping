package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.Product;
import com.onlineshopping.entity.SubCategory;

@Controller
public class MainController {

	@Autowired
	private DataInitilizerBean beanInitilizer;

	@RequestMapping("/")
	public String gotoIndex(Model model) {

		List<Category> categoreis = beanInitilizer.getCategoriesEAGER();

		List<Product> products = new ArrayList<>();
		for (Category category : categoreis) {

			for (SubCategory subCategory : category.getSubCategories()) {

				products.addAll(subCategory.getProducts());
			}
		}
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoreis);
		model.addAttribute("products", products);
		return "index";
	}

	@RequestMapping("/pageNotFound")
	public String pageNotFound() {

		return "notfound-404";
	}
}
