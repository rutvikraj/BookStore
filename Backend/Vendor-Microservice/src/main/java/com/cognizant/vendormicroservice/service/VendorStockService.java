package com.cognizant.vendormicroservice.service;

import java.util.List;

import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.VendorStock;
import com.cognizant.vendormicroservice.exception.ProductIdNotFoundException;
import com.cognizant.vendormicroservice.exception.QuantityLimitExceededException;

public interface VendorStockService {

	void saveVendorDetails(VendorStock vendorStock);

	int getMaxStockCount(Integer productId);

	List<Integer> getvendorIdsForGivenProduct(Integer productId);

	List<Integer> getvendorIdsWithAvailableQuantity(Integer productId, int quantity);

	VendorStock getVendorStockDetails(Integer productId, Integer vendorId);

	VendorDTO getBestVendor(Integer productId, Integer quantity)
			throws ProductIdNotFoundException, QuantityLimitExceededException;
	
	VendorDTO getVendorById(Integer productId, Integer vendorId, Integer quantity)
			throws ProductIdNotFoundException, QuantityLimitExceededException;

	//ld
   VendorDTO getStock(Integer productId, Integer quantity) 
		   throws ProductIdNotFoundException, QuantityLimitExceededException;
}