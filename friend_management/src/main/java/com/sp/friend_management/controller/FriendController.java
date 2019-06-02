package com.sp.friend_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.friend_management.exception.EmailInvalidException;
import com.sp.friend_management.exception.FriendRequestException;
import com.sp.friend_management.exception.UserAccountNotFoundException;
import com.sp.friend_management.model.Friend;
import com.sp.friend_management.model.UserAccount;
import com.sp.friend_management.repository.UserAccountRepository;
import com.sp.friend_management.request.FriendRequest;
import com.sp.friend_management.response.FriendListResponse;
import com.sp.friend_management.response.FriendResponse;

@RestController
public class FriendController {

	  private final UserAccountRepository repository;

	  public FriendController(UserAccountRepository repository) {
	    this.repository = repository;
	  }
	  
	  @PostMapping("/friend")
	  public FriendResponse newFriend(@RequestBody FriendRequest request) {
		List<String> friends = request.getFriends();
		if (friends.size() != 2) {
			throw new FriendRequestException(friends.size());
		}
		
		if (friends.get(0).equalsIgnoreCase(friends.get(1))) {
			throw new FriendRequestException(friends.get(0));
		}
		
		UserAccount userAccount1 = repository.findById(friends.get(0))
	      .orElseThrow(() -> new UserAccountNotFoundException(friends.get(0)));
		
		UserAccount userAccount2 = repository.findById(friends.get(1))
	      .orElseThrow(() -> new UserAccountNotFoundException(friends.get(1)));
		
		userAccount1.addFriend(new Friend(userAccount2, userAccount1));
		userAccount2.addFriend(new Friend(userAccount1, userAccount2));
		
		repository.save(userAccount1);
		repository.save(userAccount2);
		 
		return new FriendResponse(true);
	  }
	  
	  @PostMapping("/friendlist")
	  public FriendListResponse getFriends(@RequestBody FriendRequest request) {
		String email = request.getEmail();
		if (email.isEmpty()) {
			//The checking here should not just be empty email but check whether the email is valid in terms of format.
			throw new EmailInvalidException();
		}
		
		UserAccount userAccount = repository.findById(email)
	      .orElseThrow(() -> new UserAccountNotFoundException(email));
		
		FriendListResponse response = new FriendListResponse(true);
		List<String> friends = new ArrayList<String>();
	    for (Friend friend : userAccount.getFriends()) {
	    	friends.add(friend.getFriendId());
	    }
		
	    response.setFriends(friends);
	    response.setCount(friends.size());
	    
		return response;
	  }
	  
	@PostMapping("/commonfriendlist")
	public FriendListResponse getCommonFriends(@RequestBody FriendRequest request) {
		List<String> friends = request.getFriends();
		if (friends.size() != 2) {
			throw new FriendRequestException(friends.size());
		}

		if (friends.get(0).equalsIgnoreCase(friends.get(1))) {
			throw new FriendRequestException(friends.get(0));
		}

		UserAccount userAccount1 = repository.findById(friends.get(0))
				.orElseThrow(() -> new UserAccountNotFoundException(friends.get(0)));

		UserAccount userAccount2 = repository.findById(friends.get(1))
				.orElseThrow(() -> new UserAccountNotFoundException(friends.get(1)));

		FriendListResponse response = new FriendListResponse(true);
		List<String> userAccount1Friends = new ArrayList<String>();
		for (Friend friend : userAccount1.getFriends()) {
			userAccount1Friends.add(friend.getFriendId());
		}

		List<String> commonFriends = new ArrayList<String>();
		for (Friend friend : userAccount2.getFriends()) {
			if (userAccount1Friends.contains(friend.getFriendId())) {
				commonFriends.add(friend.getFriendId());
			}
		}		
		
		response.setFriends(commonFriends);
		response.setCount(commonFriends.size());

		return response;
	}
}
