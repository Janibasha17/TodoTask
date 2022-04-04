package com.demo.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.todo.domain.TodoTask;
import com.demo.todo.domain.TodoUser;
import com.demo.todo.model.UserTaskModel;
import com.demo.todo.service.TodoTaskService;
import com.demo.todo.service.TodoUserService;

@Controller
public class ThymeleafController {

	@Autowired
	private TodoUserService userService;
	@Autowired
	private TodoTaskService taskService;

	@GetMapping
	public String index() {
		return "index";
	}

	@PostMapping("/userNew")
	public String createUser(@ModelAttribute TodoUser user) {
		user = userService.saveUser(user);
		System.out.println(user.getId());
		return "redirect:/users";
	}

	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute("todos", taskService.getTasksList());
		return "todos";
	}

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.getUsersList());
		return "users";
	}

	@GetMapping("/usersByTask")
	public String usersByTask(@RequestParam final String taskId, Model model) {
		model.addAttribute("tasks", taskService.getUsersByTask(taskId));
		model.addAttribute("users", userService.getUsersList());
		return "usersByTask";

	}

	@PostMapping("/assignUser")
	public String assignUser(@ModelAttribute UserTaskModel usertask) {
		taskService.createUserTask(usertask);
		return "redirect:/usersByTask?taskId=" + usertask.getTaskId();
	}

	@PostMapping("/todoNew")
	public String createUser(@ModelAttribute TodoTask task) {
		task = taskService.saveTask(task);
		return "redirect:/todos";
	}

	@GetMapping("/todoUpdate/{todoId}")
	public String todoView(Model model, @PathVariable String todoId) {

		model.addAttribute("todo", taskService.getTodoById(todoId));
		return "todoView";
	}

	@PostMapping("/todoUpdate")
	public String todoUpdate(Model model, @ModelAttribute TodoTask task) {
		taskService.updateTask(task);
		return "redirect:/todos";
	}
	
	@GetMapping("/removeUserToTask/{taskId}/{userTaskId}")
	public String removeUserToTask(@PathVariable("taskId") String taskId, @PathVariable("userTaskId")String userTaskId) {
		taskService.deleteUserTask(userTaskId);
		return "redirect:/usersByTask?taskId=" + taskId;
	}
}
