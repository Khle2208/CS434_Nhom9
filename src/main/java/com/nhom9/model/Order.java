package com.nhom9.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Order {
	private UUID orderId;
    private UUID userId;
    private Date orderDate;
    private BigDecimal price;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private String status;
    
    public Order() {
    	super();
    }
    
	public Order(UUID orderId, UUID userId, Date orderDate, BigDecimal price, BigDecimal totalDiscount,
			BigDecimal totalPrice, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.price = price;
		this.totalDiscount = totalDiscount;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
