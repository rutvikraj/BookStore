package com.cognizant.productmicroservice.dao;

import java.util.List;

import com.cognizant.productmicroservice.entity.Product;

public interface ProductDAO {

	Product searchProductByIdDao(Integer id);

	List<Product> searchProductByNameDao(String name);

	void addProductRatingDao(Product product);

	List<Product> getAllProductsDao();

}