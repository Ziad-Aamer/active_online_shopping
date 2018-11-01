package com.onlineshopping.test.controller;

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
import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.entity.Category;
import com.onlineshopping.test.context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class MainControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private DataInitilizerBean beanInitializer;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(beanInitializer);
//.addFilter(springSecurityFilterChain)
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void findAllCategories_ShouldAddCategoriesEntriesToModelAndRenderTodoListView() throws Exception {

		System.out.println("now inside findAll Categories Method Test!!!!!");
		List<Category> categories = new ArrayList<>();
		Category cat = new Category();
		cat.setCategoryName("test1");
		cat.setId(1);
		categories.add(cat);

		categories = beanInitializer.getCategoriesEAGER();

		when(beanInitializer.getCategoriesEAGER()).thenReturn(categories);

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/view/index.jsp"));
		// .andExpect(model().attribute("categories", hasSize(1)));
		// .andExpect(model().attribute("categories",
		// hasItem(allOf(hasProperty("categoryName", is("test1"))))));

		verify(beanInitializer, times(1)).getCategoriesEAGER();
		verifyNoMoreInteractions(beanInitializer);
	}
}

//mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("/index"))
//.andExpect(forwardedUrl("/WEB-INF/view/index.jsp")).andExpect(model().attribute("todos", hasSize(2)))
//.andExpect(model().attribute("todos",
//		hasItem(allOf(hasProperty("id", is(1L)), hasProperty("description", is("Lorem ipsum")),
//				hasProperty("title", is("Foo"))))))
//.andExpect(model().attribute("todos", hasItem(allOf(hasProperty("id", is(2L)),
//		hasProperty("description", is("Lorem ipsum")), hasProperty("title", is("Bar"))))));
