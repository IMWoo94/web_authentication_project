package com.example.httpHeader.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.httpHeader.model.LoginRequest;
import com.example.httpHeader.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

	private final UserService userService;

	@PostMapping("/login")
	public String login(
		@RequestBody LoginRequest loginRequest,
		HttpServletResponse response,
		HttpSession httpSession
	){
		return userService.login(loginRequest, response);

	}
}
