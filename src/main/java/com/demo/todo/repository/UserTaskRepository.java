package com.demo.todo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.todo.domain.TodoTask;
import com.demo.todo.domain.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask, Serializable>{

	@Query("SELECT DISTINCT task FROM UserTask task WHERE task.user.id=?1")
	List<UserTask> findTasksByUserId(Long userId);
	@Query("SELECT DISTINCT task FROM UserTask task WHERE task.task.id=?1")
	List<UserTask> findUsersByTaskId(Long taskId);

}
