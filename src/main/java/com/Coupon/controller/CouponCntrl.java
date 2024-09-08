package com.Coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Coupon.model.ApplyCouponResponse;
import com.Coupon.model.Cart;
import com.Coupon.model.Coupon;
import com.Coupon.service.CouponSvcImpl;



@RestController
@RequestMapping("/coupon")
public class CouponCntrl {
	
	@Autowired
	CouponSvcImpl couponSvcImpl;
	
	@Autowired
	Coupon coupon;
	
	@Autowired
	Cart cart;
	@Autowired
	ApplyCouponResponse applyCouponResponse;
	
	//Mapping for fetching all coupons
	@GetMapping("/getcoupons")
	@ResponseBody
	public ResponseEntity<List<Coupon>> getAllCoupons() {   //This method returns all the coupons fetched.
       List<Coupon> coupon =  couponSvcImpl.getAllCoupons();
       return ResponseEntity.ok(coupon);
    }
	
	//Mapping for creating a new coupon
	 @PostMapping("/create")
	    public ResponseEntity<String> createCoupon(@RequestBody Coupon coupon) {
		 coupon.getType();
		 coupon.getDetails();
		 couponSvcImpl.saveCoupon(coupon);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Coupon created successfully.");
	    }
	 //Fetch a coupon by Id
	 @GetMapping("/{id}")
	    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
	        Coupon coupon = couponSvcImpl.getCoupon(id);
	        return ResponseEntity.ok(coupon);
	    }
	 
	 //Update coupon by id
	 @PutMapping("/{id}")
	 public ResponseEntity<String> updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
		 couponSvcImpl.updateCoupon(id, coupon);
	     return ResponseEntity.ok("Coupon updated successfully");
	 }
	 //Delete coupon by id
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteCoupon(@PathVariable Long id) {
		 couponSvcImpl.deleteCoupon(id);
	     return ResponseEntity.ok("Coupon deleted successfully");
	 }
	 /* Apply a specific coupon to the cart and return the updated cart with 
	  discounted prices for each item.	*/
	 @PostMapping("/apply-coupon/{couponId}")
	 public ResponseEntity<ApplyCouponResponse>applyCoupon(@PathVariable Long couponId, @RequestBody Cart cart) {
	     Coupon coupon = couponSvcImpl.getCoupon(couponId);
	     if (coupon == null) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	     }

	     // Apply the discount logic based on coupon type
	     switch (coupon.getType()) {
	         case "cart-wise":
	        	 couponSvcImpl.applyCartWiseDiscount(coupon, cart);
	             break;
	         case "product-wise":
	        	 couponSvcImpl.applyProductWiseDiscount(coupon, cart);
	             break;
	         case "bxgy":
	        	 couponSvcImpl.applyBxGyDiscount(coupon, cart);
	             break;
	         default:
	             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                     .body(new ApplyCouponResponse("Unsupported coupon type"));
	     }

	     ApplyCouponResponse response = new ApplyCouponResponse();
	     response.setUpdatedCart(cart);  // Return the updated cart with applied discounts
	     return ResponseEntity.ok(response);
	 }

	
	
}
