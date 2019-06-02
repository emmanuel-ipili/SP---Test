package com.sp.friend_management.exception;

public class FriendRequestException extends RuntimeException {
	public FriendRequestException(int size) {
		super("The number of emails passed is " + size + " and not 2");
	}
	
	public FriendRequestException(String email) {
		super("The same email " + email + " is provided twice.");
	}
}
