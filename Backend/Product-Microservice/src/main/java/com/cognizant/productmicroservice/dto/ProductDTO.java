package com.cognizant.productmicroservice.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	@Id
	private Integer id;
	private String name;
	private double price;
	private String description;
	@Column(name="image_name")
	private String imageName;
	private float rating;
	private int noOfusersRated;

}
