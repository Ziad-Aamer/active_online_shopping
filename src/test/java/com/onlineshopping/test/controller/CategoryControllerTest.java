package com.onlineshopping.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.onlineshopping.config.AppConfig;
import com.onlineshopping.entity.Category;
import com.onlineshopping.service.CategoryService;
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class CategoryControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(categoryService);
//.addFilter(springSecurityFilterChain)
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getCategories_ShouldGetCategoriesEntriesToModelAndRenderTodoListView() throws Exception {

		System.out.println("now inside get Categories Method Test!!!!!");
		List<Category> categories = new ArrayList<>();
		Category cat = new Category();
		cat.setCategoryName("test1");
		cat.setId(1);
		categories.add(cat);
		when(categoryService.getCategoriesLAZY()).thenReturn(categories);
		System.out.println("all categories size inside get categories: " + categories.size());
		mockMvc.perform(get("/category/search")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
		assertEquals(categories.size(), 1);

		verify(categoryService, times(1)).getCategoriesLAZY();
		verifyNoMoreInteractions(categoryService);
	}

	@Test
	public void getCategories_ShouldNotGetCategoriesEntries() throws Exception {

		List<Category> categories = new ArrayList<>();

		when(categoryService.getCategoriesLAZY()).thenReturn(categories);
		System.out.println("all categories size inside get categories: " + categories.size());
		mockMvc.perform(get("/category/search")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
		assertEquals(categories.size(), 0);
		verify(categoryService, times(1)).getCategoriesLAZY();
		verifyNoMoreInteractions(categoryService);
	}
}