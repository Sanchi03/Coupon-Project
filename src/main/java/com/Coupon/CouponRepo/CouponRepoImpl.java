package com.Coupon.CouponRepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Coupon.constant.QueryConstant;
import com.Coupon.model.Coupon;

@Repository
public class CouponRepoImpl implements CouponRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	Coupon coupon;
	
	@Override
	public List<Coupon> getAllCoupons(){
		//This method fetches all coupons from db
		try {
			System.out.println("Retrieving all the coupons");
			coupon=jdbcTemplate.queryForObject(QueryConstant.GET_ALL_COUPONS, new Object[] {coupon.getId(),coupon.getType(),coupon.getDetails()}, new BeanPropertyRowMapper<Coupon>(Coupon.class));
			return (List<Coupon>) coupon;
		
		}catch(Exception e) {
			System.out.printf("Couldn't fetch the coupon",e);
		}
		return (List<Coupon>) coupon;
		
		
	}

	//Create a new coupon using insert query
	@Override
	public void saveCoupon(Coupon coupon) {
		try {
			int insertcount= jdbcTemplate.update(QueryConstant.CREATE_NEW_COUPON, new Object[] {coupon.getType(), coupon.getDetails()});
		 
			if(insertcount>0) {
				System.out.println("Coupon created successfully");
				}
			}catch(Exception e) {
				System.out.println("Coupon creation failed");
			}
		
	}

	//Fetch coupon from db by id
	@Override
	public Coupon getCouponById(Long id) {
		try {
		coupon=jdbcTemplate.queryForObject(QueryConstant.GET_COUPON_BY_ID, new Object[] {coupon.getId()}, new BeanPropertyRowMapper<Coupon>(Coupon.class));
		coupon.setId(coupon.getId());
		coupon.setType(coupon.getType());
		coupon.setDetails(coupon.getDetails());
		return coupon;
		}catch(Exception e) {
			System.out.println("Couldn't fetch the coupon by Id");
		}
		return coupon;
	}

	//update coupon by id
	@Override
	public int updateCoupon(Long id, String type, Map<String, Object> details) {
		return jdbcTemplate.update(QueryConstant.UPDATE_COUPON_BY_ID, new Object[]{coupon.getType(),coupon.getDetails(),coupon.getId()});


	}
 
	//delete a coupon by id
	@Override
	public int deleteCoupon(Long id) {
		 return jdbcTemplate.update(QueryConstant.DELETE_A_COUPON, coupon.getId());
	}

}
