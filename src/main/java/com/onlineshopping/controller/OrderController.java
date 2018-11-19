package com.onlineshopping.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineshopping.common.Commons;
import com.onlineshopping.entity.Order;
import com.onlineshopping.service.OrderService;

@RequestMapping("/orders")
@Controller
public class OrderController {

	private String selectedOrderStatus;
	private List<String> ordersStatus;
	@Autowired
	private OrderService orderService;
	Commons common = new Commons();

	public void OrdersStatus() {
		ordersStatus = new ArrayList<String>();
		ordersStatus.add("pending");
		ordersStatus.add("inProgress");
		ordersStatus.add("delivered");
	}

	@GetMapping("/list")
	public String getOrders(Model model, HttpServletRequest request, HttpServletResponse response) {

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = common.determineTargetUrl(authorities);

		if (target.equals("/user/showUser")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}

		OrdersStatus();
		List<Order> orders = orderService.getOrders();
		System.out.println("\n=========> All orders From DB: " + orders);
		model.addAttribute("order", new Order());
		model.addAttribute("orders", orders);
		model.addAttribute("ordersStatus", ordersStatus);
		return "admin-orders";
	}

	@GetMapping("/search")
	public String search(@RequestParam("orderStatusSearch") String orderStatusSearch, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = common.determineTargetUrl(authorities);

		if (target.equals("/user/showUser")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}
		System.out.println("\n============>input Search textBox : " + orderStatusSearch);
		List<Order> orders = orderService.findByOrderStatus(orderStatusSearch);
		model.addAttribute("orders", orders);

		return "admin-orders";
	}

	@RequestMapping("/showFormUpdate")
	public String showFormUpdate(@RequestParam("orderId") int orderId, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = common.determineTargetUrl(authorities);

		if (target.equals("/user/showUser")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}
		System.out.println("inside updateOrderStatus !!!!!!!!!!!!!!!!!!!!!!!!!!!" + orderId);
		Order order = orderService.findOrderById(orderId);
		OrdersStatus();
		System.out.println("order before update : " + order.getAddress().getId());
		model.addAttribute("order", order);
		model.addAttribute("ordersStatus", ordersStatus);
		System.out.println("All Orders Statusssss: " + ordersStatus);
		return "order-status";
	}

	@PostMapping("/updateOrderStatus")
	public String updateOrderStatus(@ModelAttribute("order") Order order, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String target = common.determineTargetUrl(authorities);

		if (target.equals("/user/showUser")) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			return "redirect:/user/showMyLoginPage?logout";
		}

		System.out.println("new order status : " + order.getStatus() + "  order id : " + order.getId());

		Order orderBeforeUpdate = orderService.findOrderById(order.getId());
		System.out.println("order after update : " + orderBeforeUpdate.getAddress().getId());
		order.setTimestamp(new Date());
		order.setAddress(orderBeforeUpdate.getAddress());
		order.setCustomer(orderBeforeUpdate.getCustomer());
		orderService.update(order);
		List<Order> orders = orderService.getOrders();
		model.addAttribute("orders", orders);
		return "redirect:/orders/list";
	}

	public String getSelectedOrderStatus() {
		return selectedOrderStatus;
	}

	public void setSelectedOrderStatus(String selectedOrderStatus) {
		this.selectedOrderStatus = selectedOrderStatus;
	}

	public List<String> getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(List<String> ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

}
