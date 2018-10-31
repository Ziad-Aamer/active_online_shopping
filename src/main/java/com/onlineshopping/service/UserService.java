package com.onlineshopping.service;

import com.onlineshopping.entity.User;

public interface UserService {

	public boolean addUser(User user, String role);

	public User getUser(String email);

	// this method for forgot password
	public boolean doesUserExists(String email);

	public void updatePassword(String email, String newPassword);
}
