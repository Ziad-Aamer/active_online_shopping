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
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// TODO: i need to solve securityException , when add hasProperty attritbute!!!
	@Test
	public void showRegistratonForm_ShouldRenderToRegistrationView() throws Exception {

		mockMvc.perform(get("/customer/showRegistrationForm")).andExpect(status().isOk())
				.andExpect(view().name("customer-registration"))
				.andExpect(forwardedUrl("/WEB-INF/view/customer-registration.jsp"))
				.andExpect(model().attributeExists("customer")).andExpect(model().size(1));
	}

	@Test
	public void showForgetPasswordForm_ShouldRenderToForgetPasswordView() throws Exception {
		mockMvc.perform(get("/customer/showForgetPasswordForm")).andExpect(status().isOk())
				.andExpect(view().name("forgot-password")).andExpect(forwardedUrl("/WEB-INF/view/forgot-password.jsp"))
				.andExpect(model().attributeExists("customer")).andExpect(model().size(1));

	}

	@Test
	public void forgotPassword_ShouldSendEmailIfUserExist() throws Exception {
		mockMvc.perform(get("/customer/forgotPassword").param("email", "kesmail@activedd.com"))
				.andExpect(status().isOk()).andExpect(view().name("check-email"))
				.andExpect(forwardedUrl("/WEB-INF/view/check-email.jsp"));
	}

	@Test
	public void forgotPassword_ShouldUserNotExist() throws Exception {
		mockMvc.perform(get("/customer/forgotPassword").param("email", "test@gmail.com")).andExpect(status().isOk())
				.andExpect(view().name("forgot-password")).andExpect(forwardedUrl("/WEB-INF/view/forgot-password.jsp"))
				.andExpect(model().attributeExists("emailNotExist")).andExpect(model().attributeExists("customer"));
	}

	@Test
	public void createNewPasswordForm_ShouldRenderNewPasswordView() throws Exception {
		mockMvc.perform(get("/customer/newPasswordForm").param("email", "kesmail@activedd.com"))
				.andExpect(status().isOk()).andExpect(view().name("new-password"))
				.andExpect(forwardedUrl("/WEB-INF/view/new-password.jsp")).andExpect(model().attributeExists("email"));
	}

	@Test
	public void updatePassword_ShouldPasswordNotMatch() throws Exception {
		mockMvc.perform(get("/customer/updatePassword").param("email", "kesmail@activedd.com").param("password", "123")
				.param("confirmPassword", "1234")).andExpect(status().isOk()).andExpect(view().name("new-password"))
				.andExpect(forwardedUrl("/WEB-INF/view/new-password.jsp")).andExpect(model().attributeExists("email"))
				.andExpect(model().attributeExists("notMatch"));
	}

	@Test
	public void updatePassword_ShouldPasswordMatch() throws Exception {
		mockMvc.perform(post("/customer/updatePassword").param("email", "kesmail@activedd.com").param("password", "123")
				.param("confirmPassword", "123")).andExpect(status().isOk()).andExpect(view().name("user-login"))
				.andExpect(forwardedUrl("/WEB-INF/view/user-login.jsp"));
	}

//	@Test
//	public void processRegistrationForm_ShouldValidateFormInputs() throws Exception {
//		Customer customer = new Customer();
//		customer.setEmail(null);
//		customer.setPassword("12");
//
////		MockHttpServletRequest request = new MockHttpServletRequest();
////
////	    BindingResult errors = new DataBinder(customer).getBindingResult();
////	    // controller is initialized in @Before method
////	    controller.add(customer, errors, request);
////	    assertEquals(1, errors.getErrorCount());
////param("customer.email", "testtest@gmail.com")
//// .param("customer.password", "123321").param("firstName", "khaled")
//		final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
//				MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
//		String EMPLOYEE_REQUEST = "{\"name\" : \"email\"}";
//		mockMvc.perform(
//				post("/customer/processRegistrationForm").content(EMPLOYEE_REQUEST).contentType(APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk()).andExpect(view().name("customer-registration"))
//				.andExpect(forwardedUrl("/WEB-INF/view/customer-registration.jsp"))
//				.andExpect(model().attributeExists("customer")).andExpect(model().attributeExists("registrationError"));
//	}

}