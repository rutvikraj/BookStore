package com.cognizant.vendormicroservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.vendormicroservice.entity.VendorStock;

public interface VendorStockRepository extends JpaRepository<VendorStock, Integer> {
	
	@Query("select v.vendorId from VendorStock v where v.productId=:productId and stockInHand>=:quantity ")
	public List<Integer> getvendorIdsWithAvailableQuantity(Integer productId, int quantity);

	@Query("from VendorStock v where v.productId=:productId and vendorId=:vendorId")
	public VendorStock getVendorStockDetails(Integer productId, Integer vendorId);

	@Query("select max(v.stockInHand) from VendorStock v where v.productId=:productId")
	public int getMaxStockCount(Integer productId);

	@Query("select v.vendorId from VendorStock v where v.productId=:productId")
	public List<Integer> getvendorIdsForGivenProduct(Integer productId);

}
