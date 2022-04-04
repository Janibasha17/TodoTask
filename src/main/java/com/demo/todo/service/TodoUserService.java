package com.demo.todo.service;

import java.util.List;


import com.demo.todo.domain.TodoUser;

public interface TodoUserService {

	List<TodoUser> getUsersList();

	TodoUser saveUser(TodoUser user);

}
