package fa.mockproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fa.mockproject.model.Author;
import fa.mockproject.model.Book;
import fa.mockproject.model.BookImage;
import fa.mockproject.model.Ratings;
import fa.mockproject.model.User;
import fa.mockproject.model.WritedBy;
import fa.mockproject.service.AuthorService;
import fa.mockproject.service.BookImageService;
import fa.mockproject.service.BookService;
import fa.mockproject.service.RatingsService;
import fa.mockproject.service.UserService;
import fa.mockproject.service.WritedByService;

@Controller
@RequestMapping("/product_detail")
public class ProductDetailController {
	@Autowired
	BookService bookService;
	@Autowired
	BookImageService bookImageService;
	@Autowired
	RatingsService ratingsService;
	@Autowired
	UserService userService;
	@Autowired
	WritedByService writedByService;
	@Autowired
	AuthorService authorService;
	@GetMapping("/{bookId}")
	public String displayProductDetail(@PathVariable UUID bookId, HttpServletRequest req, Model model) {
		Book b = new Book(bookId, null, null, 0, null, null, null, 0, null, null, bookId);
		Book book = bookService.findById(b);
		ArrayList<BookImage> bookImages = bookImageService.findByBookId(b);
		ArrayList<Ratings> ratings = ratingsService.findRatingsByBookId(b.getBookId());
		HashMap<UUID, User> usersMap = new HashMap<>();
		for (Ratings rating : ratings) {
			usersMap.put((rating.getUserId()), userService.findByUserId(rating.getUserId()));
		}
		List<Author> listAuthorDetail = new ArrayList<Author>();
		List<WritedBy> listWrited = writedByService.findByBookId(book.getBookId());
		for (WritedBy writedBy : listWrited) {
			Author a = authorService.findById(writedBy.getAuthorId());
			listAuthorDetail.add(a);
		}
		String url = "product_detail/"+ b.getBookId();
		List<Book> books = bookService.findTop4();
		model.addAttribute("actionUrl", url );
		model.addAttribute("books", books);
		model.addAttribute("usersMap", usersMap);
		model.addAttribute("listAuthorDetail", listAuthorDetail);
		model.addAttribute("ratings",ratings);
		model.addAttribute("bookImages",bookImages);
		model.addAttribute("book", book);
		model.addAttribute("avgStar", avgStar(ratings));
		return "content/product_detail";
		
	}
	
	public Float avgStar(ArrayList<Ratings> ratings) {
		float avgStar = 0;
		if(ratings.isEmpty()) return 0f;
		 int sumStar = ratings.stream().mapToInt(rating -> rating.getStarNumber()).sum(); 
		 avgStar = sumStar / ratings.size();
		 if(avgStar < (Math.round(avgStar)+0.3)) {
			 avgStar = (float) Math.round(avgStar);
		 } else {
			 if(avgStar > (Math.round(avgStar) + 0.7)) {
				 avgStar = (float) Math.ceil(avgStar);
			 } else {
				 avgStar = (float) (Math.round(avgStar) + 0.5);
			 }
		 }
		return avgStar;
	}
}
