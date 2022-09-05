package com.cognizant.productmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@CrossOrigin("**")
public class ProductmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmicroserviceApplication.class, args);
	}

}
