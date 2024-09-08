package com.Coupon.model;

import java.util.List;
//Represents the cart with a list of items and total price.
public class Cart {
	
	private List<CartItem> items;       // List of items in the cart
    private double totalPrice;          // Total price of the cart before discount
    private double totalDiscount;       // Total discount applicable on the cart
    private double finalPrice;    		// Final price after discount

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
    
}
