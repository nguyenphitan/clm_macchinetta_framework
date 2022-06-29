package com.example.clm.domain.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.clm.domain.jwt.JwtTokenProvider;
import com.example.clm.domain.model.CustomUserDetails;
import com.example.clm.domain.model.request.LoginRequest;
import com.example.clm.domain.model.response.JwtResponse;

@Service
public class AuthLoginServiceImpl implements AuthLoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public JwtResponse login(LoginRequest loginRequest) {
		JwtResponse response = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(), 
							loginRequest.getPassword()
					)
			);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String accessToken = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
			
			response.setAccessToken(accessToken);
			response.setMessage("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return response;
	}

}
