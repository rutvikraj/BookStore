package com.cognizant.apigatewayauthenication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
@EnableWebSecurity
@CrossOrigin("*")
public class APIGatewayAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIGatewayAuthenticationApplication.class, args);
	}

}
