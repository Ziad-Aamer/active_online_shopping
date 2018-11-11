package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlineshopping.entity.Address;
import com.onlineshopping.entity.CartProduct;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.entity.Order;
import com.onlineshopping.service.CartService;
import com.onlineshopping.service.CustomerService;
import com.onlineshopping.service.OrderService;

@RequestMapping("checkout")
@Controller
public class CheckOutController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	private List<Address> addresses;
	private Address selectedAddress;
	private String addressId;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Address getSelectedAddress() {
		return selectedAddress;
	}

	public void setSelectedAddress(Address selectedAddress) {
		this.selectedAddress = selectedAddress;
	}

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		if (customer == null)
			return "redirect:/";

		customer = customerService.getCustomer(customer.getEmail());

		addresses = new ArrayList<>();
		addresses.addAll(customer.getAddresses());

		model.addAttribute("address", new Address());
		model.addAttribute("addresses", addresses);
		return "checkout";
	}

	@PostMapping("createOrder")
	public String createOrder(@ModelAttribute("address") Address address, HttpServletRequest request, Model model) {
		Customer customer = (Customer) request.getSession().getAttribute("loggedinUser");
		if (customer == null) {
			return "redirect:/";
		}

		Order order = new Order();

		customer = customerService.getCustomer(customer.getEmail());

		List<Address> targetAddress = customer.getAddresses().stream().filter(item -> item.getId() == address.getId())
				.collect(Collectors.toList());

		System.out.println("target Address: " + targetAddress.get(0));
		order.setCustomer(customer);
		order.setAddress(targetAddress.get(0));
		List<CartProduct> cartProducts = cartService.getProducts(customer.getCart().getId());

		System.out.println("cart products:::::: " + cartProducts);

		order.setTotalPrice(calculateTotalPrice(cartProducts));
		orderService.createOrder(cartProducts, order);
		cartService.removeCartProducts(cartProducts);
		System.out.println("saved Successfully!!! : ");
		return "redirect:/";
	}

	private double calculateTotalPrice(List<CartProduct> cartProducts) {
		double totalPrice = 0;
		for (CartProduct cartProduct : cartProducts) {
			totalPrice += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
		}
		return totalPrice;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
