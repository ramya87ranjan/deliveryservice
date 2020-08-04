package com.eatza.delivery.exception;

@SuppressWarnings("serial")
public class DeliveryNotFoundException extends RuntimeException {

	public DeliveryNotFoundException(String msg) {
		super(msg);
	}

}
