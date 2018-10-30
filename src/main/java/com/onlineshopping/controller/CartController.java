package com.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    //get the user cart 
    //get the cart products from the join table
    //add the product to the model as form
    @GetMapping("/showCart")
    public String showCart(Model theModel, HttpServletRequest request) {
	Customer customer = 
		(Customer) request.getSession().getAttribute("loggedinUser");
	if(customer==null)
	    return "redirect:/";
	
	Cart cart = customer.getCart();
	List<CartProduct> listOfProducts = cartService.getProducts(cart.getId());
	for(CartProduct cp: listOfProducts) {
	  System.out.println(cp.getCart());
	  System.out.println(cp.getProduct());
	  System.out.println(cp.getQuantity() +"\n\n");
	}
	
	//work here
	theModel.addAttribute("ListOfCartProducts", listOfProducts);
	
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
