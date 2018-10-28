package com.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/showCart")
    public String showCart() {
	
	return "cart";
    }
    
    @GetMapping("/addProduct/{productId}")
    public String addProduct(@PathVariable int productId, HttpServletRequest request) {
	
	Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
	if(customer==null)
	    return "redirect:/";
	Cart cart = customer.getCart();
	System.out.println(cart);
	
	cartService.addProduct(cart, productId);
	
	
	
	System.out.println("Product with id: " + productId + " Added");
	
	return "redirect:/";
    }
}
