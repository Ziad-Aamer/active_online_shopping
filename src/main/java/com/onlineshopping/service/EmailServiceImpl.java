package com.onlineshopping.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendMimeMessage(String to, String subject, String body) {
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setTo(to);
//		simpleMailMessage.setSubject(subject);
//		simpleMailMessage.setText(body);
		// emailSender.send(simpleMailMessage);
//		MimeMessage message = emailSender.createMimeMessage();
//
//		// use the true flag to indicate you need a multipart message
//		MimeMessageHelper helper;
//		try {
//			helper = new MimeMessageHelper(message, true);
//			helper.setTo(to);
//			helper.setSubject(subject);
//			// use the true flag to indicate the text included is HTML
//			helper.setText(body, true);
//emailSender.send(message);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		///////////////
//
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setSubject(subject);
				message.setText(body, true);
			}
		};

//		MimeMessage message = emailSender.createMimeMessage();
//
//		MimeMessageHelper helper;
//		try {
//			helper = new MimeMessageHelper(message, true);
//			helper.setTo(to);
//			helper.setSubject(subject);
//			helper.setText(body);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		this.emailSender.send(preparator);

	}

	@Override
	public void sendSimpleMessage(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
	}
}
