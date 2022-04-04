package com.demo.todo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.todo.domain.TodoUser;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Serializable>{

}
