package com.cognizant.vendormicroservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="vendor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
	@Id
	@Column(name = "vendor_id")
	private Integer vendorId;
	@Column(name = "vendor_name")
	private String vendorName;
	@Column(name = "delivery_charge")
	private double deliveryCharge;
	private float rating;

}
