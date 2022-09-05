package com.cognizant.cartmicroservice.service;

import java.util.List;

import com.cognizant.cartmicroservice.dto.CartDTO;
import com.cognizant.cartmicroservice.dto.CartRequestDTO;
import com.cognizant.cartmicroservice.dto.CartResponseDTO;
import com.cognizant.cartmicroservice.entity.Cart;

public interface CartService {

	Cart exists(Integer customerId, Integer productId);

	void saveCart(Cart cart);

	String addProductToCart(CartRequestDTO cartRequestDTO);

	List<CartResponseDTO> getCartList(Integer customerId);
	
	Cart exists(Integer customerId, Integer productId, Integer vendorId);

}