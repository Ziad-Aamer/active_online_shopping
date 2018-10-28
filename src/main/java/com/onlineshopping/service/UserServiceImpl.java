package com.onlineshopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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
	    org.springframework.security.core.userdetails.User tempUser = new 
		    org.springframework.security.core.userdetails
		    .User(user.getEmail(), encodedPassword, authorities);

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
	
	User customer  = customerDao.getCustomer(email);
	if(customer != null)
	    return customer;
	/////////////////wtw
	
	User admin  = adminDao.getAdmin(email);
	return admin;
    }

}
