package com.dsquare.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "USER")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
