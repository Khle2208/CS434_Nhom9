package fa.mockproject.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.AuthorDAO;
import fa.mockproject.model.Author;

@Service
public class AuthorService implements ServiceInterface<Author> {

	@Autowired
	AuthorDAO authorDao;

	@Override
	public int insert(Author t) {
		return authorDao.insert(t);
	}

	@Override
	public int update(Author t) {
		return authorDao.update(t);
	}

	@Override
	public int delete(Author t) {
		return authorDao.delete(t);
	}

	@Override
	public ArrayList<Author> findAll() {
		return authorDao.findAll();
	}

	@Override
	public Author findById(Author t) {
		return authorDao.findById(t);
	}

	public int countBookByAuthorId(Author t) {
		return authorDao.countBookByAuthorId(t);
	}
	
	
	public ArrayList<Author> findByName(String name) {
		return authorDao.findByName(name);
	}
	
	public Author findByFullName(String name) {
		return authorDao.findByFullName(name);
	}
	
	public Integer statisticAuthorService() {
		return authorDao.statisticAuthorDAO();
		
	}
	
	public Author findById(UUID authorId) {
		return authorDao.findById(authorId);
	}


}
