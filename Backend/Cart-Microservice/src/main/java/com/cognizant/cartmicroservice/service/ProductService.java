package com.cognizant.cartmicroservice.service;

import com.cognizant.cartmicroservice.entity.Product;

public interface ProductService {

	Product getProductbyId(Integer productId);

}