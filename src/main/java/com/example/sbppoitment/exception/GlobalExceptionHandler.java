package com.example.sbppoitment.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleResourceNotFoundError(ResourceNotFoundException ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", ex.getMessage());
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());

	        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String,Object>> handleConstraintError(ConstraintViolationException ex) {

	        Map<String, String> errors = ex.getConstraintViolations()
	                .stream()
	                .collect(Collectors.toMap(
	                        v -> v.getPropertyPath().toString(),
	                        ConstraintViolation::getMessage,
	                        (msg1, msg2) -> msg1
	                ));

	        Map<String, Object> response = new HashMap<>();
	        response.put("status", HttpStatus.BAD_REQUEST.value());
	        response.put("message", "Validation failed");
	        response.put("errors", errors);
	        response.put("timestamp", LocalDateTime.now());

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, Object>> handleError(Exception ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", ex.getMessage());
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
