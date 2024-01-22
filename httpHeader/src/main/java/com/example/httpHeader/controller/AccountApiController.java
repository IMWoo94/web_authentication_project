package com.example.cookie.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

	private final UserService userService;

	@PostMapping("/login")
	public void login(
		@RequestBody LoginRequest loginRequest,
		HttpServletResponse response
	){
		userService.login(loginRequest, response);

	}
}
