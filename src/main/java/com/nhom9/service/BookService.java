
package com.nhom9.service;

package fa.mockproject.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.BookDAO;
import fa.mockproject.model.Book;

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
	
	public ArrayList<Book> findTop4(){
		return bookDao.findBestBooks();
	}
	
	public ArrayList<Book> findBooksByCondition(String sql) {
		return bookDao.findBooksByCondition(sql);
	}
	
	public int getQuantityByID(UUID bookId) {
		return bookDao.getQuantityByID(bookId);
	}
	
	public Integer getBookService(java.util.Date startDateBook, java.util.Date endDateBook) {
		return bookDao.getBookDAO(startDateBook, endDateBook);
	}
	public List<Object[]> chartBookService(int year) {
		return bookDao.chartBookDAO(year);

	}
}
