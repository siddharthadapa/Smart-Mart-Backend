package com.vcube.smart_mart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.dto.RegisterRequest;
import com.vcube.smart_mart.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {

		return userService.register(request);
	}
}