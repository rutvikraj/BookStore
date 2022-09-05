package com.cognizant.cartmicroservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	private Integer productId;
	private String zipcode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	private Integer vendorId;
	private Integer customerId;
	private int quantity;
	
	public Cart(Integer productId, String zipcode, LocalDate deliveryDate, Integer vendoreId, Integer customerId,
			int quantity) {
		super();
		this.productId = productId;
		this.zipcode = zipcode;
		this.deliveryDate = deliveryDate;
		this.vendorId = vendoreId;
		this.customerId = customerId;
		this.quantity = quantity;
	}
	
	

}
