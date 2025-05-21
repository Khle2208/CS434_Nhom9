package fa.mockproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.BookImageDAO;
import fa.mockproject.model.Book;
import fa.mockproject.model.BookImage;

@Service
public class BookImageService implements ServiceInterface<BookImage>{

	@Autowired
	BookImageDAO bookImageDao;
	
	@Override
	public int insert(BookImage t) {
		return bookImageDao.insert(t);
	}

	@Override
	public int update(BookImage t) {
		return bookImageDao.update(t);
	}

	@Override
	public int delete(BookImage t) {
		return bookImageDao.delete(t);
	}

	@Override
	public ArrayList<BookImage> findAll() {
		return bookImageDao.findAll();
	}

	@Override
	public BookImage findById(BookImage t) {
		return bookImageDao.findById(t);
	}
	
	public ArrayList<BookImage> findByBookId(Book t){
		return bookImageDao.findByBookId(t);
	}

}
