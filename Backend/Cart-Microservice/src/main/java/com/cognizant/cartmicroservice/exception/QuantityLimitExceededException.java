package com.cognizant.cartmicroservice.exception;

public class QuantityLimitExceededException extends Exception{

	private static final long serialVersionUID = 1L;

	public QuantityLimitExceededException(String message) {
		super(message);
	}

}
