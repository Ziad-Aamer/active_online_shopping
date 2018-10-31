package com.onlineshopping.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MailSenderBean {

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setPort(2525);

		mailSender.setUsername("8b447bb76afb8e");
		mailSender.setPassword("5ca16ec974f42b");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.debug", "true");

//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//
//		mailSender.setUsername("kesmail@activedd.com");
//		mailSender.setPassword("khaled121212");
//
//		Properties props = mailSender.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		// props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.debug", "true");
		return mailSender;
	}

//	@Bean
//	public MimeMailMessage templateSimpleMessage() {
//		MimeMailMessage message = new Mime();
//		message.setText("Please click on the link below to continue your process :\n%s\n");
//		return message;
//	}
}
