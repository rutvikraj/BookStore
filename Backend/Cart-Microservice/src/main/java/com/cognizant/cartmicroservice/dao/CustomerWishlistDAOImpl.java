package com.cognizant.cartmicroservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.cartmicroservice.Repository.CustomerWishlistRepository;
import com.cognizant.cartmicroservice.entity.CustomerWishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerWishlistDAOImpl implements CustomerWishlistDAO {
	
	@Autowired
	private CustomerWishlistRepository customerWishlistRepository;
	@Override
	public void saveCustomerWishlistDao(CustomerWishlist customerWishlist) {
		log.info("CustomerWishList Saved Successfully from saveCustomerWishlistDao method");
		customerWishlistRepository.save(customerWishlist);
		
		
	}
	@Override
	public CustomerWishlist existsCustomerWishlistDao(Integer customerId, Integer productId) {
		log.info("Checked existing CustomerWishlist from existsCustomerWishlistDao method");
		return customerWishlistRepository.existsCustomerWishlist(customerId, productId);
		
	}
	@Override
	public List<CustomerWishlist> getCustomerWishlistByIdDao(Integer customerId) {
		log.info("Get CustomerListById from getCustomerWishlistByIdDao method");
		return customerWishlistRepository.getCustomerWishlistById(customerId);
	}

}
