package com.sp.friend_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sp.friend_management.exception.EmailInvalidException;

@ControllerAdvice
public class EmailInvalidAdvice {

	  @ResponseBody
	  @ExceptionHandler(EmailInvalidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  public String userAccountNotFoundHandler(EmailInvalidException ex) {
	    return ex.getMessage();
	  }
}
