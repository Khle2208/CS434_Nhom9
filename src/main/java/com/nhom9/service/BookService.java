package com.nhom9.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom9.dao.BookDAO;
import com.nhom9.model.Book;



@Service
public class BookService implements ServiceInterface<Book>{
	@Autowired
	BookDAO bookDao;
	
	
	@Override
	public int insert(Book t) {
		return bookDao.insert(t);
	}

	@Override
	public int update(Book t) {
		return bookDao.update(t);
	}

	@Override
	public int delete(Book t) {
		return bookDao.delete(t);
	}

	@Override
	public ArrayList<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findById(Book t) {
		return bookDao.findById(t);
	}

	public ArrayList<Book> findByName(String name){
		return bookDao.findByName(name);
	}
	

	public ArrayList<Book> findBooksByCondition(String sql) {
		return bookDao.findBooksByCondition(sql);
	}
	
	public Integer getBookService(Date startDate, Date endDate) {
		return bookDao.getBookDAO(startDate, endDate);
	}
	
}
