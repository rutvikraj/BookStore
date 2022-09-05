package com.cognizant.vendormicroservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.vendormicroservice.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{
	public List<Vendor> findByVendorIdIn(List<Integer>ids);
}
