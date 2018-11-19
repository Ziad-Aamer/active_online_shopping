package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.common.Commons;
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

	Commons common = new Commons();

	@Autowired
	public MainController(DataInitilizerBean dataInitilizerBean) {
		this.beanInitilizer = dataInitilizerBean;
	}

	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@GetMapping("/")
	public String gotoIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Category> categoreis = beanInitilizer.getCategoriesEAGER();

		List<Product> products = new ArrayList<>();
		for (Category category : categoreis) {

			for (SubCategory subCategory : category.getSubCategories()) {

				products.addAll(subCategory.getProducts());
			}
		}

		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = common.determineTargetUrl(authorities);

		if (target.equals("/orders/list")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}

		if (customer != null) {
			Cart cart = customer.getCart();
			List<CartProduct> cp = cartService.getProducts(cart.getId());
			model.addAttribute("cartList", cp.size());
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
