package com.onlineshopping.test.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.onlineshopping.config.AppConfig;
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class CartControllerTest {

	private MockMvc mockMvc;

	protected MockHttpServletRequest request;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;
//	  
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity())
				.addFilters(this.springSecurityFilterChain).build();
	}

//	@Test
//	public void showCart_ShouldRedirectToMainPageIfNotLoggedIn() throws Exception {
//
//		try {
//			mockMvc.perform(formLogin("/authenticateTheUser").user("john").password("test123"))
//					.andExpect(authenticated().withUsername("john").withRoles("EMPLOYEE"));
//
//			mockMvc.perform(get("/cart/showCart").with(user("john").password("test123").roles("EMPLOYEE")))
//					.andExpect(status().isOk()).andExpect(view().name("cart"))
//					.andExpect(forwardedUrl("/WEB-INF/view/cart.jsp"));
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		HttpSession session = mockMvc
//				.perform(post("/j_spring_security_check").param("NAME", "john").param("PASSWORD", "test123"))
//				.andExpect(status().isMovedTemporarily()).andReturn().getRequest().getSession();
//		// .andDo(print())
//		System.out.println("maybe it help , i hope that : " + session.getAttribute("john"));
//		request = new MockHttpServletRequest();
//		request.setSession(session);
//		request.setRequestURI("/cart/showCart");

	// RequestContextHolder.setRequestAttributes(new
	// ServletRequestAttributes(request));
	// assertEquals(SecurityContextHolder.getContext().getAuthentication().getName(),
	// "john");

//		System.out.println(
//				"logedin user in unit test! : " + (Customer) request.getSession().getAttribute("loggedinUser"));

//		mockMvc.perform(get("/cart/showCart").session((MockHttpSession) session)).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
//	}

//	@Test
//	@WithMockUser(username = "john", password = "test123", roles = "EMPLOYEE")
//	public void showCart_ShouldShowCartViewIfCustomerLoggedIn() throws Exception {
//
//		mockMvc.perform(get("/cart/showCart")).andExpect(status().is3xxRedirection()).andExpect(view().name("cart"))
//				.andExpect(forwardedUrl("/WEB-INF/view/cart.jsp"));
//
//	}
//We need user to be loggedin to continue testing in showCart method!!	

	@Test
	public void addProduct_ShouldAddProductInCartAndRenderToListView() throws Exception {
		long id = 1;
		mockMvc.perform(get("/cart/addProduct/" + id)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
	}

}
