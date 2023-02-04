package com.org.soundar.exception;

import org.springframework.http.HttpStatus;

public class AppCustomException extends RuntimeException {

	private static final long serialVersionUID = 6208039533971854202L;

	public AppCustomException(String message) {
		super(message);
	}

	public AppCustomException(HttpStatus status, String message) {
		// set http staus
		super(message);
	}

}
