package com.sp.friend_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sp.friend_management.exception.UserAccountNotFoundException;
import com.sp.friend_management.model.UserAccount;
import com.sp.friend_management.repository.UserAccountRepository;

@RestController
public class UserAccountController {

	  private final UserAccountRepository repository;

	  public UserAccountController(UserAccountRepository repository) {
	    this.repository = repository;
	  }

	  @GetMapping("/useraccounts")
	  public List<UserAccount> all() {
	    return repository.findAll();
	  }

	  @PostMapping("/useraccounts")
	  public UserAccount newUserAccount(@RequestBody UserAccount newUserAccount) {
	    return repository.save(newUserAccount);
	  }

	  @GetMapping("/useraccounts/{id}")
	  public UserAccount one(@PathVariable String id) {
	    return repository.findById(id)
	      .orElseThrow(() -> new UserAccountNotFoundException(id));
	  }

	  @PutMapping("/useraccounts/{id}")
	  public UserAccount replaceUserAccount(@RequestBody UserAccount newUserAccount, @PathVariable String id) {
	    return repository.findById(id)
	      .map(userAccount -> {
	    	  userAccount.setEmail(newUserAccount.getEmail());
	        return repository.save(userAccount);
	      })
	      .orElseGet(() -> {
	        newUserAccount.setEmail(id);
	        return repository.save(newUserAccount);
	      });
	  }

	  @DeleteMapping("/useraccount/{id}")
	  public void deleteUserAccount(@PathVariable String id) {
	    repository.deleteById(id);
	  }
}
