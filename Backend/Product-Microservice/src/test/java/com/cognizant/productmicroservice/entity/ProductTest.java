package com.cognizant.productmicroservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	Product product = new Product();
	
	@Test
	void testGetId() {
		product.setId(3);
		assertEquals(3, product.getId());
	}
	
	@Test
	void testGetName() {
		product.setName("Prakash");
		assertEquals("Prakash", product.getName());
	}
	
	@Test
	void testGetPrice() {
		product.setPrice(15000);
		assertEquals(15000,product.getPrice());
	}
	
	@Test
	void testGetDescription() {
		product.setDescription("Name");
		assertEquals("Name",product.getDescription());
	}
	
	@Test
	void testGetImageName() {
		product.setImageName("Image");
		assertEquals("Image",product.getImageName());
	}
	
	@Test
	void testGetRating() {
		product.setRating(4.0f);
		assertEquals(4.0f,product.getRating());
	}
	
	@Test
	void testGetNoOfUsersRated() {
		product.setNoOfUsersRated(5);
		assertEquals(5,product.getNoOfUsersRated());
	}
	

}
