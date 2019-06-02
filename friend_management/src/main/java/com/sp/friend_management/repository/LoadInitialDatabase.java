package com.sp.friend_management.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sp.friend_management.model.UserAccount;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadInitialDatabase {

	  @Bean
	  CommandLineRunner initDatabase(UserAccountRepository repository) {
	    return args -> {
	      log.info("Preloading " + repository.save(new UserAccount("user1@friendmanagement.com")));
	      log.info("Preloading " + repository.save(new UserAccount("user2@friendmanagement.com")));
	    };
	  }
}
