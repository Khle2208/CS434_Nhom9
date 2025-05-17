package com.nhom9.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nhom9.model.Book;
import com.nhom9.model.Genre;
import com.nhom9.service.BookService;
import com.nhom9.service.GenreService;


@Controller
public class BookListController {
	@Autowired
	BookService bookService;
	@Autowired
	GenreService genreService;
	
	@GetMapping("/BookList")
	public String displayBookList(Model model) {
		List<Book> bookList = bookService.findAll();
		List<Genre> genreList = genreService.findAll();
		
		model.addAttribute("books", bookList);
		model.addAttribute("genres", genreList);
		
		return "content/book_list";
	}
	
	@GetMapping("/book_list/search")
	public String displaySearchResult(HttpServletRequest req, Model model) {
		
		String sql = (addConditionGenre(req).isEmpty()) ? getQuerySearchWithPrice(req) + addConditionName(req)
			: getQuerySearchWithPrice(req)+ " and " + addConditionGenre(req)+ addConditionName(req);
		
		List<Book> bookList = bookService.findBooksByCondition(sql);
		List<Genre> genreList = genreService.findAll();
		
		int totalBooksFound = (bookList == null)? 0 :bookList.size();
		if (bookList == null || bookList.isEmpty()) {
			
			sql = (addConditionGenre(req).isEmpty()) ? getQuerySearchWithPrice(req) 
			: (getQuerySearchWithPrice(req)+ " or " + addConditionGenre(req));
			
			
			bookList = bookService.findBooksByCondition(sql);															
			}
		model.addAttribute("totalBooksFound", totalBooksFound);
		model.addAttribute("genres", genreList);
		model.addAttribute("books", bookList);
		
		return "content/book_list";
	}
	
	public String getQuerySearchWithPrice(HttpServletRequest req) {
		
		int minPrice = (req.getParameter("minPrice").isEmpty())? 0 : Integer.parseInt(req.getParameter("minPrice"));
		int maxPrice = (req.getParameter("maxPrice").isEmpty())? 999999999: Integer.parseInt(req.getParameter("maxPrice"));
				
		return "SELECT * FROM BOOK WHERE (price between " + minPrice + " and " + maxPrice +") " ;
	}
	
	public String addConditionGenre(HttpServletRequest req) {
		String genre=""; 
		String[] options = req.getParameterValues("option");
		 if (options == null || options.length == 0) {
		        return "";
		    }
		if(options!= null) {
			genre = String.join("', '", req.getParameterValues("option"));
		}
		
		return " genreId in('"+ genre + "')";
		
	}
	
	public String addConditionName(HttpServletRequest req) {
		String search_value = req.getParameter("search_value");
		
		if(search_value != null) {
			return "and book_name like '%" + search_value + "%'";
		} else {
			return "";
		}
	}
}