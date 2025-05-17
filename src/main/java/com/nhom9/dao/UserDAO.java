package com.nhom9.dao;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nhom9.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import java.util.Date;
import java.util.List;

@Repository
public class UserDAO implements DAOInterface<User> {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public int insert(User t) {
		try {
			String sql = "INSERT INTO [User] (username, [password], email, [name], phone, sex, avatar, created_at, update_at, roleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Timestamp currentTimestamp = new Timestamp(new Date().getTime());
			return jdbcTemplate.update(sql, t.getUsername(), t.getPassword(), t.getEmail(), t.getName(), t.getPhone(),
					t.getSex(), t.getAvatar(), currentTimestamp, currentTimestamp,  "R02");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(User t) {
		try {
			String sql = "UPDATE [User] " + "SET password = ?, email = ?, name = ?, phone = ?, "
					+ "sex = ?, avatar = ?, roleId = ?, update_at = ? " + "WHERE userId = ?";

			Timestamp currentTimestamp = new Timestamp(new Date().getTime());

			return jdbcTemplate.update(sql, t.getPassword(), t.getEmail(), t.getName(), t.getPhone(), t.getSex(),
					t.getAvatar(), t.getRoleId(), currentTimestamp, t.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(User t) {
		try {
			String sql = "DELETE FORM [User] WHERE userId = ?";
			return jdbcTemplate.update(sql, t.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<User> findAll() {
		try {
			String sql = "SELECT * FROM [User] WHERE roleId NOT LIKE 'R01'";
			return (ArrayList<User>) jdbcTemplate.query(sql, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setUserId(rs.getObject("userId", UUID.class));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setSex(rs.getString("sex"));
					user.setAvatar(rs.getBytes("avatar"));
					user.setRoleId(rs.getString("roleId"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("update_at"));
					user.setStatus(rs.getInt("status"));
					return user;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public User findById(User t) {
		try {

			String sql = "SELECT * FROM [User] WHERE userId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { t.getUserId() }, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setUserId(rs.getObject("userId", UUID.class));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setSex(rs.getString("sex"));
					user.setAvatar(rs.getBytes("avatar"));
					user.setRoleId(rs.getString("roleId"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("update_at"));
					user.setStatus(rs.getInt("status"));
					return user;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int setStatusUser(User t) {
		try {
			String sql = "UPDATE [User] " + "SET status = ?" + " WHERE userId = ?";

			return jdbcTemplate.update(sql, t.getStatus(), t.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<User> findByName(String name) {
		try {
			String sql = "SELECT * FROM [User] WHERE username LIKE ?";
			return (ArrayList<User>) jdbcTemplate.query(sql, new Object[] { "%" + name + "%" }, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setUserId(rs.getObject("userId", UUID.class));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setSex(rs.getString("sex"));
					user.setAvatar(rs.getBytes("avatar"));
					user.setRoleId(rs.getString("roleId"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("update_at"));
					user.setStatus(rs.getInt("status"));
					return user;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	// Anh binh
	public boolean exitMail(String email) {
		String sql = "SELECT COUNT(*) FROM [User] WHERE email=?";
		int count = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
		return count > 0;
	}

	public boolean addUser(String email) {
		if (exitMail(email)) {
			return false;
		}
		String sql = "INSERT INTO [User] (email) VALUES(?)";
		int row = jdbcTemplate.update(sql, email);
		return row > 0;
	}

	public String getOTPByEmail(String email, HttpSession session) {
		String otp = (String) session.getAttribute("otp");

		if (otp != null) {
			return otp;
		} else {
			return null;
		}
	}

	public void Register(String username, String password, String email, String otp) {
		String sql = "INSERT INTO [User] (username,password,email,otp, roleId) VALUES(?,?,?,?,?) ";

		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		jdbcTemplate.update(sql, username, hashedPassword, email, otp, "R02");

	}


	



	public String getID(String email) {
		String sql = "SELECT userId FROM [User] WHERE email=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, String.class);
	}

	public boolean forgotPasswordDAO(String email, String passWord) {
		String sql = "UPDATE [User] SET password= ? WHERE email=?";

		String hashedPassword = BCrypt.hashpw(passWord, BCrypt.gensalt(12));

		int rowsAffected = jdbcTemplate.update(sql, hashedPassword, email);

		return rowsAffected > 0;
	}
	
	

}
