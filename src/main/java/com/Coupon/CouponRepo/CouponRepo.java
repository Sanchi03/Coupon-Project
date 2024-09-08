package com.Coupon.CouponRepo;

import java.util.List;
import java.util.Map;

import com.Coupon.model.Coupon;

public interface CouponRepo {
	public List<Coupon> getAllCoupons();

	void saveCoupon(Coupon coupon);

	Coupon getCouponById(Long id);

	int updateCoupon(Long id, String type, Map<String, Object> details);

	int deleteCoupon(Long id);
}
