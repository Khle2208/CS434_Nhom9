package com.nhom9.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom9.dao.GenreDAO;
import com.nhom9.model.Genre;



@Service
public class GenreService implements ServiceInterface<Genre>{

	@Autowired
	GenreDAO genreDao;
	@Override
	public int insert(Genre t) {
		return genreDao.insert(t);
	}

	@Override
	public int update(Genre t) {
		return genreDao.update(t);
	}

	@Override
	public int delete(Genre t) {
		return genreDao.delete(t);
	}

	@Override
	public ArrayList<Genre> findAll() {
		return genreDao.findAll();
	}

	@Override
	public Genre findById(Genre t) {
		return genreDao.findById(t);
	}
	

	public ArrayList<Genre> findByName(String name){
		return genreDao.findByName(name);
	}
	
	public Genre findByFullName(String name) {
		return genreDao.findByFullName(name);
	}
}
