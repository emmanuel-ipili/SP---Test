package com.sp.friend_management.response;

import lombok.Data;

@Data
public class FriendResponse {
	private boolean success;
	
	public FriendResponse(boolean success) {
		this.success = success;
	}
}
