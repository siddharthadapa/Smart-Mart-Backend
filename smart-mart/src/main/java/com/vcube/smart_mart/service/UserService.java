package com.vcube.smart_mart.service;

import com.vcube.smart_mart.dto.LoginRequest;
import com.vcube.smart_mart.dto.RegisterRequest;

public interface UserService {

	String register(RegisterRequest request);

	String login(LoginRequest request);
}