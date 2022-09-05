package com.cognizant.vendormicroservice.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.vendormicroservice.dto.ErrorResponseDTO;
import com.cognizant.vendormicroservice.exception.ProductIdNotFoundException;
import com.cognizant.vendormicroservice.exception.QuantityLimitExceededException;
import com.cognizant.vendormicroservice.exception.VendorNotFoundException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ VendorNotFoundException.class })
	public ErrorResponseDTO vendorNotFoundException(Exception exception, HttpServletRequest request) {
		log.info("VendorNotFoundException occured...");
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler({ QuantityLimitExceededException.class })
	public ErrorResponseDTO quantityLimitExceedException(Exception exception, HttpServletRequest request) {
		log.info("QuantityLimitExceedException occured...");
		return new ErrorResponseDTO(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ ProductIdNotFoundException.class })
	public ErrorResponseDTO productNotFoundException(Exception exception, HttpServletRequest request) {
		log.info("ProductNotFoundException occured...");
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

	@ExceptionHandler({ Exception.class })
	public ErrorResponseDTO exception(Exception exception, HttpServletRequest request) {
		log.info("Exception occured...");
		return new ErrorResponseDTO(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}


}
