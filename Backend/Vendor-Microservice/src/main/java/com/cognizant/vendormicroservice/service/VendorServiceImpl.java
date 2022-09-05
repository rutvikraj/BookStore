package com.cognizant.vendormicroservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.vendormicroservice.Repository.VendorRepository;
import com.cognizant.vendormicroservice.dao.VendorDAO;
import com.cognizant.vendormicroservice.dto.VendorDTO;
import com.cognizant.vendormicroservice.entity.Vendor;
import com.cognizant.vendormicroservice.exception.VendorNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	private VendorDAO vendorDAOImpl;
	
	@Autowired
	private VendorRepository vendorrepo;
	
	@Override
	public VendorDTO getVendorDetailsById(Integer vendorId) throws VendorNotFoundException {
		log.info("Get Vendor Details from Vendor Service Method");
		Vendor entity = vendorDAOImpl.getVendorDetailsByIdDao(vendorId);
		System.out.println(entity);
		if(entity!=null) {
			VendorDTO dto = new VendorDTO();
			ModelMapper mapper = new ModelMapper();
			mapper.map(entity,dto);
			return dto;
		}
		else {
			throw new VendorNotFoundException("Vendor With id [" + vendorId + "] not found");
		}
		
	}
	@Override
	public List<Vendor> totalVend(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Vendor> vend = vendorrepo.findByVendorIdIn(ids);
		return vend;
	}

}
