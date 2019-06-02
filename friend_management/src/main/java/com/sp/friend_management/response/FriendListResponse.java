package com.sp.friend_management.response;

import java.util.List;

import lombok.Data;

@Data
public class FriendListResponse {
	private boolean success;
	
	private List<String> friends;
	private int count;
	
	public FriendListResponse(boolean success) {
		this.success = success;
	}
}
