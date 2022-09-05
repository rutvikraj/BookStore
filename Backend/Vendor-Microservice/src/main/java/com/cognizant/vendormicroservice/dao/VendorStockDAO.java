package com.cognizant.vendormicroservice.dao;

import java.util.List;

import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.entity.VendorStock;

public interface VendorStockDAO {

	Vendor getBestVendorDao(Integer productId, Integer quantity);

	void saveVendorDetailsDao(VendorStock vendorStock);

	int getMaxStockCountDao(Integer productId);

	List<Integer> getvendorIdsForGivenProductDao(Integer productId);

	List<Integer> getvendorIdsWithAvailableQuantityDao(Integer productId, int quantity);

	VendorStock getVendorStockDetailsDao(Integer productId, Integer vendorId);
	
	List<VendorStock> getAllVendorStock();

}