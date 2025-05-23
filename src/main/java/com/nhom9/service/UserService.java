package com.nhom9.service;

import java.util.ArrayList;


import java.util.UUID;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import fa.mockproject.dao.UserDAO;
import fa.mockproject.model.User;


import com.nhom9.dao.UserDAO;
import com.nhom9.model.User;




@Service
public class UserService implements ServiceInterface<User>{

	@Autowired


	UserDAO userdao;
	@Autowired
	private EmailService emailservicel;
	
	@Override
	public int insert(User t) {
		return userdao.insert(t);

	UserDAO userDao;


	UserDAO userDao;
	@Autowired
	private EmailService emailservicel;

	@Override
	public int insert(User t) {
		return userDao.insert(t);
	}

	@Override
	public int update(User t) {


		return userdao.update(t);

		return userDao.update(t);


		return userDao.update(t);

	}

	@Override
	public int delete(User t) {


		return userdao.delete(t);

		return userDao.delete(t);

		

		return userDao.delete(t);

	}

	@Override
	public ArrayList<User> findAll() {


		return userdao.findAll();

		return userDao.findAll();


		return userDao.findAll();

	}

	@Override
	public User findById(User t) {


		return userdao.findById(t);
	}
	
	public int setStatusUser(User t) {
		return userdao.setStatusUser(t);
	}
	
	public ArrayList<User> findByName(String name){
		return userdao.findByName(name);
	}
	
	public String sendOtp(String email, HttpSession session) {
        String otp = emailservicel.getOtp(); // Tạo OTP mới
        emailservicel.sendOtpEmail(email, otp);; // Gửi OTP qua email
		return otp;
    }

	public boolean exitMailService(String email) {
		return userdao.exitMail(email);
	}

	public String getOTP(String email,HttpSession session) {
		 String otp = (String) session.getAttribute("otp_" + email);
	        if (otp == null) {
	            System.out.println("❌ Không tìm thấy OTP trong session cho email: " + email);
	        }
	        return otp;

	}

		public boolean isValidOTP(String email, String enterotp,HttpSession session) {
			String storedOTP = userdao.getOTPByEmail(email, session);


		return userDao.findById(t);
	}
	
	public int setStatusUser(User t) {
		return userDao.setStatusUser(t);
	}
	
	public ArrayList<User> findByName(String name){
		return userDao.findByName(name);
	}



		public boolean isValidOTP(String email, String enterotp,HttpSession session) {
			String storedOTP = userDao.getOTPByEmail(email, session);



	public boolean exitMailService(String email) {
		return userDao.exitMail(email);
	}



		public boolean isValidOTP(String email, String enterotp,HttpSession session) {
			String storedOTP = userDao.getOTPByEmail(email, session);

			return storedOTP != null && storedOTP.equals(enterotp);
	
		}

	public void Register(String username, String password, String email,String otp) {

		userdao.Register(username, password, email,otp);

		userDao.Register(username, password, email,otp);


	}

	public String Login(String email, String password) {

	    User user = userdao.login(email, password); // Chỉ lấy user theo email

	    User user = userDao.login(email, password); // Chỉ lấy user theo email


	    if (user == null) {
	        return "Email hoặc mật khẩu không chính xác!";
	    }
	    
	    if (user.getStatus() == 0) {
	        System.out.println("Tài khoản bị khóa!");
	        return "Tài khoản của bạn đã bị khóa!";
	    }

	    // So sánh mật khẩu đã nhập với mật khẩu mã hóa trong DB
	    if (!BCrypt.checkpw(password, user.getPassword())) {
	        System.out.println("Mật khẩu không chính xác cho email: " + email);
	        return "Email hoặc mật khẩu không chính xác!";
	    }
	    System.out.println(user.getRoleId());
	   if(user.getRoleId().equals("R01")) {
		   System.out.println("Admin đăng nhập thành công!");
		    return "adminLogin";
	   }
	    System.out.println("Đăng nhập thành công!");
	    return "success";
	}

	public UserService(UserDAO userdao) {
		this.userdao = userdao;
	}

	public Integer getTotalUserService(Date startDateU, Date endDateU) {
		return userdao.getTotalUserDAO(startDateU, endDateU);
	}

	public String getIdService(String email) {
		return userdao.getID(email);
	}

	public boolean forgotPasswordService(String email, String newPassword) {
	    return userdao.forgotPasswordDAO(email, newPassword);
	}
	
	public List<Object[]> chartUserSerivce(int year) {
		return userdao.chartUserDAO(year);
		
	}
	
	public User findByUserId(UUID t) {
		return userdao.findByUserId(t);
	}
	
	public User getUserByID(UUID id_user) {
		return userdao.getUserByID(id_user);
	}
	


	public int updateUserById(UUID id_user, String name, String phone, String email, String sex) {
		return userdao.updateUserById(id_user, name, phone, email, sex);
	}


	public UserService(UserDAO userdao) {
		this.userDao = userdao;
	}

		userDao.Register(username, password, email,otp);

	}

	



	public String getIdService(String email) {
		return userDao.getID(email);
	}


	public boolean forgotPasswordService(String email, String newPassword) {
	    return userDao.forgotPasswordDAO(email, newPassword);
	}
	
	
}
