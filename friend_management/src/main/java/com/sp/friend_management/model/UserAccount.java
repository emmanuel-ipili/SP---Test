package com.sp.friend_management.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class UserAccount {

	private @Id String email;

	@OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Friend> friends;

	public UserAccount() {
	}

	public UserAccount(String email) {
		this.email = email;
	}
	
    public void addFriend(Friend friend) {
    	boolean exist = false;
    	for (Friend ifriend : friends) {
    		if (ifriend.getFriendId().equalsIgnoreCase(friend.getFriendId())) {
    			exist = true;
    			break;
    		}
    	}
    	
    	if (!exist) {
            friends.add(friend);
            friend.setUserAccount(this);    		
    	}
    }
 
    public void removeFriend(Friend friend) {
    	Iterator<Friend> iterator = friends.iterator();
    	while (iterator.hasNext()) {
    		Friend ifriend = iterator.next();
    		if (ifriend.getFriendId().equalsIgnoreCase(friend.getFriendId())) {
    			iterator.remove();
    		}
    	}
    }
}
