package com.demo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todo.domain.TodoUser;
import com.demo.todo.service.TodoUserService;

@RestController
public class UserController {

	@Autowired
	private TodoUserService userService;



	@GetMapping(value="/userList")
	public List<TodoUser> todoUsers() {
		
		return userService.getUsersList();
	}

	@PostMapping(value="/user/create",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<TodoUser> createUser(@RequestBody TodoUser user) {
		user = userService.saveUser(user);
		return new ResponseEntity<TodoUser>(user, HttpStatus.CREATED);
	}
}
