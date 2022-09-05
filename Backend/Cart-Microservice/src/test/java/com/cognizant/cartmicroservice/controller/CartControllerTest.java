package com.cognizant.cartmicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.cognizant.cartmicroservice.dao.CustomerWishlistDAO;
import com.cognizant.cartmicroservice.dto.CartRequestDTO;
import com.cognizant.cartmicroservice.dto.CartResponseDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistRequestDTO;
import com.cognizant.cartmicroservice.dto.StatusDTO;
import com.cognizant.cartmicroservice.entity.Product;
import com.cognizant.cartmicroservice.entity.Vendor;
import com.cognizant.cartmicroservice.service.CartServiceImpl;
import com.cognizant.cartmicroservice.service.CustomerWishlistServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
	
	@MockBean
	private CartServiceImpl cartServiceImpl;
	
	@MockBean
	private CustomerWishlistServiceImpl customerWishlistServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testAddProductToCart() throws Exception{
		CartRequestDTO cartRequestDto = new CartRequestDTO(2, 1, "453441", 10);
		String jsonCartRequestDto = this.mapToJson(cartRequestDto);
		when(cartServiceImpl.addProductToCart(cartRequestDto)).thenReturn("Successfully added to Cart");
		MvcResult mvcResult = mockMvc
				.perform(post("/cart/addProductToCart").contentType("application/json").content(jsonCartRequestDto))
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Successfully added to Cart"));
	}
	
	@Test
	 void testGetCartList() throws Exception {
		Integer customerId = 2;
		Product product = new Product(1, "Heaphone", 500, "description", "imageName", 5,0);
		Vendor vendor = new Vendor(1, "Amazon",40,(float) 4.50);
		CartResponseDTO cartList = new CartResponseDTO(10, "453441", LocalDate.of(2021, 05, 15), 2, 10, product,
				vendor);
		List<CartResponseDTO> list = new ArrayList<CartResponseDTO>();
		list.add(cartList);
		when(cartServiceImpl.getCartList(customerId)).thenReturn(list);
		MvcResult mvcResult = mockMvc.perform(get("/cart/getCart/" + customerId)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	 void testAddToCustomerWishList() throws Exception {
		Integer customerId = 2;
		CustomerWishlistRequestDTO customerWishlist = new CustomerWishlistRequestDTO(1, 2, 10);
		String jsonCustomerWishlist = this.mapToJson(customerWishlist);
	    when(customerWishlistServiceImpl.addToCustomerWishList(customerWishlist))
				.thenReturn(new StatusDTO("SuccessFully Added to WishList"));
		MvcResult mvcResult = mockMvc.perform(
				post("/cart/addToCustomerWishlist").contentType("application/json").content(jsonCustomerWishlist))
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("SuccessFully Added to WishList"));
	}

	@Test
	 void testViewAllWishlist() throws Exception {
		Integer customerId = 2;
		Product product = new Product(1, "Heaphone", 500, "description", "imageName", 5,0);
		CustomerWishlistDTO customerWishlistDTO = new CustomerWishlistDTO(3, 10, LocalDate.of(2021, 05, 04), 1,
				product);
		List<CustomerWishlistDTO> list = new ArrayList<CustomerWishlistDTO>();
		list.add(customerWishlistDTO);
		when(customerWishlistServiceImpl.getAllWishlist(customerId)).thenReturn(list);
		MvcResult mvcResult = mockMvc.perform(get("/cart/getWishlist/" + customerId)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
