package com.example.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session.model.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class NewUserApiController {

	@GetMapping("/me")
	public UserDto me(
		HttpSession httpSession
		// , HttpServletRequest request
	){
		/**
		 * 파라미터로 HttpSession 을 바로 가져오게 되면, Session 없는 경우에도 무조건 Session 을 만들어 버린다.
		 * Session 을 만들지 않기 위해서는 아래와 같이 Request 에서 getSession 시 false 를 주면 된다.
		 */
		// session 정보가 없으면 생성하지 않고, 있으면 session 정보를 가져 온다.
		// HttpSession session = request.getSession(false);
		// if(session != null){
		// 	var userObject = session.getAttribute("USER");
		// 	if(userObject != null){
		// 		var userDto = (UserDto)userObject;
		//
		// 		return userDto;
		// 	}
		//
		// 	// 로그인 정보가 없으면 null 반환
		// 	return null;
		// }
		//
		// // session 정보가 없으면 null 반환
		// return null;

		var userObject = httpSession.getAttribute("USER");
			if(userObject != null){
				var userDto = (UserDto)userObject;
				return userDto;
			}
		// 로그인 정보가 없으면 null 반환
		return null;
	}
}
