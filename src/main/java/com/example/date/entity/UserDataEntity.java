package com.example.date.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserDataEntity {
	@Id
	private String id;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "updated_at")
	private LocalTime updatedAt = LocalTime.now();

}
