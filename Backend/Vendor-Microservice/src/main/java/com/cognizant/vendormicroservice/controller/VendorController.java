package com.cognizant.vendormicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.vendormicroservice.dto.Vendor2DTO;
import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.entity.VendorStock;
import com.cognizant.vendormicroservice.exception.ProductIdNotFoundException;
import com.cognizant.vendormicroservice.exception.QuantityLimitExceededException;
import com.cognizant.vendormicroservice.service.VendorService;
import com.cognizant.vendormicroservice.service.VendorStockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(value={"/vendor"})
public class VendorController {	
	
	@Autowired
	private VendorService vendorServiceImpl;
	
	@Autowired
	private VendorStockService vendorStockServiceImpl;
	
	
	
	@GetMapping(value={"/getVendorDetails/{vendorId}"})
	public VendorDTO getVendorDetails(@PathVariable Integer vendorId) {
		log.info("Get Vendor Details from Controller method");
		return vendorServiceImpl.getVendorDetailsById(vendorId); 
	}
	
	@GetMapping(value= {"/getBestVendor/{productId}/{quantity}"})
	public VendorDTO getBestVendor(@PathVariable Integer productId, @PathVariable Integer quantity) throws ProductIdNotFoundException, QuantityLimitExceededException {
		log.info("Get Best Vendor Details from Controller method");
		return vendorStockServiceImpl.getBestVendor(productId,quantity);
	}
	
	//LD
	@GetMapping(value= {"/getStock/{productId}/{quantity}"})
	public VendorDTO getStock(@PathVariable Integer productId, @PathVariable Integer quantity) throws ProductIdNotFoundException, QuantityLimitExceededException {
		log.info("LUCKY -> Get Best Vendor Details from Controller method");
		return vendorStockServiceImpl.getStock(productId,quantity);
	}
	
	//vk
	@GetMapping("/getVendorById/{productId}/{vendorId}/{quantity}")
	public VendorDTO getVendorById(@PathVariable Integer productId, @PathVariable Integer vendorId,
			@PathVariable Integer quantity) throws ProductIdNotFoundException, QuantityLimitExceededException {
		System.out.println("in venoder -:> "+vendorId);
		return vendorStockServiceImpl.getVendorById(productId, vendorId, quantity); 
	}
	
	@GetMapping("/getAllVendors/{productId}")
	public List<Vendor2DTO> getTotalVendor(@PathVariable Integer productId){
		System.out.println("Product ID is "+productId);
		List<Integer> ids = vendorStockServiceImpl.getvendorIdsForGivenProduct(productId);
			List<Vendor> vend = vendorServiceImpl.totalVend(ids);
			List<Vendor2DTO> vendorDTO = new ArrayList();
			
			for(Vendor v: vend) {
				VendorStock vstock = vendorStockServiceImpl.getVendorStockDetails(productId, v.getVendorId());
				Vendor2DTO vdto = new Vendor2DTO(v.getVendorId(), v.getVendorName(), v.getDeliveryCharge(), v.getRating(), vstock.getStockInHand());
				vendorDTO.add(vdto);
			}
			
			System.out.println(vendorDTO);
			return vendorDTO;
	}
	
	@GetMapping("/stockdetails/{productId}/{vendorId}")
	public VendorStock getStockdetails(@PathVariable Integer productId,@PathVariable Integer vendorId) {
		VendorStock vendorStock = vendorStockServiceImpl.getVendorStockDetails(productId, vendorId);
		return vendorStock;
	}
	

}
