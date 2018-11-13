package com.onlineshopping.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.onlineshopping.dao.AdminDao;
import com.onlineshopping.dao.CustomerDao;
import com.onlineshopping.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsManager userDetailsManager;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AdminDao adminDao;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public boolean addUser(User user, String role) {

		boolean userExists = doesUserExist(user.getEmail());
		// add user to db
		if (!userExists) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());

			encodedPassword = "{bcrypt}" + encodedPassword;

			List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + role);

			// create user object (from Spring Security framework)
			org.springframework.security.core.userdetails.User tempUser = new org.springframework.security.core.userdetails.User(
					user.getEmail(), encodedPassword, authorities);

			// save user in the database
			userDetailsManager.createUser(tempUser);
			System.out.println("User: " + user.getFirstName() + " " + user.getLastName() + " created");
		}

		return userExists;
	}

	private boolean doesUserExist(String email) {
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(email);
		System.out.println("User: " + email + ", exists: " + exists);

		return exists;
	}

	@Transactional
	public User getUser(String email) {

		User customer = customerDao.getCustomer(email);
		if (customer != null)
			return customer;

		User admin = adminDao.getAdmin(email);
		return admin;
	}

	@Override
	public boolean doesUserExists(String email) {

		return doesUserExist(email);
	}

	@Override
	public void updatePassword(String email, String newPassword) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		boolean ok = doesUserExist(email);

		if (ok) {
			userDetailsManager.deleteUser(email);
		}

		org.springframework.security.core.userdetails.User tempUser = new org.springframework.security.core.userdetails.User(
				email, "{noop}" + newPassword, grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER")), ok,
				ok, ok, grantedAuthorities);
		userDetailsManager.createUser(tempUser);
	}

	@Override
	public void deleteUser(String email) {
		boolean ok = doesUserExist(email);

		if (ok) {
			userDetailsManager.deleteUser(email);
		}
	}

}
