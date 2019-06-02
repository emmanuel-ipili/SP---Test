package com.sp.friend_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sp.friend_management.exception.FriendRequestException;

@ControllerAdvice
public class FriendRequestAdvice {

	  @ResponseBody
	  @ExceptionHandler(FriendRequestException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public String userAccountNotFoundHandler(FriendRequestException ex) {
	    return ex.getMessage();
	  }
}
