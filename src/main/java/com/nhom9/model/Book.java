/*
 * package fa.mockproject.model;
 * 
 * import java.math.BigDecimal; import java.time.LocalDate; import
 * java.util.UUID;
 * 
 * import javax.validation.constraints.DecimalMin; import
 * javax.validation.constraints.Min; import
 * javax.validation.constraints.NotBlank; import
 * javax.validation.constraints.NotNull; import
 * javax.validation.constraints.PastOrPresent;
 * 
 * import org.springframework.format.annotation.DateTimeFormat;
 * 
 * public class Book { private UUID bookId;
 * 
 * @NotBlank private String bookName;
 * 
 * @NotBlank private String author; private String discription;
 * 
 * @Min(value = 1) private int quantity;
 * 
 * @NotNull
 * 
 * @DecimalMin(value = "0.0", inclusive = false) private BigDecimal price;
 * private BigDecimal totalPay;
 * 
 * @PastOrPresent
 * 
 * @DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate publishedDate;
 * private int totalRating; private byte[] avatar; private LocalDate addDate;
 * 
 * @NotNull private UUID genreId;
 * 
 * public Book() { super(); }
 * 
 * public Book(UUID bookId, String bookName, String author, String discription,
 * int quantity, BigDecimal price, BigDecimal totalPay, LocalDate publishedDate,
 * int totalRating, byte[] avatar, LocalDate addDate, UUID genreId) { super();
 * this.bookId = bookId; this.bookName = bookName; this.author = author;
 * this.discription = discription; this.quantity = quantity; this.price = price;
 * this.totalPay = totalPay; this.publishedDate = publishedDate;
 * this.totalRating = totalRating; this.avatar = avatar; this.addDate = addDate;
 * this.genreId = genreId; }
 * 
 * public UUID getBookId() { return bookId; }
 * 
 * public void setBookId(UUID bookId) { this.bookId = bookId; }
 * 
 * public String getBookName() { return bookName; }
 * 
 * public void setBookName(String bookName) { this.bookName = bookName; }
 * 
 * public String getAuthor() { return author; }
 * 
 * public void setAuthor(String author) { this.author = author; }
 * 
 * public String getDiscription() { return discription; }
 * 
 * public void setDiscription(String discription) { this.discription =
 * discription; }
 * 
 * public int getQuantity() { return quantity; }
 * 
 * public void setQuantity(int quantity) { this.quantity = quantity; }
 * 
 * public BigDecimal getPrice() { return price; }
 * 
 * public void setPrice(BigDecimal price) { this.price = price; }
 * 
 * public BigDecimal getTotalPay() { return totalPay; }
 * 
 * public void setTotalPay(BigDecimal totalPay) { this.totalPay = totalPay; }
 * 
 * // * public LocalDate getPublishedD?ate() { return publishedDate; }
 * 
 * public void setPublishedDate(LocalDate publishedDate) { this.publishedDate =
 * publishedDate; }
 * 
 * public int getTotalRating() { return totalRating; }
 * 
 * public void setTotalRating(int totalRating) { this.totalRating = totalRating;
 * }
 * 
 * public byte[] getAvatar() { return avatar; }
 * 
 * public void setAvatar(byte[] avatar) { this.avatar = avatar; }
 * 
 * public LocalDate getAddDate() { return addDate; }
 * 
 * public void setAddDate(LocalDate addDate) { this.addDate = addDate; }
 * 
 * public UUID getGenreId() { return genreId; }
 * 
 * public void setGenreId(UUID genreId) { this.genreId = genreId; }
 * 
 * }
 */
package com.nhom9.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

public class Book {
	private UUID bookId;
	@NotBlank
	private String bookName;
	private String discription;
	@NotNull
	private Integer quantity;
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal price;
	private BigDecimal totalPay;
	@NotNull
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	private int totalRating;
	private byte[] avatar;
	private LocalDate addDate;
	@NotNull
	private UUID genreId;

	public Book() {
		super();
	}

	public Book(UUID bookId, String bookName, String discription, int quantity, BigDecimal price,
			BigDecimal totalPay, LocalDate publishedDate, int totalRating, byte[] avatar, LocalDate addDate,
			UUID genreId) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.discription = discription;
		this.quantity = quantity;
		this.price = price;
		this.totalPay = totalPay;
		this.publishedDate = publishedDate;
		this.totalRating = totalRating;
		this.avatar = avatar;
		this.addDate = addDate;
		this.genreId = genreId;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getQuantity() {
		return quantity != null ? quantity : 0;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public LocalDate getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDate addDate) {
		this.addDate = addDate;
	}

	public UUID getGenreId() {
		return genreId;
	}

	public void setGenreId(UUID genreId) {
		this.genreId = genreId;
	}

}

