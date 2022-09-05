package com.cognizant.cartmicroservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.cartmicroservice.entity.CustomerWishlist;

public interface CustomerWishlistRepository extends JpaRepository<CustomerWishlist, Integer> {
	
	@Query("from CustomerWishlist c where c.customerId=:customerId")
	public List<CustomerWishlist> getCustomerWishlistById(Integer customerId);
	@Query("from CustomerWishlist c where c.customerId=:customerId and c.productId=:productId")
	public CustomerWishlist existsCustomerWishlist(Integer customerId, Integer productId);

}
