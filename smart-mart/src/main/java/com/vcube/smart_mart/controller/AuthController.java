package com.vcube.smart_mart.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.dto.LoginRequest;
import com.vcube.smart_mart.dto.LoginResponse;
import com.vcube.smart_mart.entity.User;
import com.vcube.smart_mart.jwt.JwtUtil;
import com.vcube.smart_mart.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

		String token = jwtUtil.generateToken(user.getEmail());

		// Enforce uniform front-end distribution structure rules
		String outRole = "ROLE_USER";
		if (user.getRole() != null) {
			String checkStr = user.getRole().trim().toUpperCase();
			if (checkStr.contains("ADMIN")) {
				outRole = "ROLE_ADMIN";
			} else {
				outRole = "ROLE_USER";
			}
		}

		return new LoginResponse(token, user.getId(), user.getName(), outRole);
	}
}