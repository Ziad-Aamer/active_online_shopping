package com.onlineshopping.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class OrderControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getOrders_ShouldGetAllOrdersToModelAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/orders/list")).andExpect(status().isOk()).andExpect(view().name("admin-orders"))
				.andExpect(forwardedUrl("/WEB-INF/view/admin-orders.jsp")).andExpect(model().attributeExists("order"))
				.andExpect(model().attributeExists("orders")).andExpect(model().attributeExists("ordersStatus"))
				.andExpect(model().size(3));
	}

	@Test
	public void search_ShouldGetOrdersBasedOnSearchToModelAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/orders/search").param("orderStatusSearch", "pending")).andExpect(status().isOk())
				.andExpect(view().name("admin-orders")).andExpect(forwardedUrl("/WEB-INF/view/admin-orders.jsp"))
				.andExpect(model().attributeExists("orders")).andExpect(model().size(1));
	}

	@Test
	public void showFormUpdateOrderStatus_ShouldShowFormUpdateStatusAndRenderTodoListView() throws Exception {

		mockMvc.perform(get("/orders/showFormUpdate").param("orderId", "1")).andExpect(status().isOk())
				.andExpect(view().name("order-status")).andExpect(forwardedUrl("/WEB-INF/view/order-status.jsp"))
				.andExpect(model().attributeExists("ordersStatus")).andExpect(model().attributeExists("order"))
				.andExpect(model().size(2));
	}

	@Test
	public void updateOrderStatus_ShouldUpdateOrderStatusAndRenderTodoListView() throws Exception {

		mockMvc.perform(post("/orders/updateOrderStatus").param("orderId", "1")).andExpect(status().isOk())
				.andExpect(view().name("admin-orders")).andExpect(forwardedUrl("/WEB-INF/view/admin-orders.jsp"))
				.andExpect(model().attributeExists("orders"));
	}
}
