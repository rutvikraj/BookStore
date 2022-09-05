package com.cognizant.cartmicroservice.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.cognizant.cartmicroservice.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishlistDTO {
	
	private Integer wishListId;
	private int quantity;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate addedToWishlistDate;
	private Integer customerId;
	private Product product;

}
