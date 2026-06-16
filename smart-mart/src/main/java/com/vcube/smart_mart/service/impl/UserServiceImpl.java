package com.vcube.smart_mart.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vcube.smart_mart.dto.LoginRequest;
import com.vcube.smart_mart.dto.RegisterRequest;
import com.vcube.smart_mart.entity.User;
import com.vcube.smart_mart.repository.UserRepository;
import com.vcube.smart_mart.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public String register(RegisterRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		// Set clear explicitly formatted default string
		user.setRole("ROLE_USER");

		userRepository.save(user);
		return "User Registered Successfully";
	}

	@Override
	public String login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		return "Authenticated";
	}
}