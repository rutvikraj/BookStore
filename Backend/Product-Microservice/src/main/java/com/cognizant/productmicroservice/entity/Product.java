
package com.cognizant.productmicroservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	private Integer id;
	private String name;
	private double price;
	private String description;
	@Column(name="image_name")
	private String imageName;
	@Range(min = 1,max = 5,message = "Rating should be between 1 to 5")
	private float rating;
	private int noOfUsersRated;

}
