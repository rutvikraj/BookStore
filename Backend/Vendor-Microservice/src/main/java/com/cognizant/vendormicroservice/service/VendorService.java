package com.cognizant.vendormicroservice.service;

import java.util.List;

import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.Vendor;

public interface VendorService {

	VendorDTO getVendorDetailsById(Integer vendorId);
	
	List<Vendor> totalVend(List<Integer> ids);

}