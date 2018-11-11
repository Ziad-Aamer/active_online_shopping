package com.onlineshopping.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.onlineshopping.config.AppConfig;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class CheckoutControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	HashMap<String, Object> sessionattr = new HashMap<String, Object>();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Customer customer = new Customer();
		customer.setEmail("john");
		customer.setFirstName("john");
		customer.setLastName("peter");
		customer.setPassword("test123");
		customer.setPhoneNumber(12233);

		sessionattr.put("loggedinUser", customer);
	}

	@Test
	public void showCheckOutForm_ShouldRedirectToLoginViewIfCustomerNotLoggedIn() throws Exception {

		mockMvc.perform(get("/checkout/")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/"))
				.andExpect(redirectedUrl("/"));
	}

	@Test
	public void showCheckOutForm_ShouldRenderToCheckoutIndexViewIfCustomerLoggedIn() throws Exception {

		mockMvc.perform(get("/checkout/").sessionAttrs(sessionattr)).andExpect(status().isOk())
				.andExpect(view().name("checkout")).andExpect(forwardedUrl("/WEB-INF/view/checkout.jsp"))
				.andExpect(model().attributeExists("address")).andExpect(model().attributeExists("addresses"));
	}

	@Test
	public void createOrder_ShouldRedirectToIndexViewIfCustomerNotLoggedIn() throws Exception {

		mockMvc.perform(post("/checkout/createOrder")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
	}

//	@Test
//	public void createOrder_ShouldSaveOrderAndRenderToIndexView() throws Exception {
//
//		mockMvc.perform(post("/checkout/createOrder").sessionAttrs(sessionattr)).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
//	}

}
