package com.example.clm.domain.service.auth;

import com.example.clm.domain.model.request.LoginRequest;
import com.example.clm.domain.model.response.JwtResponse;

public interface AuthLoginService {
	JwtResponse login(LoginRequest loginRequest);
}
