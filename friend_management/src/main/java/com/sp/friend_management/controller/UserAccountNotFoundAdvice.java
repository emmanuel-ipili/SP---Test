package com.sp.friend_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sp.friend_management.exception.UserAccountNotFoundException;

@ControllerAdvice
public class UserAccountNotFoundAdvice {

	  @ResponseBody
	  @ExceptionHandler(UserAccountNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public String userAccountNotFoundHandler(UserAccountNotFoundException ex) {
	    return ex.getMessage();
	  }
}
