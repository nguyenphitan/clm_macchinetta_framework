package com.example.clm.app.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clm.domain.model.BaseResponse;
import com.example.clm.domain.model.User;
import com.example.clm.domain.model.request.LoginRequest;
import com.example.clm.domain.model.response.JwtResponse;
import com.example.clm.domain.repository.user.UserRepository;
import com.example.clm.domain.service.auth.AuthLoginService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
	@Autowired
	private AuthLoginService authLoginService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public BaseResponse<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		return BaseResponse.ofSuccess(authLoginService.login(loginRequest));
	}
	
	@PostMapping("/register")
	public User register(@Valid @RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
}
