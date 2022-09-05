package com.cognizant.apigatewayauthenication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("**")
public class FallbackController {
	
	@GetMapping("productServiceFallback")
	public String productServiceFallbackMethod() {
		return "Product Service failed";
	}
	 
	@GetMapping("cartServiceFallback")
	public String cartServiceFallbackMethod() {
		return "Cart Service failed";
	}
	
	@GetMapping("vendorServiceFallback")
	public String vendorServiceFallbackMethod() {
		return "Vendor Service failed";
	}
	
	
	@GetMapping("ecommerceportalServiceFallback")
	public String eCommercePortalServiceFallbackMethod() {
		return "E-Commerce Portal Service failed";
	}
}
