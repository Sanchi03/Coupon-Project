package com.Coupon.CouponProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.Coupon.controller.CouponCntrl;

@SpringBootApplication
public class CouponProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponProjectApplication.class, args);
	}

}
