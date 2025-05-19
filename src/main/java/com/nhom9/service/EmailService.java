package com.nhom9.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
	@Autowired
	private JavaMailSender javamailsender;

	public String getOtp() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(100000));

	}

	public void sendOtpEmail(String toEmail, String otp) {
		SimpleMailMessage simplemailmessage = new SimpleMailMessage();
		simplemailmessage.setFrom("levannguyen2k44@gmail.com");
		simplemailmessage.setTo(toEmail);
		simplemailmessage.setSubject("Mã OTP Xác Thực:");
		simplemailmessage.setText("Mã OTP của bạn là:" + otp);
		javamailsender.send(simplemailmessage);

	}
}
