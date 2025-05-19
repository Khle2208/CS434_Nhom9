package com.nhom9.model;

import java.util.Date;
import java.util.UUID;

public class User {
	private UUID userId;
	private String username;
	private String password;
	private String email;
	private String name;
	private String phone;
	private String sex;
	private byte[] avatar;
	private Date createdAt;
	private Date updatedAt;
	private String roleId;
	private String OTP;
	private int status;

	public User() {
		super();
	}

	public User(UUID userId, String username, String password, String email, String name, String phone, String sex,
			byte[] avatar, Date createdAt, Date updatedAt, String roleId, int status, String OTP) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.avatar = avatar;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roleId = roleId;
		this.status = status;
		this.OTP = OTP;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}
	
	
}
