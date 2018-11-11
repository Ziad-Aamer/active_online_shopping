package com.onlineshopping.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.WebApplicationContext;

import com.onlineshopping.config.AppConfig;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.test.context.TestContext;
import com.onlineshopping.test.context.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, AppConfig.class })
@WebAppConfiguration
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private org.springframework.validation.Validator validator;

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
		mockMvc.perform(get("/customer/updatePassword").param("email", "kesmail@activedd.com").param("password", "123")
				.param("confirmPassword", "123")).andExpect(status().isOk()).andExpect(view().name("user-login"))
				.andExpect(forwardedUrl("/WEB-INF/view/user-login.jsp"));
	}

	@Test
	public void processRegistrationForm_ShouldGetErrorForFormInputsAndRenderRegistrationForm() throws Exception {
		Customer customer = new Customer();
		customer.setEmail("testEmail");
		customer.setPassword("123");
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/customer/processRegistrationForm");
		request.setParameter("email", "testEmail");
		request.setParameter("password", "123");
		WebDataBinder binder = new WebDataBinder(customer);
		binder.setValidator(validator);

		binder.bind(new MutablePropertyValues(request.getParameterMap()));

		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
		assertEquals(2, binder.getBindingResult().getErrorCount());

		mockMvc.perform(post("/customer/processRegistrationForm").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(TestUtil.convertObjectToFormUrlEncodedBytes(customer)).sessionAttr("customer", customer))
				.andExpect(status().isOk()).andExpect(view().name("customer-registration"))
				.andExpect(forwardedUrl("/WEB-INF/view/customer-registration.jsp"));
	}

	@Test
	public void processRegistrationForm_ShouldRenderRegistrationFormIfEmailAlreadyExist() throws Exception {

		Customer customer = new Customer();
		customer.setEmail("testEmail@gmail.com");
		customer.setPassword("123456789");

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/customer/processRegistrationForm");

		// email already exists before
		request.setParameter("email", "testEmail@gmail.com");
		request.setParameter("password", "123456789");

		WebDataBinder binder = new WebDataBinder(customer);

		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());

		assertEquals(0, binder.getBindingResult().getErrorCount());

		mockMvc.perform(post("/customer/processRegistrationForm").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(TestUtil.convertObjectToFormUrlEncodedBytes(customer)).sessionAttr("customer", customer))
				.andExpect(status().isOk()).andExpect(view().name("customer-registration"))
				.andExpect(forwardedUrl("/WEB-INF/view/customer-registration.jsp"));
	}

	@Test
	public void processRegistrationForm_ShouldValidateAndAcceptFormInputs() throws Exception {

		Random r = new Random();
		Integer random = r.nextInt();
		Customer customer = new Customer();
		String email = random.toString() + "testEmail@gmail.com";
		customer.setEmail(email);
		customer.setPassword("123456789");

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/customer/processRegistrationForm");

		request.setParameter("email", email);
		request.setParameter("password", "123456789");

		WebDataBinder binder = new WebDataBinder(customer);

		binder.setValidator(validator);
		binder.bind(new MutablePropertyValues(request.getParameterMap()));
		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());

		assertEquals(0, binder.getBindingResult().getErrorCount());

		mockMvc.perform(post("/customer/processRegistrationForm").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(TestUtil.convertObjectToFormUrlEncodedBytes(customer)).sessionAttr("customer", customer))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/user/showMyLoginPage"))
				.andExpect(redirectedUrl("/user/showMyLoginPage"));
	}

}