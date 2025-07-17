package com.fleethub.hub.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
		return ResponseEntity.status(ex.getStatusCode())
				.body(new ErrorResponse(ex.getReason(), ex.getStatusCode().value()));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage(), 400));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse("Erro de validação: " + message, 400));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> DataIntegrityViolationException(DataIntegrityViolationException ex) {
		
		String message = ex.getMessage();
		Matcher matcher = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'").matcher(message);
		if (matcher.find()) {
			String value = matcher.group(1);
			String[] keyParts = matcher.group(2).split("_", 3);
			String key = keyParts[keyParts.length - 1].toUpperCase();
			String entity = matcher.group(2).split("\\.")[0].toUpperCase();
			message = "Já existe %s com %s: %s no sistema.".formatted(entity, key, value);
			
			
		} 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message, 400));
	}
}