package com.nhom9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nhom9.model.Book;



@Repository
public class BookDAO implements DAOInterface<Book> {

	@Autowired
	JdbcTemplate jdbcTemplate;


	@Override
	public int insert(Book t) {
		if (t.getBookId() == null) {
			try {
				String sql = "INSERT INTO [Book] "
						+ "(book_name, discription, quantity, price, total_pay, published_date, total_rating, avatar, genreId) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Remove author from insert.

				return jdbcTemplate.update(sql, t.getBookName(), t.getDiscription(), t.getQuantity(), t.getPrice(),
						t.getTotalPay(), t.getPublishedDate(), t.getTotalRating(), t.getAvatar(), t.getGenreId());
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			try {
				String sql = "INSERT INTO [Book] "
						+ "(bookId, book_name, discription, quantity, price, total_pay, published_date, total_rating, avatar, genreId) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // Remove author from insert

				return jdbcTemplate.update(sql, t.getBookId(), t.getBookName(), t.getDiscription(), t.getQuantity(),
						t.getPrice(), t.getTotalPay(), t.getPublishedDate(), t.getTotalRating(), t.getAvatar(),
						t.getGenreId());
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}

	}

	@Override
	public int update(Book t) {
		try {
			String sql = "UPDATE [Book] " + "SET book_name = ?, discription = ?, quantity = ?, "
					+ "price = ?, total_pay = ?, published_date = ?, total_rating = ?, avatar= ?, genreId= ? "
					+ "WHERE bookId = ?";
			return jdbcTemplate.update(sql, t.getBookName(), t.getDiscription(), t.getQuantity(), t.getPrice(),
					t.getTotalPay(), t.getPublishedDate(), t.getTotalRating(), t.getAvatar(), t.getGenreId(),
					t.getBookId());

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Book t) {
		try {
			String sqlWritedBy = "DELETE FROM Writed_by WHERE bookId = ?";
			String sqlBook = "DELETE FROM [Book] WHERE bookId = ?";
			String sqlRatings = "DELETE FROM Ratings WHERE bookId = ?";
			String sqlDeleteOrderDetail = "DELETE FROM [Order_detail] WHERE bookId = ?";
			String sqlDeleteCartItems = "DELETE FROM [Cart_items] WHERE bookId = ?";
			String sqlDeleteBookImage = "DELETE FROM [Book_image] WHERE bookId = ?";
			jdbcTemplate.update(sqlDeleteCartItems, t.getBookId());
			jdbcTemplate.update(sqlDeleteOrderDetail, t.getBookId());
			jdbcTemplate.update(sqlWritedBy, t.getBookId());
			jdbcTemplate.update(sqlDeleteBookImage, t.getBookId());
			jdbcTemplate.update(sqlRatings, t.getBookId());
			return jdbcTemplate.update(sqlBook, t.getBookId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Book> findAll() {
		try {
			String sql = "SELECT * FROM [Book]";
			return (ArrayList<Book>) jdbcTemplate.query(sql, new RowMapper<Book>() {
				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					book.setBookId(UUID.fromString(rs.getString("bookId")));
					book.setBookName(rs.getString("book_name"));
					book.setDiscription(rs.getString("discription"));
					book.setQuantity(rs.getInt("quantity"));
					book.setPrice(rs.getBigDecimal("price"));
					book.setTotalPay(rs.getBigDecimal("total_pay"));
					Timestamp timestamp = rs.getTimestamp("published_date");
					if (timestamp != null) {
						LocalDate publishedDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						book.setPublishedDate(publishedDate);
					}
					book.setTotalRating(rs.getInt("total_rating"));
					book.setAvatar(rs.getBytes("avatar"));
					book.setGenreId(UUID.fromString(rs.getString("genreId")));
					return book;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public Book findById(Book t) {
		try {

			String sql = "SELECT * FROM [Book] WHERE bookId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { t.getBookId() }, new RowMapper<Book>() {
				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					book.setBookId(UUID.fromString(rs.getString("bookId")));
					book.setBookName(rs.getString("book_name"));
					book.setDiscription(rs.getString("discription"));
					book.setQuantity(rs.getInt("quantity"));
					book.setPrice(rs.getBigDecimal("price"));
					book.setTotalPay(rs.getBigDecimal("total_pay"));
					Timestamp timestamp = rs.getTimestamp("published_date");
					if (timestamp != null) {
						LocalDate publishedDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						book.setPublishedDate(publishedDate);
					}
					book.setTotalRating(rs.getInt("total_rating"));
					book.setAvatar(rs.getBytes("avatar"));
					book.setGenreId(UUID.fromString(rs.getString("genreId")));
					return book;
				}

			});

		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Book> findByName(String name) {
		try {
			String sql = "SELECT * FROM [Book] WHERE book_name like ?";
			return (ArrayList<Book>) jdbcTemplate.query(sql, new Object[] { "%" + name + "%" }, new RowMapper<Book>() {
				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					book.setBookId(UUID.fromString(rs.getString("bookId")));
					book.setBookName(rs.getString("book_name"));
					book.setDiscription(rs.getString("discription"));
					book.setQuantity(rs.getInt("quantity"));
					book.setPrice(rs.getBigDecimal("price"));
					book.setTotalPay(rs.getBigDecimal("total_pay"));
					Timestamp timestamp = rs.getTimestamp("published_date");
					if (timestamp != null) {
						LocalDate publishedDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						book.setPublishedDate(publishedDate);
					}
					book.setTotalRating(rs.getInt("total_rating"));
					book.setAvatar(rs.getBytes("avatar"));
					book.setGenreId(UUID.fromString(rs.getString("genreId")));
					return book;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<Book> findBooksByCondition(String sql) {
		try {
			return (ArrayList<Book>) jdbcTemplate.query(sql, new RowMapper<Book>() {
				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book = new Book();
					book.setBookId(UUID.fromString(rs.getString("bookId")));
					book.setBookName(rs.getString("book_name"));
					book.setDiscription(rs.getString("discription"));
					book.setQuantity(rs.getInt("quantity"));
					book.setPrice(rs.getBigDecimal("price"));
					book.setTotalPay(rs.getBigDecimal("total_pay"));
					Timestamp timestamp = rs.getTimestamp("published_date");
					if (timestamp != null) {
						LocalDate publishedDate = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						book.setPublishedDate(publishedDate);
					}
					book.setTotalRating(rs.getInt("total_rating"));
					book.setAvatar(rs.getBytes("avatar"));
					book.setGenreId(UUID.fromString(rs.getString("genreId")));
					return book;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Integer getBookDAO(Date startDate, Date endDate) {
		String sql = "SELECT SUM(quantity) FROM Book WHERE addDate BETWEEN ? AND ?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, startDate, endDate);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}



}
