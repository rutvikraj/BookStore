package com.cognizant.vendormicroservice.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class VendorStockDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	@Column(name="vendor_id")
	private Integer vendorId;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="stock_in_hand")
	private int stockInHand;
	@Column(name="expected_stock_replinshment_date")
	private LocalDate expectedStockReplinshmentDate;


}
