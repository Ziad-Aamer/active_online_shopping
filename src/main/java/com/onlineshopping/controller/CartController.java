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

	@Autowired
	private CartService cartService;

	List<CartProduct> listOfProducts;

	// get the user cart
	// get the cart products from the join table
	// add the product to the model as form
	@GetMapping("/showCart")
	public String showCart(Model theModel, HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		System.out.println("current user showCart: " + customer);

		if (customer == null)
			return "redirect:/";

		Cart cart = customer.getCart();
		listOfProducts = new ArrayList<>();
		listOfProducts = cartService.getProducts(cart.getId());
		cpm = new CartProductCm(listOfProducts);

		System.out.println("all products in cart product : " + cpm.getListOfProducts());

		for (CartProduct cp : cpm.getListOfProducts()) {
			System.out.println("cart product id !!!! " + cp.getCartProductId());
			System.out.println(cp.getCart());
			System.out.println(cp.getProduct());
			System.out.println(cp.getQuantity() + "\n\n");
		}

		// work here
		theModel.addAttribute("cpc", cpm);

		return "cart";
	}

	@PostMapping("/removeProduct/{productId}")
	public String removeProduct(@PathVariable int productId, HttpServletRequest request) {

		System.out.println("product id in cart controller to deleteeeeeeeeeeeeeeeeee : " + productId);
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
		System.out.println(cart);

		cartService.addProduct(cart, productId);

		System.out.println("Product with id: " + productId + " Added");

		return "redirect:/";
	}

	@PostMapping("updateCart")
	public String updateCartProducts(@ModelAttribute("cpc") CartProductCm cpc, HttpServletRequest request,
			Model theModel) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		System.out.println("all updated products: " + cpc.getListOfProducts());
		if (customer == null)
			return "redirect:/";
		Cart cart = customer.getCart();

		if (cpc.getListOfProducts() != null) {
			for (CartProduct cartProduct : cpc.getListOfProducts()) {
				System.out.println("each product in specific cart : " + cartProduct.getProduct());
				CartProductId cpId = new CartProductId(cart, cartProduct.getProduct());
				cartProduct.setCartProductId(cpId);
				cartService.updateCartProduct(cartProduct);
			}

			theModel.addAttribute("cpc", cpc);
		}
		return "cart";
	}

	public List<CartProduct> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<CartProduct> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

}
