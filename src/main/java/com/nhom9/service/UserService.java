package com.nhom9.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom9.dao.UserDAO;
import com.nhom9.model.User;



@Service
public class UserService implements ServiceInterface<User>{

	@Autowired
	UserDAO userDao;

	@Override
	public int insert(User t) {
		return userDao.insert(t);
	}

	@Override
	public int update(User t) {
		return userDao.update(t);
	}

	@Override
	public int delete(User t) {
		return userDao.delete(t);
		
	}

	@Override
	public ArrayList<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(User t) {
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
			return storedOTP != null && storedOTP.equals(enterotp);
	
		}

	public void Register(String username, String password, String email,String otp) {
		userDao.Register(username, password, email,otp);

	}

	public String Login(String email, String password) {
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
		this.userDao = userdao;
	}


	public String getIdService(String email) {
		return userDao.getID(email);
	}

	
}
