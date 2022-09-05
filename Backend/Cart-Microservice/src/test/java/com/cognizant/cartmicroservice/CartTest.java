package com.cognizant.cartmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.cognizant.cartmicroservice.entity.Cart;

public class CartTest {
	
	@Test
	void testCartConstructor() {
		Cart cart = new Cart();
		cart.setProductId(1);
		cart.setZipcode("603301");
		cart.setDeliveryDate(LocalDate.of(2021, 05, 04));
		cart.setVendorId(1);
		cart.setCustomerId(1);
		cart.setQuantity(1);
		assertEquals(1, cart.getProductId());
		assertEquals("603301", cart.getZipcode());
		assertEquals(LocalDate.of(2021, 05, 04),cart.getDeliveryDate());
		assertEquals(1,cart.getVendorId());
		assertEquals(1, cart.getQuantity());
	}

}
