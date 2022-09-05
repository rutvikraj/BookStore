package com.cognizant.productmicroservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.productmicroservice.dto.ProductDTO;
import com.cognizant.productmicroservice.entity.Product;
import com.cognizant.productmicroservice.exception.ProductNotFoundException;
import com.cognizant.productmicroservice.exception.RatingGreaterThan5Exception;
import com.cognizant.productmicroservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping(value={"/product"})
public class ProductController {

	@Autowired
	private ProductService productServiceImpl;

	@GetMapping(value = { "/searchProductById/{id}" })
	public ProductDTO searchProductById(@PathVariable Integer id) {
		log.info("Inside searchProductById method");
		ProductDTO productDTO = productServiceImpl.searchProductById(id);
		log.info("Exiting searchProductById method");
		return productDTO;

	}

	@GetMapping(value = { "/searchProductByName/{name}" })
	public List<ProductDTO> searchProductByName(@PathVariable String name) {
		log.info("Inside searchProductByName method");
		List<ProductDTO> productDTO = productServiceImpl.searchProductByName(name);
		log.info("Exiting searchProductByName method");
		return productDTO;

	}

	@PostMapping(value = { "/addProductRating/{id}/{rating}" })
	public String addProductRating(@PathVariable Integer id, @PathVariable int rating)
			throws ProductNotFoundException, RatingGreaterThan5Exception {

		log.info("Inside addProductRating method");
		productServiceImpl.addProductRating(id, rating);
		log.info("Exiting addProductRating method");
		return "Rating added Successfully to the Product";

	}
	
	@GetMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts(HttpServletRequest request) 
	{
		System.out.println("***"+request.getHeader("Authorization"));
		log.info("fetching all the products");
		List<ProductDTO> productList = productServiceImpl.getAllProducts();
		log.info("All products Displayed Successfully");
		return productList;
	}

}
