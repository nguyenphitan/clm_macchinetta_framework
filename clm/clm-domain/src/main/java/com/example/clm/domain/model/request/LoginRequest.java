package com.example.clm.domain.model.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String organizationId;
	private String password;
}
