package com.sp.friend_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.friend_management.exception.FriendRequestException;
import com.sp.friend_management.exception.UserAccountNotFoundException;
import com.sp.friend_management.model.Friend;
import com.sp.friend_management.model.UserAccount;
import com.sp.friend_management.repository.UserAccountRepository;
import com.sp.friend_management.request.FriendRequest;
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
		// return a json response with just the variable success that is a boolean
	    return new FriendResponse(true);
	  }
}
