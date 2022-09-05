package com.cognizant.cartmicroservice.dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishlistRequestDTO {
	@Id
	private Integer customerId;
	private Integer productId;
	private int quantity;	
}
