package com.org.soundar.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

	HttpStatus status;

	String errorMessage;

	public ErrorDetails(HttpStatus status, String message) {

		super();

		this.status = status;
		this.errorMessage = message;

	}
}
