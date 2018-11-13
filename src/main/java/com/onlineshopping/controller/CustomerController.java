package com.onlineshopping.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineshopping.entity.Address;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.service.CustomerService;
import com.onlineshopping.service.EmailService;
import com.onlineshopping.service.UserService;

@Controller
@RequestMapping("/customer")
@PropertySource("classpath:application.properties")
public class CustomerController {

	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailService emailService;

	@Autowired
	private Environment env;

	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("showNewAddressForm")
	public String addNewAddress(Model model) {
		model.addAttribute("address", new Address());
		return "address";
	}

	@PostMapping("addAddress")
	public String addNewAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");

		if (customer == null)
			return "redirect:/";

		if (bindingResult.hasErrors()) {
			System.out.println("inside binding result!!!!!!");
			model.addAttribute("address", new Address());
			model.addAttribute("FieldsRequired", "All Fields required!");
			return "address";
		}

		customerService.addCustomerAddress(customer.getId(), address);

		return "redirect:/checkout/";
	}

	@GetMapping("/showRegistrationForm")
	public String showRegistratonForm(Model theModel) {

		theModel.addAttribute("customer", new Customer());
		// need to update the name of the page returned
		return "customer-registration";
	}

	@GetMapping("/showForgetPasswordForm")
	public String showForgetPasswordForm(Model theModel) {

		theModel.addAttribute("customer", new Customer());
		// need to update the name of the page returned
		return "forgot-password";
	}

	@GetMapping("/forgotPassword")
	public String forgotPassword(HttpServletRequest request, @RequestParam("email") String email, Model model) {

		String path = env.getProperty("host") + request.getContextPath() + "/customer/newPasswordForm?email=" + email;

		boolean exist = userService.doesUserExists(email);
		if (!exist) {
			model.addAttribute("emailNotExist", "This email not Exist!");
			model.addAttribute("customer", new Customer());
			return "forgot-password";
		} else {

			String url = "please click the link below to continue your process: \n\n\n <a href='" + path
					+ "'>Create New Password</a>";

			emailService.sendMimeMessage(email, "Confirmation Email", url);
			logger.info("email sent!!!!");
		}
		return "check-email";
	}

	@GetMapping("/newPasswordForm")
	public String createNewPasswordForm(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		return "new-password";
	}

	@GetMapping("/updatePassword")
	public String createNewPassword(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, Model model) {
		System.out.println(
				"password and confirm password : " + password + " confirm: " + confirmPassword + "  email : " + email);
		if (!password.equals(confirmPassword)) {
			model.addAttribute("notMatch", "password dosen't matches!!");
			model.addAttribute("email", email);
			return "new-password";
		} else {
			userService.updatePassword(email, password);
		}
		return "user-login";
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

	@GetMapping("FbUserProfile")
	public String getFbUserProfile(@RequestParam String email, @RequestParam String first_name,
			@RequestParam String last_name, Model theModel) {
		Customer c = new Customer();
		c.setEmail(email);
		c.setFirstName(first_name);
		c.setLastName(last_name);

		theModel.addAttribute("customer", c);
		theModel.addAttribute("registrationError", "Enter your password.");

		logger.warning("Email/password must be valid.");

		return "customer-registration";

	}

	@GetMapping("delete")
	public void deleteCustomerForTesting(@RequestParam("email") String email) {
		System.out.println("inside customer controller , delete customer method : " + email);
		customerService.deleteCustomer(email);
		userService.deleteUser(email);
	}

}
