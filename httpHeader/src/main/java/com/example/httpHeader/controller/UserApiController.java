package com.example.httpHeader.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.httpHeader.db.UserRepository;
import com.example.httpHeader.model.UserDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

	private final UserRepository userRepository;

	@GetMapping("/me")
	public UserDto me(
		HttpServletRequest request
		, @CookieValue(name = "authorization-cookie", required = false) String authorizationCookie
		){

		// 1. HttpRequest 에 들어 있는 Cookies 정보를 통해서 필요한 Cookie 정보 찾는 방식
		var cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies){
				log.info("key : {}, value : {}", cookie.getName(), cookie.getValue());
			}
		}

		// 2. @CookieValue 어노테이션을 통해서 파라미터로 찾아오는 방식
		log.info("authorizationCookie : {}", authorizationCookie);

		var optionalUserDto = userRepository.findById(authorizationCookie);

		return optionalUserDto.get();

	}

	@GetMapping("/me2")
	public UserDto me2(
		@RequestHeader(name = "authorization", required = false) String authorizationHeader
	){
		log.info("authorizationHeader : {}", authorizationHeader);
		var optionalUserDto = userRepository.findById(authorizationHeader);

		return optionalUserDto.get();

	}
}
