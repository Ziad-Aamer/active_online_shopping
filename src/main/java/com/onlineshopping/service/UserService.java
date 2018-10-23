package com.onlineshopping.service;

import com.onlineshopping.entity.User;

public interface UserService {
	
	public boolean addUser(User user, String role);
	public User getUser(String email);
}
