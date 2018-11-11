package com.onlineshopping.test.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;

import org.junit.After;
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
import com.onlineshopping.entity.Cart;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class CartControllerTest {

	private MockMvc mockMvc;

	protected MockHttpServletRequest request;

	@Autowired
	private org.springframework.validation.Validator validator;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private WebApplicationContext webApplicationContext;

	HashMap<String, Object> sessionattr = new HashMap<String, Object>();

	@Before
	public void setUp() {
		Customer customer = new Customer();
		customer.setEmail("john");
		customer.setFirstName("john");
		customer.setLastName("peter");
		customer.setPassword("test123");
		customer.setPhoneNumber(12233);

		Cart cart = new Cart();
		cart.setId(6);
		cart.setCustomer(customer);
		cart.setTotalNumberOfProducts(555);
		cart.setTotalPrice(12345);
		customer.setCart(cart);

		sessionattr.put("loggedinUser", customer);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity())
				.addFilters(this.springSecurityFilterChain).build();
	}

	@Test
	public void showCart_ShouldRedirectToMainPageIfNotLoggedIn() throws Exception {

		mockMvc.perform(get("/cart/showCart")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
	}

	@Test
	public void showCart_ShouldShowCartViewIfCustomerLoggedIn() throws Exception {

		mockMvc.perform(get("/cart/showCart").sessionAttrs(sessionattr)).andExpect(status().isOk())
				.andExpect(view().name("cart")).andExpect(forwardedUrl("/WEB-INF/view/cart.jsp"))
				.andExpect(model().attributeExists("cpm"));

	}

	@Test
	public void addProduct_ShouldAddProductInCartAndRenderToListView() throws Exception {
		long id = 1;
		mockMvc.perform(get("/cart/addProduct/" + id)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/")).andExpect(redirectedUrl("/"));
	}

//	@Test
//	public void updateCart_shouldUpdateCartAndShowCartViewIfCustomerLoggedIn() {
//		CartProductCm cpm = new CartProductCm();
//		CartProduct cp = new CartProduct();
//
//		Cart cart = new Cart(1, 55);
//		cart.setId(6);
//
//		Product product = new Product("prod1", 55);
//		product.setId(8);
//
//		CartProductId cpId = new CartProductId(cart, product);
//
//		cp.setCart(cart);
//		cp.setProduct(product);
//		cp.setCartProductId(cpId);
//
//		List<CartProduct> cpList = new ArrayList<>();
//		cpList.add(cp);
//		cpList.add(cp);
//
//		cpm.setListOfProducts(cpList);

//		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/cart/updateCart");
//
//		WebDataBinder binder = new WebDataBinder(cpm);
//		binder.setValidator(validator);
//
//		binder.bind(new MutablePropertyValues(request.getParameterMap()));
//
//		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
	// assertEquals(0, binder.getBindingResult().getErrorCount());

//		try {

//			MockHttpServletRequest request = new MockHttpServletRequest("POST", "/cart/updateCart");
//
//			WebDataBinder binder = new WebDataBinder(cpm);
//			binder.setValidator(validator);
//
//			binder.bind(new MutablePropertyValues(request.getParameterMap()));
//
//			binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
	// assertEquals(0, binder.getBindingResult().getErrorCount());

//			mockMvc.perform(post("/cart/updateCart").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//					.content(TestUtil.convertObjectToFormUrlEncodedBytes(cpm)).sessionAttrs(sessionattr))
//					.andExpect(status().isOk()).andExpect(view().name("cart"))
//					.andExpect(forwardedUrl("/WEB-INF/view/cart.jsp"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	@After
	public void destroy() {
		long id = 1;
		try {
			mockMvc.perform(get("/cart/removeProduct/" + id).sessionAttrs(sessionattr))
					.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/cart/showCart"))
					.andExpect(redirectedUrl("/cart/showCart"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
