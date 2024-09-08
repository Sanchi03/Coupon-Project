package com.Coupon.constant;

public class QueryConstant {
	public static final String GET_ALL_COUPONS="SELECT * from coupons";
	public static final String CREATE_NEW_COUPON="INSERT INTO coupons (type, details) VALUES (?, cast(? as jsonb))";
	public static final String GET_COUPON_BY_ID="SELECT * FROM coupons WHERE id = ?";
	public static final String UPDATE_COUPON_BY_ID="UPDATE coupons SET type = ?, details = cast(? as jsonb) WHERE id = ?";
	public static final String DELETE_A_COUPON="DELETE FROM coupons WHERE id = ?";

}
