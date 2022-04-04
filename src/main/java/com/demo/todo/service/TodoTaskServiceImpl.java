package com.demo.todo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.todo.domain.TodoTask;
import com.demo.todo.domain.TodoUser;
import com.demo.todo.domain.UserTask;
import com.demo.todo.model.UserTaskModel;
import com.demo.todo.repository.TodoTaskRepository;
import com.demo.todo.repository.TodoUserRepository;
import com.demo.todo.repository.UserTaskRepository;

@Service("taskService")
public class TodoTaskServiceImpl implements TodoTaskService {

	@Autowired
	TodoTaskRepository taskRepository;
	
	@Autowired
	TodoUserRepository userRepository;
	
	@Autowired
	UserTaskRepository userTaskRepository;
	
	@Override
	public List<TodoTask> getTasksList() {
		return taskRepository.findAll();
	}

	@Override
	public List<TodoTask> getTasksByUser(String userId) {
		List<UserTask> userTaskList = userTaskRepository.findTasksByUserId(Long.valueOf(userId));
		List<TodoTask> tasks = userTaskList.stream()
                .map(UserTask::getTask)
                .collect(Collectors.toList());
		return tasks;
	}

	@Override
	public TodoTask saveTask(TodoTask task) {
		task.setCreatedDate(LocalDateTime.now());
		return taskRepository.save(task);
	}

	@Override
	public TodoTask updateTask(TodoTask task) {
		task.setUpdatedDate(LocalDateTime.now());
		return taskRepository.save(task);
	}
	
	@Override
	public UserTask createUserTask(UserTaskModel userTaskModel) {
		UserTask userTask = new UserTask();
		userTask.setTask(taskRepository.getById(Long.valueOf(userTaskModel.getTaskId())));
		userTask.setUser(userRepository.getById(Long.valueOf(userTaskModel.getUserId())));
		return userTaskRepository.save(userTask);
	}

	@Override
	public UserTask updateUserTask(UserTaskModel userTaskModel) {
		UserTask userTask = new UserTask();
		TodoTask task = taskRepository.getById(Long.valueOf(userTaskModel.getTaskId()));
		userTask.setTask(task);
		userTask.setUser(userRepository.getById(Long.valueOf(userTaskModel.getUserId())));
		userTask = userTaskRepository.save(userTask);

		// update todo task 
		task.setUpdatedDate(LocalDateTime.now());
		return userTask;
	}

	@Override
	public List<UserTask> getUsersByTask(String taskId) {
		List<UserTask> userTaskList = userTaskRepository.findUsersByTaskId(Long.valueOf(taskId));
	
		return userTaskList;
	}
	
	@Override
	public TodoTask getTodoById(String todoId) {
		return taskRepository.findById(Long.valueOf(todoId)).orElse(null);
	}

	@Override
	public void deleteUserTask(String userTaskId) {
		userTaskRepository.deleteById(Long.valueOf(userTaskId));
	}

	

}
