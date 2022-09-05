package com.cognizant.productmicroservice.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.productmicroservice.dto.ErrorResponseDTO;
import com.cognizant.productmicroservice.exception.ProductNotFoundException;
import com.cognizant.productmicroservice.exception.RatingGreaterThan5Exception;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ ProductNotFoundException.class })
	public ErrorResponseDTO productNotFoundException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler({ RatingGreaterThan5Exception.class })
	public ErrorResponseDTO ratingGreaterThan5Exception(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}


}
