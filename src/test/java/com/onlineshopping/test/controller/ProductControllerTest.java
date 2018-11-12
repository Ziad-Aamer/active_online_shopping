package com.onlineshopping.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void searchByProduct_ShouldGetProductswhichMatchedCategAndProductNameToModelAndRenderTodoListView()
			throws Exception {

		mockMvc.perform(get("/product/search").param("searchProducts", "Broom").param("categoryName", ""))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().size(3)).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("products"));
	}

	@Test
	public void searchByProduct_ShouldGetAllProductsToModelAndRenderTodoListView() throws Exception {
		mockMvc.perform(get("/product/search").param("searchProducts", "").param("categoryName", ""))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().size(3)).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("products"));
	}

	@Test
	public void filterByPrice_ShouldGetProductsInRangeToModelAndRenderTodoListView() throws Exception {
		mockMvc.perform(get("/product/filterByPrice").param("price-min", "100").param("price-max", "1000"))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().attributeExists("categories")).andExpect(model().attributeExists("products"))
				.andExpect(model().size(3));

	}

	@Test
	public void filterByPrice_ShouldNotGetProductsNotInRangeToModelAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/product/filterByPrice").param("price-min", "-100").param("price-max", "-10"))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().attributeExists("categories")).andExpect(model().attributeExists("products"))
				.andExpect(model().size(3));
	}

}