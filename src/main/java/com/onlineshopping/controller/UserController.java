package com.onlineshopping.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
	public String redirectUserAfterLogin(Principal principal, HttpServletRequest request) {
	    
	    if(principal==null)
		return "redirect:/";
	   //redirect based on role
	   User user = (User) request.getSession().getAttribute("loggedinUser");
	       
	   user = userService.getUser(principal.getName());
	   
	   request.getSession().setAttribute("loggedinUser",user);
	   
	   System.out.println("is he a Customer " + (user instanceof Customer));
	   System.out.println("is he an Admin " + (user instanceof Admin));
	   
	   if(user instanceof Customer)
	  	return "redirect:/";
	   else if(user instanceof Admin)
	       return "admin-manageOrders";
	   
	   return "redirext:/pageNotFound";
	}
	
	@GetMapping(value="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/user/showMyLoginPage?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

}
