package com.cognizant.productmicroservice.exception;

import lombok.NoArgsConstructor;


public class RatingGreaterThan5Exception extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public RatingGreaterThan5Exception(String message)
	{
		super(message);
	}

}
