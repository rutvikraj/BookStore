package com.cognizant.cartmicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.cartmicroservice.entity.Vendor;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class VendorServiceImplTest {
	
	@Autowired
	VendorService vendorService;
	
	@MockBean
	VendorServiceImpl vendorServiceImpl;
	
	@Test
	 void findVendorById()
		{
			Vendor vendor = new Vendor(2, "Amazon", 30.5, (float) 5.0);
			Integer vendorId = 1;
			when(vendorServiceImpl.getVendorById(vendorId)).thenReturn(vendor);
			assertEquals(vendorService.getVendorById(vendorId), vendor);
		}
	
	

}
