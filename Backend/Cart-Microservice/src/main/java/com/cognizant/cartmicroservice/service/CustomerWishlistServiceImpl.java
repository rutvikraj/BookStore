package com.cognizant.cartmicroservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.cartmicroservice.dao.CustomerWishlistDAO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistRequestDTO;
import com.cognizant.cartmicroservice.dto.StatusDTO;
import com.cognizant.cartmicroservice.entity.CustomerWishlist;
import com.cognizant.cartmicroservice.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerWishlistServiceImpl implements CustomerWishlistService {

	@Autowired
	private CustomerWishlistDAO customerWishlistDAOImpl;
	
	@Autowired
	private ProductService productServiceImpl;

	@Override
	public void saveCustomerWishlist(CustomerWishlist customerWishlist) {
		log.info("Save CustomerWishlist from CustomerWishlistServiceImpl method");
		customerWishlistDAOImpl.saveCustomerWishlistDao(customerWishlist);
	}

	@Override
	public CustomerWishlist existsCustomerWishlist(Integer customerId, Integer productId) {
		log.info("To check exiting CustomerWishlist from existsCustomerWishlist service method");
		return customerWishlistDAOImpl.existsCustomerWishlistDao(customerId, productId);
	}
	
	@Override
	public List<CustomerWishlist> getCustomerWishlistById(Integer customerId) {
		log.info("Get Customer Wishlist by Id from getCustomerListById service method");
		return customerWishlistDAOImpl.getCustomerWishlistByIdDao(customerId);
		
	}

	@Override
	public StatusDTO addToCustomerWishList(CustomerWishlistRequestDTO customerWishlistRequestDTO) {

		log.info("wishlish call save starts");
		CustomerWishlist wish = new CustomerWishlist(customerWishlistRequestDTO.getProductId(),
				customerWishlistRequestDTO.getQuantity(), LocalDate.now(), customerWishlistRequestDTO.getCustomerId());
		Integer customerId = customerWishlistRequestDTO.getCustomerId();
		Integer productId = customerWishlistRequestDTO.getProductId();
		CustomerWishlist wishTemp = existsCustomerWishlist(customerId,productId);
		if (wishTemp != null) {
			wishTemp.setQuantity(wishTemp.getQuantity() + customerWishlistRequestDTO.getQuantity());
			wish = wishTemp;
		}
		saveCustomerWishlist(wish);
		log.info("saved details to wishlist successfully");
		return new StatusDTO("SuccessFully Added to WishList");

	}

	@Override
	public List<CustomerWishlistDTO> getAllWishlist(Integer customerId) {
		log.info("Get Wishlist from getAllWishList Service method");
		List<CustomerWishlistDTO> list = new ArrayList<>();
		for (CustomerWishlist wish : getCustomerWishlistById(customerId)) {
			Product product = productServiceImpl.getProductbyId(wish.getProductId());
			list.add(new CustomerWishlistDTO(wish.getWishListId(), wish.getQuantity(), wish.getAddedToWishlistDate(),
					wish.getCustomerId(), product));
			log.info("wishlist are returned of customer id "+customerId);
		}
		return list;
	}

}
