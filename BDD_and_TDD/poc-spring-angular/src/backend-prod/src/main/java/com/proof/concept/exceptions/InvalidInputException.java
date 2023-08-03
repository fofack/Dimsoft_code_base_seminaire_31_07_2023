package com.proof.concept.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super(message);
	}

}
