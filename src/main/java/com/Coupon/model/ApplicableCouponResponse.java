package com.Coupon.model;

import java.util.List;
//Response object for listing applicable coupons and their discounts.
public class ApplicableCouponResponse {
	 
		private List<CouponDiscount> applicableCoupons;   // List of applicable coupons with discount information

		
	    public List<CouponDiscount> getApplicableCoupons() {
			return applicableCoupons;
		}


		public void setApplicableCoupons(List<CouponDiscount> applicableCoupons) {
			this.applicableCoupons = applicableCoupons;
		}


		// Inner class representing the discount details of a coupon
	    public static class CouponDiscount {
	    private Long couponId;     // The ID of the applicable coupon
	    private String type;       // Coupon type
	    private double discount;   // Discount amount for this coupon
		public Long getCouponId() {
			return couponId;
		}
		public void setCouponId(Long couponId) {
			this.couponId = couponId;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getDiscount() {
			return discount;
		}
		public void setDiscount(double discount) {
			this.discount = discount;
		}
	    
	    
	}
}
