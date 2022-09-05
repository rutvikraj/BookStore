package com.cognizant.cartmicroservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.cartmicroservice.Repository.CartRepository;
import com.cognizant.cartmicroservice.entity.Cart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CartDAOImpl implements CartDAO {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart existsDao(Integer customerId, Integer productId) {
		
		log.info("To check the Cart is available from exitsDao method");
		return cartRepository.exists(customerId, productId);
	}
	
	//vk
	@Override
	public Cart existsDao(Integer customerId, Integer productId, Integer vendorId) {
		
		log.info("To check the Cart is available from exitsDao method");
		return cartRepository.exists(customerId, productId, vendorId);
	}
	
	
	

	@Override
	public void saveCartDao(Cart cart) {
		log.info("Cart Saved Successfully from saveCartDao method");
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> getCartListDao(Integer customerId) {
		log.info("Get Cart Details from getCartListDao method");
		return cartRepository.getCartList(customerId);
	}

//	@Override
//	public void delete(Integer id) {
//		// TODO Auto-generated method stub
//		System.out.println("in delete");
//	}

	
}
