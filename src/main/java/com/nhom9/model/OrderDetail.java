package com.nhom9.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDetail {
	private UUID orderDetailId;
	private UUID orderId;
	private UUID bookId;
	private String bookName;
	private BigDecimal price;
	private int quantityOrder;
	private BigDecimal discount;
	private BigDecimal totalPrice;
	private byte[] avatar;
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(UUID orderDetailId, UUID orderId, UUID bookId, String bookName, BigDecimal price,
			int quantityOrder, BigDecimal discount, BigDecimal totalPrice, byte[] avatar) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;
		this.quantityOrder = quantityOrder;
		this.discount = discount;
		this.totalPrice = totalPrice;
		this.avatar = avatar;
	}

	public UUID getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(UUID orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	

}
