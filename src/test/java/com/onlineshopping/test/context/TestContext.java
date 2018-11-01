package com.onlineshopping.test.context;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.onlineshopping.config.DataInitilizerBean;

@Configuration
public class TestContext {

	@Bean
	public DataInitilizerBean beanInitializer() {
		return Mockito.mock(DataInitilizerBean.class);
	}
}
