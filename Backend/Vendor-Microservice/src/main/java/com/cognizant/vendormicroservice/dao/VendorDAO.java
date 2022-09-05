package com.cognizant.vendormicroservice.dao;

import com.cognizant.vendormicroservice.entity.Vendor;

public interface VendorDAO {

	Vendor getVendorDetailsByIdDao(Integer vendorId);

}