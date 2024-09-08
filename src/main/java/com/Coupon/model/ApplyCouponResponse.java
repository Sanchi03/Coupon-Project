package com.Coupon.model;

//Response object after applying a coupon to a cart.
public class ApplyCouponResponse {
	private Cart updatedCart;   // The updated cart with discounted prices

	public ApplyCouponResponse(String string) {
		
	}

	public ApplyCouponResponse() {
		
	}

	public Cart getUpdatedCart() {
		return updatedCart;
	}

	public void setUpdatedCart(Cart updatedCart) {
		this.updatedCart = updatedCart;
	}
	
	
}
