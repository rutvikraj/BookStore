package com.cognizant.vendormicroservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.vendormicroservice.Repository.VendorRepository;
import com.cognizant.vendormicroservice.entity.Vendor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VendorDAOImpl implements VendorDAO {
	
	@Autowired
	private VendorRepository vendorRepository;
	@Override
	public Vendor getVendorDetailsByIdDao(Integer vendorId) {
		log.info("Get Vendor details from Vendor DAO method");
		Vendor dao = vendorRepository.findById(vendorId).orElse(null);
		return dao;
		
	}

}
