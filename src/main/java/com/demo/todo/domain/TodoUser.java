package com.demo.todo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoUser implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String userName;
	@Column
	private String email;
	@Column(nullable = true)
	private String address;
	@JsonIgnore
	@NonNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserTask> userTasks = new HashSet<UserTask>(
			0);

	public TodoUser(String userName, String email, String address) {
		super();
		this.userName = userName;
		this.email = email;
		this.address = address;
	}
}
