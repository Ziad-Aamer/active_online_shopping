package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.entity.Product;
import com.onlineshopping.entity.SubCategory;
import com.onlineshopping.service.CartService;

@Controller
public class MainController {

	// @Autowired
	private DataInitilizerBean beanInitilizer;

	@Autowired
	CartService cartService;

	@Autowired

	public MainController(DataInitilizerBean dataInitilizerBean) {
		this.beanInitilizer = dataInitilizerBean;
	}

	@GetMapping("/")
	public String gotoIndex(HttpServletRequest request, Model model) {

		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		Cart cart = customer.getCart();

		List<Category> categoreis = beanInitilizer.getCategoriesEAGER();

		List<Product> products = new ArrayList<>();
		for (Category category : categoreis) {

			for (SubCategory subCategory : category.getSubCategories()) {

				products.addAll(subCategory.getProducts());
			}
		}

		List<CartProduct> cp = cartService.getProducts(cart.getId());

		model.addAttribute("cartList", cp.size());
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
