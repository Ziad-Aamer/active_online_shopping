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

//	@Autowired
//	private DataInitilizerBean beanInitializer;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		// Mockito.reset(beanInitializer);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// TODO: i need to solve securityException , when add hasProperty attritbute!!!
	@Test
	public void searchByProduct_ShouldGetProductswhichMatchedCategAndProductNameToModelAndRenderTodoListView()
			throws Exception {

		mockMvc.perform(get("/product/search").param("searchProducts", "Broom").param("categoryName", ""))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().size(3)).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("products"));

		// verifyNoMoreInteractions(beanInitializer);
	}

	@Test
	public void searchByProduct_ShouldGetAllProductsToModelAndRenderTodoListView() throws Exception {

//		List<Category> categories = new ArrayList<>();
//		List<SubCategory> subCategories1 = new ArrayList<>();
//		List<Product> products = new ArrayList<>();
//
//		Category cat1 = new Category();
//		cat1.setCategoryName("testCateg1");
//		cat1.setId(1);
//
//		SubCategory subCat1 = new SubCategory();
//		subCat1.setName("subCateg1");
//		subCat1.setId(1);
//
//		subCategories1.add(subCat1);
//
//		Product prod1 = new Product();
//		prod1.setId(1);
//		prod1.setName("prod1");
//		prod1.setDescription("desc of prod1");
//
//		products.add(prod1);
//
//		subCat1.setProducts(products);
//
//		cat1.setSubCategories(subCategories1);
//
//		categories.add(cat1);
//
//		when(beanInitializer.getCategoriesEAGER()).thenReturn(categories);

		mockMvc.perform(get("/product/search").param("searchProducts", "").param("categoryName", ""))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().size(3)).andExpect(model().attributeExists("categories"))
				.andExpect(model().attributeExists("products"));
//.andExpect(model().attribute("products", hasProperty("name")))
//		assertEquals(categories.size(), 1);
//		assertEquals(categories.get(0).getSubCategories().get(0).getProducts().size(), 1);
//		assertNotEquals(categories.get(0).getSubCategories().get(0).getProducts(), null);

		// verifyNoMoreInteractions(beanInitializer);
	}

	@Test
	public void filterByPrice_ShouldGetProductsInRangeToModelAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/product/filterByPrice").param("price-min", "100").param("price-max", "1000"))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().attributeExists("categories")).andExpect(model().attributeExists("products"))
				.andExpect(model().size(3));

		// verifyNoMoreInteractions(beanInitializer);
	}

	// When i solve the security Exception i will check on products size should be 0
	@Test
	public void filterByPrice_ShouldNotGetProductsNotInRangeToModelAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/product/filterByPrice").param("price-min", "-100").param("price-max", "-10"))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attributeExists("category"))
				.andExpect(model().attributeExists("categories")).andExpect(model().attributeExists("products"))
				.andExpect(model().size(3));

		// verifyNoMoreInteractions(beanInitializer);
	}

}