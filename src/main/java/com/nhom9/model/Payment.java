package com.nhom9.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Payment {
	private UUID paymentId;
	private UUID userId;
	private UUID orderId;
	private Date paymentDate;
	private BigDecimal paymentAmount;
	private String paymentMethod;
	private String paymentStatus;
	private UUID addressId;
	
	
	public Payment() {
		super();
	}
	public Payment(UUID paymentId, UUID userId, UUID orderId, Date paymentDate, BigDecimal paymentAmount,
			String paymentMethod, String paymentStatus,UUID addressId) {
		super();
		this.paymentId = paymentId;
		this.userId = userId;
		this.orderId = orderId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.addressId = addressId;
	}
	public UUID getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(UUID paymentId) {
		this.paymentId = paymentId;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public UUID getAddressId() {
		return addressId;
	}
	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}
	
	
}
