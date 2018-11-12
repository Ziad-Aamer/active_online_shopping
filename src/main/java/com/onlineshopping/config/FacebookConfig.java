package com.onlineshopping.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.onlineshopping.social.SocialContext;
import com.onlineshopping.social.UserCookieGenerator;

@Configuration
@ComponentScan("com.onlineshopping")
@PropertySource("classpath:application.properties")
public class FacebookConfig implements InitializingBean {

	private static final Logger logger = Logger.getLogger(FacebookConfig.class.getName());

	@Autowired
	private Environment env;

	private SocialContext socialContext;

	private UsersConnectionRepository usersConnectionRepositiory;

	@Autowired
	private DataSource myDataSource;

	/**
	 * Point to note: the name of the bean is either the name of the method
	 * "socialContext" or can be set by an attribute
	 * 
	 * @Bean(name="myBean")
	 */
	@Bean
	public SocialContext socialContext() {

		return socialContext;
	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {

		logger.info("getting connectionFactoryLocator");
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("spring.social.facebook.appId"),
				env.getProperty("spring.social.facebook.appSecret")));
		System.out.println("Facebook secret: " + env.getProperty("spring.social.facebook.appSecret"));
		return registry;
	}

	/**
	 * Singleton data access object providing access to connections across all
	 * users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {

		return usersConnectionRepositiory;
	}

	/**
	 * Request-scoped data access object providing access to the current user's
	 * connections.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		String userId = socialContext.getUserId();
		System.out.println("\n=====================>inside connectionRepository : " + userId);
		return usersConnectionRepository().createConnectionRepository(userId);
	}

	/**
	 * A proxy to a request-scoped object representing the current user's primary
	 * Facebook account.
	 * 
	 * @throws NotConnectedException if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}

	/**
	 * Create the ProviderSignInController that handles the OAuth2 stuff and tell it
	 * to redirect back to /posts once sign in has completed
	 */
	@Bean
	public ProviderSignInController providerSignInController() {
		System.out.println("\n=====================>inside providerSignInController");
		ProviderSignInController providerSigninController = new ProviderSignInController(connectionFactoryLocator(),
				usersConnectionRepository(), socialContext);
		providerSigninController.setPostSignInUrl("/facebook/posts");
		return providerSigninController;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		JdbcUsersConnectionRepository usersConnectionRepositiory = new JdbcUsersConnectionRepository(myDataSource,
				connectionFactoryLocator(), Encryptors.noOpText());

		socialContext = new SocialContext(usersConnectionRepositiory, new UserCookieGenerator(), facebook());
		usersConnectionRepositiory.setConnectionSignUp(socialContext);
		this.usersConnectionRepositiory = usersConnectionRepositiory;
	}
}
