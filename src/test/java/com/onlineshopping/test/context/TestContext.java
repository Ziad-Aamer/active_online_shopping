package com.onlineshopping.test.context;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.onlineshopping.config.DataInitilizerBean;
import com.onlineshopping.service.CategoryService;

@Configuration
public class TestContext {

	@Bean
	public DataInitilizerBean beanInitializer() {
		return Mockito.mock(DataInitilizerBean.class);
	}

	@Bean
	public CategoryService categoryService() {
		return Mockito.mock(CategoryService.class);
	}
//
//	@Bean
//	public Validator validatorFactory() {
//		return new LocalValidatorFactoryBean();
//	}
}
