package com.example.cookie.service;

import org.springframework.stereotype.Service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	// login logic
	public void login(
		LoginRequest loginRequest,
		HttpServletResponse response
	){
		var id = loginRequest.getId();
		var pw = loginRequest.getPassword();

		var optionalUser = userRepository.findByName(id);
		if(optionalUser.isPresent()){
			var userDto = optionalUser.get();
			if(userDto.getPassword().equals(pw)){
				// cookie 에 해당 정보를 저장
				var cookie = new Cookie("authorization-cookie", userDto.getId());
				cookie.setDomain("localhost"); // naver.com, daum.net -> 입력된 도메인에 대해서만 해당 cookie 를 전달
				cookie.setPath("/");
				cookie.setMaxAge(-1); // -1 의 경우 Session 과 동일하게

				// 응답 정보에 cookie 등록 -> 즉, 클라이언트에 쿠키 전달
				response.addCookie(cookie);
			}
		}else{
			throw new RuntimeException("User Not Found");
		}

	}
}
