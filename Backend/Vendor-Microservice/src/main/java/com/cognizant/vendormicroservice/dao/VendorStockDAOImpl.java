package com.cognizant.vendormicroservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.vendormicroservice.Repository.VendorStockRepository;
import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.entity.VendorStock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VendorStockDAOImpl implements VendorStockDAO {
	
	@Autowired
	private VendorStockRepository vendorStockRepository;

	@Override
	public Vendor getBestVendorDao(Integer productId, Integer quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveVendorDetailsDao(VendorStock vendorStock) {
		log.info("To Save Vendor Stock details from Vendor Stock Dao Method");
		vendorStockRepository.save(vendorStock);
		
		
	}

	@Override
	public int getMaxStockCountDao(Integer productId) {
		log.info("To Get Maximum stock available from Vendor Stock Dao Method");
		return vendorStockRepository.getMaxStockCount(productId);
		
		
	}

	@Override
	public List<Integer> getvendorIdsForGivenProductDao(Integer productId) {
		log.info("To Get Available Vendor Id for the given ProdcutID from Vendor Stock Dao Method");
		return vendorStockRepository.getvendorIdsForGivenProduct(productId);
	}

	@Override
	public List<Integer> getvendorIdsWithAvailableQuantityDao(Integer productId, int quantity) {
		log.info("To Get Vendor Ids who are having enough quantity of stocks from getvendorIdsWithAvailableQuantityDao method");
		return vendorStockRepository.getvendorIdsWithAvailableQuantity(productId, quantity);
	}

	@Override
	public VendorStock getVendorStockDetailsDao(Integer productId, Integer vendorId) {
		log.info("To Get Vendor Stock Details from getVendorStockDetailsDao method");
		return vendorStockRepository.getVendorStockDetails(productId, vendorId);
	}

	//vk
	@Override
	public List<VendorStock> getAllVendorStock(){
		return vendorStockRepository.findAll();
	}
	
}
