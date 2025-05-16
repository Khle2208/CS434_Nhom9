package com.nhom9.model;

import java.util.UUID;

public class DeliveryInformation {
	private UUID deliveredInformationId;
    private String phone;
    private UUID userId;
    
    
	public DeliveryInformation() {
		super();
	}
	public DeliveryInformation(UUID deliveredInformationId, String phone, UUID userId) {
		super();
		this.deliveredInformationId = deliveredInformationId;
		this.phone = phone;
		this.userId = userId;
	}
	public UUID getDeliveredInformationId() {
		return deliveredInformationId;
	}
	public void setDeliveredInformationId(UUID deliveredInformationId) {
		this.deliveredInformationId = deliveredInformationId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
    
    
    
}
