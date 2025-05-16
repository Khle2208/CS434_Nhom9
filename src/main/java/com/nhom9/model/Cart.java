package com.nhom9.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Cart {
	private UUID cartId;
    private UUID userId;
    private BigDecimal price;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private String status;
    
    
    public Cart() {
    	super();
    }
	public Cart(UUID cartId, UUID userId, BigDecimal price, BigDecimal totalDiscount, BigDecimal totalPrice,
			String status) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.price = price;
		this.totalDiscount = totalDiscount;
		this.totalPrice = totalPrice;
		this.status = status;
	}
	public UUID getCartId() {
		return cartId;
	}
	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
