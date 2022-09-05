package com.cognizant.productmicroservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.productmicroservice.dto.ProductDTO;
import com.cognizant.productmicroservice.entity.Product;
import com.cognizant.productmicroservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product searchProductByIdDao(Integer id) {
		log.info("Inside searchProductByIdDao Method");
		Product dao = productRepository.findById(id).orElse(null);
		log.info("Exiting searchProductByIdDao Method");
		return dao;
	}

	@Override
	public List<Product> searchProductByNameDao(String name) {
		log.info("Inside searchProductByNameDao Method");
		List<Product> dao = productRepository.findByNameLike(name);
		log.info("Exiting searchProductByNameDao Method");
		return dao;
	}

	@Override
	public void addProductRatingDao(Product product) {
		log.info("Inside addProductRatingDao Method");
		productRepository.save(product);
		log.info("Exiting addProductRatingDao Method");
		
	}

	@Override
	public List<Product> getAllProductsDao() {
		
		log.info("Inside getAllProductsDao Method");
		List<Product> productList =productRepository.findAll();
		log.info("Exiting getAllProductsDao Method");
		return productList;
		
		
	}

}
