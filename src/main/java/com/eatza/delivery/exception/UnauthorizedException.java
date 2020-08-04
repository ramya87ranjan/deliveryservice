package com.eatza.delivery.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends Exception {

	public UnauthorizedException(String msg) {
		super(msg);
	}

}
