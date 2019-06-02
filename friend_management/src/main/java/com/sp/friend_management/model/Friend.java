package com.sp.friend_management.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Friend {

	private @Id @GeneratedValue Long id;
	private String friendId;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
	private UserAccount userAccount;
	
	public Friend() {}
	
	public Friend(UserAccount friend, UserAccount userAccount) {
		this.friendId = friend.getEmail();
		this.userAccount = userAccount;
	}
}
