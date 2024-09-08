package com.Coupon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Coupon.CouponRepo.CouponRepoImpl;
import com.Coupon.model.Cart;
import com.Coupon.model.CartItem;
import com.Coupon.model.Coupon;
@Service
public class CouponSvcImpl implements CouponService {

	@Autowired
	CouponRepoImpl couponRepoImpl;
	
	@Autowired
	public Coupon coupon;
	
	
	@Override
	public List<Coupon> getAllCoupons() { // Method to retrieve all coupons
		coupon=(Coupon) couponRepoImpl.getAllCoupons();
		coupon.setId(coupon.getId());
		coupon.setType(coupon.getType());
		coupon.setDetails(coupon.getDetails());
        return (List<Coupon>) coupon; 
    }

	//Create a new coupon
	@Override
	public void saveCoupon(Coupon coupon) {
		coupon.setType(coupon.getType());
		coupon.setDetails(coupon.getDetails());
		couponRepoImpl.saveCoupon(coupon);
		
	}

	//Retrieve coupon by id
	@Override
	public Coupon getCoupon(Long id) {
		return couponRepoImpl.getCouponById(id);
	}

	//update coupon by id
	@Override
	public void updateCoupon(Long id, Coupon coupon) {
		
		    int rowsAffected = couponRepoImpl.updateCoupon(id, coupon.getType(), coupon.getDetails());
		    if (rowsAffected == 0) {
		        throw new RuntimeException("Coupon not found with id: " + id);
		    }
		}
	
	//delete coupon by id
	@Override
	public void deleteCoupon(Long id) {
		int rowsAffected = couponRepoImpl.deleteCoupon(id);
	    if (rowsAffected == 0) {
	        throw new RuntimeException("Coupon not found with id: " + id);
	    }
		
	}

	//apply cart-wise coupon
	public void applyCartWiseDiscount(Coupon coupon, Cart cart) {
	    JSONObject details = new JSONObject(coupon.getDetails());
	    double threshold = details.getDouble("threshold");
	    double discountPercent = details.getDouble("discount");

	    if (cart.getTotalPrice() > threshold) {
	        double discount = cart.getTotalPrice() * (discountPercent / 100);
	        cart.setTotalDiscount(discount);  // Set the discount on the cart
	        cart.setFinalPrice(cart.getTotalPrice() - discount);  // Apply the discount
	    }
	}

	//apply product-wise coupon
	public void applyProductWiseDiscount(Coupon coupon, Cart cart) {
	    JSONObject details = new JSONObject(coupon.getDetails());
	    long productId = details.getLong("product_id");
	    double discountPercent = details.getDouble("discount");

	    for (CartItem item : cart.getItems()) {
	        if (item.getProductId() == productId) {
	            // Apply discount on this specific product
	            double productDiscount = item.getPrice() * (discountPercent / 100) * item.getQuantity();
	            item.setTotalDiscount(productDiscount);  // Set discount on this item
	            cart.setTotalDiscount(cart.getTotalDiscount() + productDiscount);  // Add to cart's total discount
	        }
	    }
	    cart.setFinalPrice(cart.getTotalPrice() - cart.getTotalDiscount());
	}

	//apply BxGy coupon
	public void applyBxGyDiscount(Coupon coupon, Cart cart) {
	    JSONObject details = new JSONObject(coupon.getDetails());
	    JSONArray buyProducts = details.getJSONArray("buy_products");
	    JSONArray getProducts = details.getJSONArray("get_products");
	    int repetitionLimit = details.getInt("repition_limit");

	    // Calculate how many times the 'buy' condition is met
	    Map<Long, Integer> buyProductMap = new HashMap<>();
	    for (CartItem item : cart.getItems()) {
	        for (int i = 0; i < buyProducts.length(); i++) {
	            JSONObject buyProduct = buyProducts.getJSONObject(i);
	            long buyProductId = buyProduct.getLong("product_id");
	            int requiredQuantity = buyProduct.getInt("quantity");

	            if (item.getProductId() == buyProductId) {
	                int buyCount = item.getQuantity() / requiredQuantity;
	                buyProductMap.put(buyProductId, buyCount);
	            }
	        }
	    }

	    // Calculate how many times the 'get' products should be free
	    int minBuyCount = buyProductMap.values().stream().min(Integer::compare).orElse(0);
	    minBuyCount = Math.min(minBuyCount, repetitionLimit);  // Apply repetition limit

	    // Apply free product discount for 'get' products
	    for (CartItem item : cart.getItems()) {
	        for (int i = 0; i < getProducts.length(); i++) {
	            JSONObject getProduct = getProducts.getJSONObject(i);
	            long getProductId = getProduct.getLong("product_id");
	            int freeQuantity = getProduct.getInt("quantity") * minBuyCount;

	            if (item.getProductId() == getProductId) {
	                double freeProductValue = item.getPrice() * Math.min(freeQuantity, item.getQuantity());
	                item.setTotalDiscount(freeProductValue);  // Mark the product as free
	                cart.setTotalDiscount(cart.getTotalDiscount() + freeProductValue);  // Add to total discount
	            }
	        }
	    }

	    cart.setFinalPrice(cart.getTotalPrice() - cart.getTotalDiscount());  // Adjust the final price
	}

	

		
	
}
