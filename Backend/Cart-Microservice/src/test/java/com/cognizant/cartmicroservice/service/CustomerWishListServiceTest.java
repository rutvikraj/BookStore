package com.cognizant.cartmicroservice.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.cartmicroservice.Repository.CustomerWishlistRepository;
import com.cognizant.cartmicroservice.dto.CustomerWishlistDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistRequestDTO;
import com.cognizant.cartmicroservice.dto.StatusDTO;
import com.cognizant.cartmicroservice.entity.CustomerWishlist;
import com.cognizant.cartmicroservice.entity.Product;

@SpringBootTest
public class CustomerWishListServiceTest {

	@MockBean
	private CustomerWishlistRepository customerWishlistRepository;
	
	@MockBean
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private CustomerWishlistServiceImpl customerWishlistServiceImpl;
	
	 @Test
	 void testAddToCustomerWishList() {
		  CustomerWishlistRequestDTO customerWishList = new CustomerWishlistRequestDTO(1, 2, 10);
			CustomerWishlist wish = new CustomerWishlist(2, 8, LocalDate.of(2021, 05, 04), 1);
			when(customerWishlistRepository.save(wish)).thenReturn(wish);
			assertEquals(customerWishlistServiceImpl.addToCustomerWishList(customerWishList), new StatusDTO("SuccessFully Added to WishList"));
	}

	@Test
	 void testGetCartDetails() {
		CustomerWishlist wish = new CustomerWishlist(2, 8, LocalDate.of(2021, 05, 04), 1);
		Integer customerId = 2;
		CustomerWishlist customerWishlist = new CustomerWishlist(2, 8, LocalDate.of(2021, 05, 04), 1);
		List<CustomerWishlist> list = new ArrayList<>();
		list.add(customerWishlist);
		Product product = new Product(2, "Heaphone", 500, "description", "imageName", 5,0);
		CustomerWishlistDTO customerWishlistDTO = new CustomerWishlistDTO(null, 8, LocalDate.of(2021, 05, 04), 1,
				product);
		List<CustomerWishlistDTO> newList = new ArrayList<CustomerWishlistDTO>();
		newList.add(customerWishlistDTO);
		when(customerWishlistRepository.getCustomerWishlistById(customerId)).thenReturn(list);
		when(productServiceImpl.getProductbyId(wish.getProductId())).thenReturn(product);
		System.err.println(customerWishlistServiceImpl.getAllWishlist(customerId));
		System.err.println(newList);
		assertEquals(customerWishlistServiceImpl.getAllWishlist(customerId), newList);
	}
	
	
}
