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
	@Autowired
	private EmailService emailservicel;
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



	public boolean exitMailService(String email) {
		return userDao.exitMail(email);
	}



		public boolean isValidOTP(String email, String enterotp,HttpSession session) {
			String storedOTP = userDao.getOTPByEmail(email, session);
			return storedOTP != null && storedOTP.equals(enterotp);
	
		}

	public void Register(String username, String password, String email,String otp) {
		userDao.Register(username, password, email,otp);

	}

	


	public String getIdService(String email) {
		return userDao.getID(email);
	}

	public boolean forgotPasswordService(String email, String newPassword) {
	    return userDao.forgotPasswordDAO(email, newPassword);
	}
	
	
}
