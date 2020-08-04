package com.eatza.delivery.exception;

@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {

	public InvalidTokenException(String msg) {
		super(msg);
	}

}
