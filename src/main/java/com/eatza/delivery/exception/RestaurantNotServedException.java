package com.eatza.delivery.exception;

@SuppressWarnings("serial")
public class RestaurantNotServedException extends RuntimeException {

	public RestaurantNotServedException(String msg) {
		super(msg);
	}

}
