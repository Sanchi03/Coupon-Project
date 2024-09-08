package com.Coupon.model;

import java.util.Map;
//Represents the coupon entity.
public class Coupon {
	private int id;				// Coupon ID (Primary key)
	private String type;		 // Coupon type (cart-wise, product-wise, bxgy)
	private Map<String, Object> details;		// JSON string containing the details (like thresholds, product IDs, discount percentage)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	public Map<String, Object> getDetails() {
		return details;
	}
	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}
	public Coupon() {
		super();
	}
	public Coupon(int id, String type, Map<String, Object> details) {
		super();
		this.id = id;
		this.type = type;
		this.details = details;
	}
	
	
	
	
}
