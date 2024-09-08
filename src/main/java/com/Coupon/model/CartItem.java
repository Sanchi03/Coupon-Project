package com.Coupon.model;

//Represents each item in the cart with its price and discount.
public class CartItem {
	   private Long productId;        // The ID of the product
	    private int quantity;          // Quantity of the product in the cart
	    private double price;          // Price per unit of the product
	    private double totalDiscount;  // Discount applicable on this specific item
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getTotalDiscount() {
			return totalDiscount;
		}
		public void setTotalDiscount(double totalDiscount) {
			this.totalDiscount = totalDiscount;
		}

	    
}
