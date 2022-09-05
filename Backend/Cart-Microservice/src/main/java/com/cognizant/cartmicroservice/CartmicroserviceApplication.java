package com.cognizant.cartmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@CrossOrigin("**")
public class CartmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartmicroserviceApplication.class, args);
	}
	
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplateBean() {
//		return new RestTemplate();
//	}

}
