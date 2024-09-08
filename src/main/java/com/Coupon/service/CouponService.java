package com.Coupon.service;

import java.util.List;

import com.Coupon.model.Cart;
import com.Coupon.model.Coupon;

public interface CouponService {
	public List<Coupon> getAllCoupons();

	void saveCoupon(Coupon coupon);

	Coupon getCoupon(Long id);

	void updateCoupon(Long id, Coupon coupon);

	void deleteCoupon(Long id);

	
}
