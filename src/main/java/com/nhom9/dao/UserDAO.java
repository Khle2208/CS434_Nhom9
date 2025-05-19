package com.nhom9.dao;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;


import org.springframework.jdbc.core.BeanPropertyRowMapper;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import fa.mockproject.model.User;



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


	
	public User findByUserId(UUID userId) {
		try {
			String sql = "SELECT * FROM [User] WHERE userId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { userId }, new RowMapper<User>() {
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
					user.setOTP(rs.getString("otp"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("update_at"));
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


	
	public int setStatusUser(User t) {
		try {
			String sql = "UPDATE [User] " + "SET status = ?" + " WHERE userId = ?";

			return jdbcTemplate.update(sql, t.getStatus(), t.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
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


	public User login(String email, String password) { // Không cần truyền mật khẩu ở đây
		String sql = "SELECT * FROM [User] WHERE email=?";
		try {

			User user = jdbcTemplate.queryForObject(sql, new Object[] { email }, new RowMapper<User>() {
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
			if (user == null) {
				System.out.println("Không tìm thấy user với email: " + email);
				return null;
			}

			if (!BCrypt.checkpw(password, user.getPassword())) {
				System.err.println("Mat khau sai");
			}
			System.out.println("Mật khẩu trong DB: " + user.getPassword());

			return user;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Không tìm thấy user với email: " + email);
			return null;
		}
	}

	public Integer getTotalUserDAO(Date startDateU, Date endDateU) {
		String sql = "SELECT COUNT(email) FROM [User] WHERE  created_at BETWEEN ? AND ?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, startDateU, endDateU);

		} catch (EmptyResultDataAccessException e) {

			return 0;
		}

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
	

	public List<Object[]> chartUserDAO(int year) {
		String sql= "SELECT \r\n"
				+ "\r\n"
				+ "    MONTH(created_at) AS month, \r\n"
				+ "    SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) AS active_users,\r\n"
				+ "    SUM(CASE WHEN status = 0 THEN 1 ELSE 0 END) AS locked_users, \r\n"
				+ "    COUNT(userId) AS user_count\r\n"
				+ "FROM [User]\r\n"
				+ "WHERE YEAR(created_at) = ? AND roleId NOT LIKE 'R01' \r\n"
				+ "GROUP BY YEAR(created_at), MONTH(created_at)\r\n"
				+ "ORDER BY month ASC";
		return jdbcTemplate.query(sql, new Object[] { year }, (rs, rowNum) -> {
			return new Object[] { rs.getInt("month"), rs.getInt("active_users"), // Đúng tên cột
					rs.getInt("locked_users"), rs.getInt("user_count") };
		});
	}

	public int updateUserById(UUID id_user, String name, String phone, String email, String sex) {
		try {
			String sql = "UPDATE [User] " + "SET email = ?, name = ?, phone = ?, "
					+ "sex = ?, update_at = ? " + "WHERE userId = ?";

			Timestamp currentTimestamp = new Timestamp(new Date().getTime());

			return jdbcTemplate.update(sql, email, name, phone, sex,
					 currentTimestamp, id_user);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public User getUserByID(UUID id_user) {
		try {

			String sql = "SELECT * FROM [User] WHERE userId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id_user }, new RowMapper<User>() {
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
					return user;
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	

}
