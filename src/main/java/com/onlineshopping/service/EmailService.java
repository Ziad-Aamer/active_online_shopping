package com.onlineshopping.service;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String body);

	public void sendMimeMessage(String to, String subject, String body);
}
