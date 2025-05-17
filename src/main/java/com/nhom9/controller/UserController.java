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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhom9.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	

	@GetMapping("/Register")
	public String showRegister() {
		return "content/Register";
	}


	@PostMapping("Register")
	public String verifyOTP(@RequestParam("otp1") String otp1, @RequestParam("otp2") String otp2,
			@RequestParam("otp3") String otp3, @RequestParam("otp4") String otp4, @RequestParam("otp5") String otp5,
			@RequestParam("otp6") String otp6, @RequestParam(value = "email", required = false) String email,
			HttpSession session, Model model) {

		// Lấy email từ session nếu không có trong request
		if (email == null || email.trim().isEmpty()) {
			email = (String) session.getAttribute("email");
		} else {
			session.setAttribute("email", email);
		}

		// Ghép OTP từ các ô input
		String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

		if (otp.trim().isEmpty()) {
			model.addAttribute("error", "Vui lòng nhập mã OTP!");
			return "content/RegisterOTP";
		}

		// Kiểm tra OTP
		System.out.println(email);
		if (userservice.isValidOTP(email, otp, session)) {

			return "content/Register";
		} else {
			model.addAttribute("error", "Mã OTP không hợp lệ, vui lòng thử lại!");
			return "content/RegisterOTP";
		}
	}

	
}
