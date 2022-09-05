package com.cognizant.vendormicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.vendormicroservice.Repository.VendorStockRepository;
import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.entity.VendorStock;
import com.cognizant.vendormicroservice.exception.ProductIdNotFoundException;
import com.cognizant.vendormicroservice.exception.QuantityLimitExceededException;

@SpringBootTest
public class VendorStockServiceImplTest {
	
	@MockBean
	private VendorStockRepository vendorStockRepository;

	@MockBean
	private VendorServiceImpl vendorServiceImpl;

	@Autowired
	private VendorStockServiceImpl vendorStockServiceImpl;

	@Test
	void testSave() {
		VendorStock vendorStock = new VendorStock(10, 3, 3, 144, LocalDate.of(2021, 12, 11));
		when(vendorStockRepository.save(vendorStock)).thenReturn(vendorStock);
		vendorStockRepository.save(vendorStock);
		verify(vendorStockRepository).save(vendorStock);
	}

	@Test
	void testGetMaxQuantity() {
		Integer productId = 1;
		when(vendorStockRepository.getMaxStockCount(productId)).thenReturn(100);
		assertEquals(100, vendorStockServiceImpl.getMaxStockCount(productId));
	}

	@Test
	void testGetVendor() throws ProductIdNotFoundException, QuantityLimitExceededException {
		VendorDTO vendor = new VendorDTO(1, "Amazon", 30.5,(float) 5.0);
		VendorStock vendorStock = new VendorStock(10, 3, 3, 144, LocalDate.of(2021, 12, 11));
		List<Integer> vendorIdList = Arrays.asList(1);
		Integer productId = 1;
		int quantity = 2;
		when(vendorStockRepository.getvendorIdsWithAvailableQuantity(productId, quantity)).thenReturn(vendorIdList);
		when(vendorStockRepository.getvendorIdsForGivenProduct(productId)).thenReturn(vendorIdList);
		when(vendorStockServiceImpl.getMaxStockCount(productId)).thenReturn(10);
		when(vendorServiceImpl.getVendorDetailsById(1)).thenReturn(vendor);
		when(vendorStockRepository.getVendorStockDetails(productId, 1)).thenReturn(vendorStock);
		assertEquals(1, vendorStockServiceImpl.getBestVendor(productId, quantity).getVendorId());
	}

	@Test
	void testGetVendorProductIdNotFoundException() {
		List<Integer> vendorIdList = Arrays.asList();
		Integer productId = 10;
		int quantity = 2;
		when(vendorStockRepository.getvendorIdsForGivenProduct(productId)).thenReturn(vendorIdList);
		Exception exception = assertThrows(ProductIdNotFoundException.class,
				() -> vendorStockServiceImpl.getBestVendor(productId, quantity));
		assertEquals("Product with the id [" + productId + "] is not present in the vendors stock",
				exception.getMessage());
	}

	@Test
	void testGetVendorQuantityLimitExceededException() {
		List<Integer> vendorIdList = Arrays.asList(1);
		Integer productId = 1;
		int quantity = 200;
		when(vendorStockRepository.getvendorIdsWithAvailableQuantity(productId, quantity)).thenReturn(vendorIdList);
		when(vendorStockRepository.getvendorIdsForGivenProduct(productId)).thenReturn(vendorIdList);
		when(vendorStockServiceImpl.getMaxStockCount(productId)).thenReturn(10);
		Exception exception = assertThrows(QuantityLimitExceededException.class,
				() -> vendorStockServiceImpl.getBestVendor(productId, quantity));
		assertEquals(
				"The Quantity You wont is more then the available quantity!, Please enter quantity of the product below limit of [10] units",
				exception.getMessage());

	}

}
