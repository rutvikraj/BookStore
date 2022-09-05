package com.cognizant.cartmicroservice.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.cartmicroservice.dto.ErrorResponseDTO;
import com.cognizant.cartmicroservice.exception.CartEmptyException;
import com.cognizant.cartmicroservice.exception.QuantityLimitExceededException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({CartEmptyException.class})
	public ErrorResponseDTO productNotFoundException(Exception exception,HttpServletRequest request )
	{
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
	}
	
	@ExceptionHandler({QuantityLimitExceededException.class})
	public ErrorResponseDTO QuantityLimitExceededException(Exception exception,HttpServletRequest request )
	{
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
	}
	
	
	@ExceptionHandler({Exception.class})
	public ErrorResponseDTO globalException(Exception exception,HttpServletRequest request)
	{
		return new ErrorResponseDTO(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
	}


}
