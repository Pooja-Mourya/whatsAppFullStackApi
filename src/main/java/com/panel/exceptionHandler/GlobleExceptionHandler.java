package com.panel.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public String ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException userE, WebRequest webRequest){
		ErrorDetails err = new ErrorDetails(userE.getMessage(), webRequest.getDescription(false), null);
		return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST) ;
	}
}
