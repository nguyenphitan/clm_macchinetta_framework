package com.example.clm.domain.repository.user;

import com.example.clm.domain.model.User;
import com.example.clm.domain.model.request.LoginRequest;

public interface UserRepository  {
	User findOne(LoginRequest loginRequest);
	
	User findByUsername(String username);

	User save(User user);
}
