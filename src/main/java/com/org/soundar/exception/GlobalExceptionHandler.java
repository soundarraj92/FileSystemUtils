package com.org.soundar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AppCustomException.class)
	@ResponseBody
	public ResponseEntity<ErrorDetails> appCustomException(AppCustomException ex) {

		ErrorDetails errorModel = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getMessage());
		log.error("Global Exception Handler with defined error - {}", ex.getCause());

		return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorDetails> globleExcpetionHandler(Exception ex) {

		ErrorDetails errorModel = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		log.error("Global Exception Handler with undefined error - {}", ex.getMessage());
		ex.printStackTrace();
		return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
