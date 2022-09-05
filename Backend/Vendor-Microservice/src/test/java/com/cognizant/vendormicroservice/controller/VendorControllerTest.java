package com.cognizant.vendormicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.exception.ProductIdNotFoundException;
import com.cognizant.vendormicroservice.exception.QuantityLimitExceededException;
import com.cognizant.vendormicroservice.exception.VendorNotFoundException;
import com.cognizant.vendormicroservice.service.VendorServiceImpl;
import com.cognizant.vendormicroservice.service.VendorStockServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VendorControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VendorStockServiceImpl vendorStockServiceImpl;
	
	@MockBean
	private VendorServiceImpl vendorServiceImpl;

	@Test
	void testGetBestVendor() throws Exception {
		VendorDTO vendor = new VendorDTO(1, "Amazon", 30.5, (float)5.0);
		int productId = 3;
		int quantity = 10;
		when(vendorStockServiceImpl.getBestVendor(productId, quantity)).thenReturn(vendor);
		MvcResult mvcResult = mockMvc.perform(get("/vendor/getBestVendor/" + productId + "/" + quantity)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Amazon"));
	}

	@Test
	void testGetBestVendorProductIdNotFoundException() throws Exception {
		int productId = 101;
		int quantity = 10;
		when(vendorStockServiceImpl.getBestVendor(productId, quantity)).thenThrow(new ProductIdNotFoundException(
				"Product with the id [" + productId + "] is not present in the vendors stock"));
		MvcResult mvcResult = mockMvc.perform(get("/vendor/getBestVendor/" + productId + "/" + quantity)).andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString()
				.contains("Product with the id [" + productId + "] is not present in the vendors stock"));
	}

	@Test
	void testGetBestVendorQuantityLimitExceededException() throws Exception {
		int productId = 1;
		int quantity = 1000;
		when(vendorStockServiceImpl.getBestVendor(productId, quantity)).thenThrow(new QuantityLimitExceededException(
				"The Quantity You wont is more then the available quantity!, Please enter quantity of the product below limit of 1000 units"));
		MvcResult mvcResult = mockMvc.perform(get("/vendor/getBestVendor/" + productId + "/" + quantity)).andReturn();
		assertEquals(409, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains(
				"The Quantity You wont is more then the available quantity!, Please enter quantity of the product below limit of 1000 units"));
	}

	@Test
	void testGetVendor() throws Exception {
		VendorDTO vendor = new VendorDTO(1, "Amazon", 30.5, (float)5.0);
		int vendorId = 1;
		when(vendorServiceImpl.getVendorDetailsById(vendorId)).thenReturn(vendor);
		MvcResult mvcResult = mockMvc.perform(get("/vendor/getVendorDetails/" + vendorId)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Amazon"));
	}

	@Test
	void testGetVendorVendorNotFoundException() throws Exception {
		int vendorId = 50;
		when(vendorServiceImpl.getVendorDetailsById(vendorId))
				.thenThrow(new VendorNotFoundException("Vendor With id [" + vendorId + "] not found"));
		MvcResult mvcResult = mockMvc.perform(get("/vendor/getVendorDetails/" + vendorId)).andReturn();
		assertEquals(404, mvcResult.getResponse().getStatus());
		assertEquals(true,
				mvcResult.getResponse().getContentAsString().contains("Vendor With id [" + vendorId + "] not found"));
	}

}
