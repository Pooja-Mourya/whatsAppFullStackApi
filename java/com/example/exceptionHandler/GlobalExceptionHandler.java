package com.example.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResouseNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResouseNotFoundException ex) {
		String message = ex.getMessage();

		ApiResponse res = new ApiResponse(false, message, null, 500);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidException(
			org.springframework.web.bind.MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

}
