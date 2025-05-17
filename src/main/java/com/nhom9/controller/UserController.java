package com.nhom9.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.nhom9.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	// Xử lý GET request để hiển thị trang đăng ký
	@GetMapping("RegisterEmail")
	public String showRegisterPage() {
		return "content/RegisterEmail"; // Trả về trang JSP (WEB-INF/views/register.jsp)
	}

	@GetMapping("/RegisterOTP")
	public String showOTPPage() {
		return "content/RegisterOTP"; // Hiển thị trang nhập OTP (WEB-INF/views/RegisterOTP.jsp)
	}

	@GetMapping("/Register")
	public String showRegister() {
		return "content/Register";
	}

	@GetMapping("/LoginPage")
	public String showLogin() {
		return "content/LoginPage";
	}

	@GetMapping("/ForgotPassword")
	public String showForgotPass() {
		return "content/ForgotPassword";
	}

	@GetMapping("/Home")
	public String showHomePage(HttpSession session) {
		return "content/home";
	}

	

	
	// Xử lý yêu cầu gửi lại OTP


	

	@PostMapping("/LoginPage")
	public String RegisterSuccess(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "otp", required = false) String otp, HttpSession session) {
		if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
			return "content/Register";
		}

		if (email == null || email.trim().isEmpty()) {
			email = (String) session.getAttribute("email");
		}
		if (otp == null || otp.trim().isEmpty()) {
			otp = (String) session.getAttribute("otp");
		}

		userservice.Register(username, password, email, otp);
		return "content/LoginPage";
	}

	


	
	
}
