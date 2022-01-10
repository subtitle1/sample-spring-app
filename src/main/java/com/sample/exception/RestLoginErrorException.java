package com.sample.exception;

public class RestLoginErrorException extends CustomException {

	private static final long serialVersionUID = -7799696001358188839L;

	public RestLoginErrorException(String message) {
		super(message);
	}
}
