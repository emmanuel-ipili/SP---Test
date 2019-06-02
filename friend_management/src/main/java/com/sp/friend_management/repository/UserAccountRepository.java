package com.sp.friend_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.friend_management.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
