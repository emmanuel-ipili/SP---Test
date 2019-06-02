package com.sp.friend_management.exception;

public class UserAccountNotFoundException extends RuntimeException {
	public UserAccountNotFoundException(String id) {
		super("Could not find user account  " + id);
	}
}
