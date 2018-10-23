package com.onlineshopping.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.entity.Admin;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.entity.User;
import com.onlineshopping.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    	
    	@Autowired
    	private UserService userService;
    	    
	@GetMapping("/showMyLoginPage")
	public String showLoginPage() {

    	    return "user-login";
	}
	
	
	@GetMapping("/showUser")
	public String redirectUserAfterLogin(Principal principal) {

	   System.out.println("Principal:toStirng ==>" + principal.getName());
	   //redirect based on role
	   User user = userService.getUser(principal.getName());
	   
	   System.out.println("is he a Customer " + (user instanceof Customer));
	   System.out.println("is he an Admin " + (user instanceof Admin));
	   if(user instanceof Customer)
	  	return "index";
	   else if(user instanceof Admin)
	       return "admin-manageOrders";
	   
	   return "notfound-404";
	}

}
