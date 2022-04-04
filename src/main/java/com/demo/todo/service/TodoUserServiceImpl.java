package com.demo.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.todo.domain.TodoUser;
import com.demo.todo.repository.TodoUserRepository;

@Service
public class TodoUserServiceImpl implements TodoUserService{

	@Autowired
	TodoUserRepository userRepository;
	
	@Override
	public List<TodoUser> getUsersList() {
		return userRepository.findAll();
	}

	@Override
	public TodoUser saveUser(TodoUser user) {
		
		return userRepository.save(user);
	}

}
