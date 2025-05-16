package com.nhom9.model;

import java.util.UUID;

public class BookImage {
	private UUID imageId;
	private UUID bookId;
	private String nameImage;
	private byte[] imageData;
	
	
	public BookImage() {
		super();
	}
	
	
	public BookImage(UUID imageId ,UUID bookId, String nameImage, byte[] imageData) {
		super();
		this.imageId = imageId;
		this.bookId = bookId;
		this.nameImage = nameImage;
		this.imageData = imageData;
	}
	

	public UUID getImageId() {
		return imageId;
	}
	public void setImageId(UUID imageId) {
		this.imageId = imageId;
	}
	
	public UUID getBookId() {
		return bookId;
	}
	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}
	public String getNameImage() {
		return nameImage;
	}
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	
	
}
