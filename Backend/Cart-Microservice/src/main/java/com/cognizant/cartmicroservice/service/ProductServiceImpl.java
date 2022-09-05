package com.cognizant.cartmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.cartmicroservice.entity.Product;
import com.cognizant.cartmicroservice.feign.ProductClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductClient productClient;
	
	 @Override
	public Product getProductbyId(Integer productId) {	
		 log.info("call made to product microservice for Product of id:"+productId);
		 //return restTemplate.getForObject("http://PRODUCT-SERVICE/product/searchProductById/"+productId, Product.class);
		 return productClient.searchProductById(productId);
	 } 

}
