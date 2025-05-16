package com.nhom9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fa.mockproject.model.Book;
import fa.mockproject.model.BookImage;

@Repository
public class BookImageDAO implements DAOInterface<BookImage>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int insert(BookImage t) {
		try {
			String sql = "INSERT INTO [Book_image] "
					+ "(bookId, name_image, image_data) "
					+ "VALUES (?, ?, ?)";

			return jdbcTemplate.update(sql,t.getBookId(), t.getNameImage(), t.getImageData());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(BookImage t) {
		try {
			String sql = "UPDATE [Book_image] " + "SET name_image = ?, image_data = ? " + "WHERE bookId = ?";
			return jdbcTemplate.update(sql,t.getNameImage(), t.getImageData(), t.getBookId());
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(BookImage t) {
		try {
			String sql = "DELETE FROM [Book_image] WHERE bookId = ? AND bookImageId = ?";
			return jdbcTemplate.update(sql, t.getBookId(), t.getImageId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<BookImage> findAll() {
		try {
			String sql = "SELECT * FROM [Book_image]";
			return (ArrayList<BookImage>) jdbcTemplate.query(sql, new RowMapper<BookImage>() {
				@Override
				public BookImage mapRow(ResultSet rs, int rowNum) throws SQLException {
					BookImage bookImage = new BookImage();
					bookImage.setImageId(UUID.fromString(rs.getString("bookImageId")));
					bookImage.setBookId(UUID.fromString(rs.getString("bookId")));
					bookImage.setNameImage(rs.getString("name_image"));
					bookImage.setImageData(rs.getBytes("image_data"));
					return bookImage;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BookImage findById(BookImage t) {
		try {
			String sql = "SELECT * FROM [Book_image] WHERE bookImageId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] {t.getImageId() }, new RowMapper<BookImage>() {
				@Override
				public BookImage mapRow(ResultSet rs, int rowNum) throws SQLException {
					BookImage bookImage = new BookImage();
					bookImage.setImageId(UUID.fromString(rs.getString("bookImageId")));
					bookImage.setBookId(UUID.fromString(rs.getString("bookId")));
					bookImage.setNameImage(rs.getString("name_image"));
					bookImage.setImageData(rs.getBytes("image_data"));
					return bookImage;
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public ArrayList<BookImage> findByBookId(Book t) {
		try {
			String sql = "SELECT * FROM [Book_image] WHERE bookId = ?";
			return (ArrayList<BookImage>) jdbcTemplate.query(sql, new Object[] {t.getBookId()},new RowMapper<BookImage>() {
				@Override
				public BookImage mapRow(ResultSet rs, int rowNum) throws SQLException {
					BookImage bookImage = new BookImage();
					bookImage.setImageId(UUID.fromString(rs.getString("bookImageId")));
					bookImage.setBookId(UUID.fromString(rs.getString("bookId")));
					bookImage.setNameImage(rs.getString("name_image"));
					bookImage.setImageData(rs.getBytes("image_data"));
					return bookImage;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
