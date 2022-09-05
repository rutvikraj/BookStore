package com.cognizant.cartmicroservice.service;

import java.util.List;

import com.cognizant.cartmicroservice.dto.CustomerWishlistDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistRequestDTO;
import com.cognizant.cartmicroservice.dto.StatusDTO;
import com.cognizant.cartmicroservice.entity.CustomerWishlist;

public interface CustomerWishlistService {

	void saveCustomerWishlist(CustomerWishlist customerWishlist);

	CustomerWishlist existsCustomerWishlist(Integer customerId, Integer productId);

	List<CustomerWishlist> getCustomerWishlistById(Integer customerId);

	StatusDTO addToCustomerWishList(CustomerWishlistRequestDTO customerWishlistRequestDTO);

	List<CustomerWishlistDTO> getAllWishlist(Integer customerId);

}