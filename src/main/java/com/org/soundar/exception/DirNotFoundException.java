package com.org.soundar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DirNotFoundException extends AppCustomException {

	private static final long serialVersionUID = 3709236632445074954L;

	public DirNotFoundException(String path) {
		super("File System Path not found - " + path);
	}
}
