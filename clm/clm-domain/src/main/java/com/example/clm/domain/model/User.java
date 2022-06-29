package com.example.clm.domain.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	@Id
	private Integer id;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String organizationId;
	
	@NotEmpty
	private String password;
	
	private String role;
}
