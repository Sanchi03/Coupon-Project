1. Introduction
This project is a RESTful API for managing and applying discount coupons for an e-commerce platform. The API supports multiple types of coupons:
Cart-wise: Discounts are applied to the total cart value.
Product-wise: Discounts applied to specific products in the cart.
BxGy (Buy X Get Y): Buy X number of products from a set, and get Y number of products for free.
The API also provides functionality for creating, updating, retrieving, and deleting coupons.

2. Project Setup
Requirements:
Java 8 or higher
Maven
PostgreSQL (or Oracle, MySQL depending on your choice)

3. Technologies Used
Spring Boot: Framework for building the API.
JDBC Template: For database interactions (alternative: Spring Data JPA).
PostgreSQL: Database for storing coupon and cart information.
JSON Library: To handle JSON details for coupon types.
Maven: Build tool.

4. Error Handling
The API provides basic error handling for:
Coupon Not Found: Returns 404 if the coupon does not exist.
Invalid Input: Returns 400 for invalid coupon details or cart payloads.
Coupon Expired: If a coupon has expired, it will not be applied.

5. Implemented Cases
The following cases have been implemented based on the project requirements:
-Implemented Coupon Types:
  1. Cart-wise Coupon
  2. Product-wise Coupon
  3. BxGy (Buy X, Get Y) Coupon
-Implemented API Endpoints:
  1. POST /coupons: Create a new coupon.
  2. GET /coupons: Retrieve all coupons.
  3. GET /coupons/{id}: Retrieve a specific coupon by its ID.
  4. PUT /coupons/{id}: Update a specific coupon by its ID.
  5. DELETE /coupons/{id}: Delete a specific coupon by its ID.
  6. POST /apply-coupon/{id}: Apply a specific coupon to the cart and return the
     updated cart with discounted prices for each item.

6. Unimplemented Cases
   POST /applicable-coupons: Fetch all applicable coupons for a given cart and
   calculate the total discount that will be applied by each coupon.

8. Assumptions:
  1. Coupons are valid only if they are not expired.
  2. Each coupon type has specific conditions for applicability (like thresholds, product IDs, and quantities).
  3. The repetition limit for BxGy coupons is enforced.

8. Limitations:
  1. The current system supports only three coupon types (cart-wise, product-wise, and BxGy). Additional coupon types, such as flat-rate discounts, loyalty-based coupons, or user-specific coupons, are not implemented yet.  2. No implementation for tracking user-specific coupons or usage limits (like "first 100 users").
  2. While the API handles basic errors (e.g., invalid coupon ID, expired coupon), more complex error handling—like partial application of coupons in case of product unavailability—has not been implemented.
  3. The current implementation assumes that all coupons are available to all customers. There are no user-specific coupons.

9. Future enhancements:
  1. The system is designed such that more coupons of different types can be added with the help of the Coupon table.
  2. Implement usage tracking for coupons to limit how many times a specific user can use a coupon.
