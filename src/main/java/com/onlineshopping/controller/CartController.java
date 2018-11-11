package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.CartProductCm;
import com.onlineshopping.entity.CartProductId;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private CartProductCm cpm;
	private double totalPrice;

	@Autowired
	private CartService cartService;

	List<CartProduct> listOfProducts;

	// get the user cart
	// get the cart products from the join table
	// add the product to the model as form
	@GetMapping("/showCart")
	public String showCart(Model theModel, HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");

		if (customer == null)
			return "redirect:/";

		Cart cart = customer.getCart();
		listOfProducts = new ArrayList<>();
		listOfProducts = cartService.getProducts(cart.getId());
		cpm = new CartProductCm(listOfProducts);

		// work here
		theModel.addAttribute("cpc", cpm);

		return "cart";
	}

	@PostMapping("/removeProduct/{productId}")
	public String removeProduct(@PathVariable int productId, HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		Cart cart = customer.getCart();
		cartService.removeProduct(cart, productId);
		return "redirect:/cart/showCart";
	}

	@GetMapping("/addProduct/{productId}")
	public String addProduct(@PathVariable int productId, HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		if (customer == null)
			return "redirect:/";
		Cart cart = customer.getCart();

		cartService.addProduct(cart, productId);

		return "redirect:/";
	}

	@PostMapping("updateCart")
	public String updateCartProducts(@ModelAttribute("cpc") CartProductCm cpc, HttpServletRequest request,
			Model theModel) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		if (customer == null)
			return "redirect:/";
		Cart cart = customer.getCart();

		if (cpc.getListOfProducts() != null) {

			double totalPrice = calculateTotalPrice(cpc.getListOfProducts());
			int quantity = getProductsQuantity(cpc.getListOfProducts());

			cart.setTotalPrice(totalPrice);
			cart.setTotalNumberOfProducts(quantity);

			cartService.updateCart(cart);

			for (CartProduct cartProduct : cpc.getListOfProducts()) {
				CartProductId cpId = new CartProductId(cart, cartProduct.getProduct());
				cartProduct.setCartProductId(cpId);
				cartService.updateCartProduct(cartProduct);
			}

			theModel.addAttribute("cpc", cpc);
		}
		return "cart";
	}

	private double calculateTotalPrice(List<CartProduct> cartProducts) {
		double totalPrice = 0;
		for (CartProduct cartProduct : cartProducts) {
			totalPrice += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
		}
		return totalPrice;
	}

	private int getProductsQuantity(List<CartProduct> cartProducts) {
		int quantity = 0;
		for (CartProduct cartProduct : cartProducts) {
			quantity += cartProduct.getQuantity();
		}
		return quantity;
	}

	public List<CartProduct> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<CartProduct> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
