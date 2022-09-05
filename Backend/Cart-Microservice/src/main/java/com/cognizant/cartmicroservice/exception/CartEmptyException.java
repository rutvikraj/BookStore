package com.cognizant.cartmicroservice.exception;

public class CartEmptyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CartEmptyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
