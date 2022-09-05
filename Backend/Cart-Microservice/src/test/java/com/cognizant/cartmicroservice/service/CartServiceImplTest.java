package com.cognizant.cartmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.cartmicroservice.Repository.CartRepository;
import com.cognizant.cartmicroservice.dto.CartRequestDTO;
import com.cognizant.cartmicroservice.dto.CartResponseDTO;
import com.cognizant.cartmicroservice.entity.Cart;
import com.cognizant.cartmicroservice.entity.Vendor;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CartServiceImplTest {

	@MockBean
	private CartServiceImpl cartServiceMock;
	
	@MockBean
	private CartRepository cartRepository;
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@Test
	void testCartAddedSuccessfully() {
		CartRequestDTO cartRequestDTO = new CartRequestDTO(1, 1, "123", 10);
		String msg = "Successfully added to Cart";
		when(cartServiceMock.addProductToCart(cartRequestDTO)).thenReturn(msg);
		assertEquals(cartServiceMock.addProductToCart(cartRequestDTO), cartServiceImpl.addProductToCart(cartRequestDTO));
	}
	@Test
	void testVendorIsNotHavingEnoughStocks() {
		CartRequestDTO cartRequestDTO = new CartRequestDTO(1, 1, "123", 200);
		String msg = "Not Enough Stocks";
		when(cartServiceMock.addProductToCart(cartRequestDTO)).thenReturn(msg);
		assertEquals(cartServiceMock.addProductToCart(cartRequestDTO), cartServiceImpl.addProductToCart(cartRequestDTO));
	}

	@Test
	void testGetCartList() {
		Integer customerId = 700;
		List<CartResponseDTO> list = new ArrayList<>();
		assertEquals(list, cartServiceImpl.getCartList(customerId));
	}
	
	@Test
	void testExists() {
		Integer customerId = 1;
		Integer productId = 1;
		Cart cart = new Cart(1,1,"603301",LocalDate.of(2021, 05, 04),1,1,10);
		when(cartRepository.exists(customerId, productId)).thenReturn(cart);
	}
	
	@Test
	void testSaveCart() {
		Cart cart = new Cart(1,1,"603301",LocalDate.of(2021, 05, 04),1,1,10);
		when(cartRepository.save(cart)).thenReturn(cart);
		
	}

	@Test
	void testVendorIsNotEmpty() {
		Vendor vendor = new Vendor(2, "Amazon", 30.5, (float) 5.0);
		assertEquals(false, cartServiceImpl.isVendorEmpty(vendor));

	}
	
	
	
	
	
}
