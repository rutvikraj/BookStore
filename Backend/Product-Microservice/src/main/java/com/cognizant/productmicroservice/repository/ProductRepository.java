package com.cognizant.productmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.productmicroservice.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("from Product p where p.name like %:name%")
	public List<Product> findByNameLike(String name);
}
