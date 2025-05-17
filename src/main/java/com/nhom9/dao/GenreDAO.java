package com.nhom9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nhom9.model.Genre;


@Repository
public class GenreDAO implements DAOInterface<Genre> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int insert(Genre t) {
		try {
			String sql = "INSERT INTO [Genre] (genre_name) VALUES (?)";
			return jdbcTemplate.update(sql, t.getGenreName());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Genre t) {
		try {
			String sql = "UPDATE [Genre] SET genre_name = ? WHERE genreId = ?";
			return jdbcTemplate.update(sql, t.getGenreName(), t.getGenreId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Genre t) {
		try {
			String sql = "DELETE FROM [Genre] WHERE genreId = ?";
			return jdbcTemplate.update(sql, t.getGenreId());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Genre> findAll() {
		try {
			String sql = "SELECT * FROM [Genre]";
			return (ArrayList<Genre>) jdbcTemplate.query(sql, new RowMapper<Genre>() {
				@Override
				public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
					Genre genre = new Genre();
					genre.setGenreId(UUID.fromString(rs.getString("genreId")));
					genre.setGenreName(rs.getString("genre_name"));
					return genre;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public Genre findById(Genre t) {
		try {
			String sql = "SELECT * FROM [Genre] WHERE genreId = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { t.getGenreId() }, new RowMapper<Genre>() {
				@Override
				public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
					Genre genre = new Genre();
					genre.setGenreId(UUID.fromString(rs.getString("genreId")));
					genre.setGenreName(rs.getString("genre_name"));
					return genre;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int counBookByGenreId(Genre t) {
		try {
			String sql = "SELECT COUNT(*) FROM [Book] WHERE genreId = ?";
			int count = jdbcTemplate.queryForObject(sql, new Object[] { t.getGenreId() }, Integer.class);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Genre> findByName(String name) {
		try {
			String sql = "SELECT * FROM [Genre] WHERE genre_name like ?";
			return (ArrayList<Genre>) jdbcTemplate.query(sql, new Object[] { "%" + name + "%" },
					new RowMapper<Genre>() {
						@Override
						public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
							Genre genre = new Genre();
							genre.setGenreId(UUID.fromString(rs.getString("genreId")));
							genre.setGenreName(rs.getString("genre_name"));
							return genre;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Genre findByFullName(String name) {
		try {
			String sql = "SELECT * FROM [Genre] WHERE genre_name  = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { name }, new RowMapper<Genre>() {
				@Override
				public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
					Genre genre = new Genre();
					genre.setGenreId(UUID.fromString(rs.getString("genreId")));
					genre.setGenreName(rs.getString("genre_name"));
					return genre;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
}
