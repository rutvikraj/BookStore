package com.cognizant.cartmicroservice.dao;

import java.util.List;

import com.cognizant.cartmicroservice.entity.Cart;

public interface CartDAO {

	Cart existsDao(Integer customerId, Integer productId);

	void saveCartDao(Cart cart);

	List<Cart> getCartListDao(Integer customerId);
	
//	void delete(Integer id);
	Cart existsDao(Integer customerId, Integer productId, Integer vendorId);

}