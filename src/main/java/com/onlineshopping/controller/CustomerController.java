package com.onlineshopping.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshopping.entity.Customer;
import com.onlineshopping.service.CustomerService;
import com.onlineshopping.service.UserService;
import com.onlineshopping.social.SocialContext;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private SocialContext socialContext;

	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;

	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showRegistratonForm(Model theModel) {

		theModel.addAttribute("customer", new Customer());
		// need to update the name of the page returned
		return "customer-registration";
	}

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public String showPostsForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println(
				"in online shopping project inside showPostsForUser postsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

		String nextView;

		if (socialContext.isSignedIn(request, response)) {
			System.out.println("\n==============>logged in Cool!");

			// List<Post> posts = retrievePosts();
			// model.addAttribute("posts", posts);
			nextView = "show-posts";
		} else {
			System.out.println("\n==============> not logged in yet ");
			nextView = "signin";
		}
		Facebook facebook = socialContext.getFacebook();
		String[] fields = { "id", "email", "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		logger.info("\n==========> email facebook: " + userProfile.getEmail() + " firstName: "
				+ userProfile.getFirstName());
		return nextView;
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("customer") Customer customer,
			BindingResult theBindingResult, Model theModel) {

		String email = customer.getEmail();

		logger.info("Processing registration form for: " + email);

		// form validation
		// update the validation error messages for the wrong email and password format
		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("customer", new Customer());
			theModel.addAttribute("registrationError", "email/password must be valid.");

			logger.warning("Email/password must be valid.");

			return "customer-registration";
		}

		logger.info("Customer validated: " + email);

		// call addUser function to add the customer into users table
		boolean userExists = userService.addUser(customer, "CUSTOMER");

		if (userExists) {
			theModel.addAttribute("customer", new Customer());
			theModel.addAttribute("registrationError", "Email already exists.");

			logger.warning("Email already exists.");

			return "customer-registration";
		}

		logger.info("Successfully created user: " + email);

		// add Customer to DB
		customerService.addCustomer(customer);
		return "redirect:/user/showMyLoginPage";

	}

}
