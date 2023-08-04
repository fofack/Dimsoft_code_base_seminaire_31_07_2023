package com.proof.concept.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CannotCreateEntityException extends Exception {
    public CannotCreateEntityException(String message) {
		super(message);
	}
}
