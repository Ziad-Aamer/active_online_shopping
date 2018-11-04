package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.Product;
import com.onlineshopping.entity.SubCategory;

@RequestMapping("/product")
@Controller
public class ProductController {

	@Autowired
	private DataInitilizerBean beanInitializer;

	@GetMapping("/search")
	public String search(@RequestParam("searchProducts") String searchProducts, @RequestParam String categoryName,
			Model model) {

		List<Category> selectedCategories = new ArrayList<>();
		// get selected category from list for search..
		if (!categoryName.isEmpty()) {
			selectedCategories = beanInitializer.getCategoriesEAGER().stream()
					.filter(item -> item.getCategoryName().equals(categoryName)).collect(Collectors.toList());
		} else {
			selectedCategories = beanInitializer.getCategoriesEAGER();
		}

		List<Product> products = new ArrayList<>();
		for (Category category : selectedCategories) {

			for (SubCategory subCategory : category.getSubCategories()) {
				if (searchProducts.isEmpty()) {
					products.addAll(subCategory.getProducts());
				} else {
					products.addAll(subCategory.getProducts().stream()
							.filter(item -> item.getName().toLowerCase().contains(searchProducts.toLowerCase()))
							.collect(Collectors.toList()));
				}
			}
		}

		List<Category> categoreis = beanInitializer.getCategoriesEAGER();
		model.addAttribute("category", new Category());
		model.addAttribute("categories", categoreis);
		model.addAttribute("products", products);

		return "index";
	}

	@GetMapping("/filterByPrice")
	public String filterByPrice(@RequestParam("price-min") double minPrice, @RequestParam("price-max") double maxPrice,
			Model model) {
		System.out.println("inside filter by price :" + minPrice + "  max price : " + maxPrice);

		List<Category> allCategories = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		allCategories = beanInitializer.getCategoriesEAGER();

		for (Category category : allCategories) {

			for (SubCategory subCategory : category.getSubCategories()) {

				products.addAll(subCategory.getProducts().stream()
						.filter(item -> item.getPrice() >= minPrice && item.getPrice() <= maxPrice)
						.collect(Collectors.toList()));
			}
		}

		model.addAttribute("category", new Category());
		model.addAttribute("categories", allCategories);
		model.addAttribute("products", products);
		return "index";
	}
}
