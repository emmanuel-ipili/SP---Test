package com.sp.friend_management.exception;

public class EmailInvalidException extends RuntimeException {
	
	public EmailInvalidException() {
		super("There is no email provided.");
	}
}
