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
public class CustomerWishlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer wishListId;
	private Integer productId;
	private int quantity;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate addedToWishlistDate;
	private Integer customerId;
	
	public CustomerWishlist(Integer productId, int quantity, LocalDate addedToWishlistDate, Integer customerId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.addedToWishlistDate = addedToWishlistDate;
		this.customerId = customerId;
	}
	

}
