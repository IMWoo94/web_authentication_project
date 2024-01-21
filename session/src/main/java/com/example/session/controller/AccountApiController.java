package com.example.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session.model.LoginRequest;
import com.example.session.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

	private final UserService userService;

	// Session 이용한 로그인
	@PostMapping("/login")
	public void login(
		@RequestBody LoginRequest loginRequest,
		HttpSession httpSession
	){
		userService.login(loginRequest, httpSession);
	}

}
